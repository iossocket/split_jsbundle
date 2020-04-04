
'use strict';
const path = require('path');
const pathSep = path.posix.sep;

function createModuleIdFactory() {
  const fileToIdMap = new Map();
  let nextId = 0;
  const projectRootPath = `${process.cwd()}`;
  console.log(projectRootPath);
  return (path) => {
    let id = fileToIdMap.get(path);
    if (typeof id !== 'number') {
      id = nextId++;
      fileToIdMap.set(path, id);
    }
    let moduleName = '';
    if (path.indexOf(`node_modules${pathSep}react-native${pathSep}Libraries${pathSep}`) > 0) {
      //这里是去除路径中的'node_modules/react-native/Libraries/‘之前（包括）的字符串，可以减少包大小，可有可无
      moduleName = path.substr(path.lastIndexOf(pathSep) + 1);
    //   console.log("rn: ", moduleName);
    } else if (path.indexOf(projectRootPath) === 0) {
      //这里是取相对路径，不这么弄的话就会打出_user_smallnew_works_....这么长的路径，还会把计算机名打进去
      moduleName = path.substr(projectRootPath.length + 1);
    //   console.log("non - rn: ", moduleName);
    } else {
      console.log("fuck");
    }
    moduleName = moduleName.replace('.js', '');
    moduleName = moduleName.replace('.png', '');
    let regExp = pathSep === '\\' ? new RegExp('\\\\', 'gm') : new RegExp(pathSep, 'gm');
    moduleName = moduleName.replace(regExp, '_');
    // console.log(`id: ${id}, path: ${path}`);
    return moduleName;
  };
}

module.exports = createModuleIdFactory;