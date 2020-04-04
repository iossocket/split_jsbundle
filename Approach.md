## Approach 1:

> pph-app-rewards-lib (js)
    >> pph-app-base-lib (js) -- include UI components, API client, Local storage and Security storage

(Maybe also need some native lib if necessary)

**Why we need the base lib ?**

1. Rewards dose not just include UI code, it also sends request.

2. pph-app-rewards-lib should not directly include all the *api request*, *local storage* code.

3. If we export rewards to HiSG, we also need to use the same way to integrate rewards.

> ps: for the pph-app-base-lib can also be used in other pph apps (eo)


## Approach 2: split js bundle

1. Demo

2. Mechanism

2.1 What‘s inside js bundle
```js
var __BUNDLE_START_TIME__=...
...
__d(function(...){},123,[]);
...
__d(function(a,s,t,e,n,r,u){n.exports={name:"statusbar",displayName:"statusbar"}},484,[]);
__r(82);
__r(0);
```

2.2 What's the number used for (how __d function works)
```js
__d = function (r, i, n) {
    if (null != e[i]) return;
    var o = {
        dependencyMap: n,
        factory: r,
        hasError: !1,
        importedAll: t,
        importedDefault: t,
        isInitialized: !1,
        publicModule: { exports: {} }
    };
    e[i] = o
}
```
> So the number is a global identifier for js module, but the number is random generated, if we need to split the js bundle we need to fix the id

`metro/src/lib/createModuleIdFactory.js`
```js
'use strict';

function createModuleIdFactory(): (path: string) => number {
  const fileToIdMap: Map<string, number> = new Map();
  let nextId = 0;
  return (path: string) => {
    let id = fileToIdMap.get(path);
    if (typeof id !== 'number') {
      id = nextId++;
      fileToIdMap.set(path, id);
    }
    return id;
  };
}

module.exports = createModuleIdFactory;
```

2.3 Metro(react-native packaging tool) provide a way to customize the id generation.

`metro.config.js` metro config file in the root dir of react-native project

```js
const createModuleIdFactory = require('./config/createModuleIdFactory');
module.exports = {
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: false,
      },
    }),
  },
  serializer: {
    createModuleIdFactory: createModuleIdFactory
  }
};
```

```js
'use strict';
const path = require('path');
const pathSep = path.posix.sep;

function createModuleIdFactory() {
  const fileToIdMap = new Map();
  const projectRootPath = `${process.cwd()}`;
  return (path) => {
    let moduleName = '';
    if (path.indexOf(`node_modules${pathSep}react-native${pathSep}Libraries${pathSep}`) > 0) {
      moduleName = path.substr(path.lastIndexOf(pathSep) + 1);
    } else if (path.indexOf(projectRootPath) === 0) {
      moduleName = path.substr(projectRootPath.length + 1);
    }
    moduleName = moduleName.replace('.js', '');
    moduleName = moduleName.replace('.png', '');
    let regExp = pathSep === '\\' ? new RegExp('\\\\', 'gm') : new RegExp(pathSep, 'gm');
    moduleName = moduleName.replace(regExp, '_');
    return moduleName;
  };
}

module.exports = createModuleIdFactory;
```

```js
...
__d(
    function (g, r, i, a, m, e, d) {
        var t = r(d[0]);
        Object.defineProperty(e, "__esModule", { value: !0 }), e.default = void 0;
        var o = t(r(d[1])), u = r(d[2]), n = u.StyleSheet.create({ image: { height: 24 } });
        e.default = function (t) {
            var c = t.routeName, l = t.focused, f = { Home: r(l ? d[3] : d[4]), My: r(l ? d[5] : d[6]) };
            return o.default.createElement(u.Image, { style: n.image, source: f[c], resizeMode: "contain" })
        }
    },
    "src_components_TabBarIcon_index",
    [
        "node_modules_@babel_runtime_helpers_interopRequireDefault",
        "node_modules_react_index",
        "react-native-implementation",
        "src_assets_icons_home_fill",
        "src_assets_icons_home",
        "src_assets_icons_my_fill",
        "src_assets_icons_my"
    ]
);
...
```

2.3 split jsbundle to base.bundle/app1.bundle/app2.bundle ... 

```
react-native bundle --platform ios --dev false --entry-file base.js --bundle-output build/ios/common.jsbundle --assets-dest build/ios/

react-native bundle --platform ios --dev false --entry-file index.js --bundle-output build/ios/main_app_all.jsbundle --assets-dest build/ios/

react-native bundle --platform ios --dev false --entry-file rewards/index.js --bundle-output build/ios/sub_app_all.jsbundle --assets-dest build/ios/

node diff.js ./build/ios/common.jsbundle ./build/ios/main_app_all.jsbundle ./build/ios/main_app.jsbundle
node diff.js ./build/ios/common.jsbundle ./build/ios/sub_app_all.jsbundle ./build/ios/sub_app.jsbundle
```

2.4 Native cod

