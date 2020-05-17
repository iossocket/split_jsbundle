### split js bundle

#### How to run it

1. `npm ci`
2. `cd ios && pod install`
3. Open iOS project by Xcode and run

#### Related script

1. iOS
```
react-native bundle --platform ios --dev false --entry-file base.js --bundle-output build/ios/common.jsbundle --assets-dest build/ios/

react-native bundle --platform ios --dev false --entry-file index.js --bundle-output build/ios/main_app_all.jsbundle --assets-dest build/ios/

react-native bundle --platform ios --dev false --entry-file rewards/index.js --bundle-output build/ios/sub_app_all.jsbundle --assets-dest build/ios/

node diff.js ./build/ios/common.jsbundle ./build/ios/main_app_all.jsbundle ./build/ios/main_app.jsbundle
node diff.js ./build/ios/common.jsbundle ./build/ios/sub_app_all.jsbundle ./build/ios/sub_app.jsbundle
```

2. Android
```
react-native bundle --platform android --dev false --minify=true --entry-file base.js --bundle-output build/android/common.jsbundle --assets-dest build/android/res

react-native bundle --platform android --dev false --minify=true --entry-file index.js --bundle-output build/android/main_app_all.jsbundle --assets-dest build/android/res

react-native bundle --platform android --dev false --entry-file rewards/index.js --bundle-output build/ios/sub_app_all.jsbundle --assets-dest build/android/res

node diff.js ./build/android/common.jsbundle ./build/android/main_app_all.jsbundle ./build/android/main_app.jsbundle
node diff.js ./build/android/common.jsbundle ./build/android/sub_app_all.jsbundle ./build/android/sub_app.jsbundle
```

#### 解读：
1. [React Native 拆包实践1 - bundle server的启动过程](https://www.jianshu.com/p/d1d77c709053)
2. [React Native 拆包实践2 - react-native start](https://www.jianshu.com/p/473dddd751c8)
3. [React Native 拆包实践3 - react-native bundle](https://www.jianshu.com/p/359721b85f12)
4. [React Native 拆包实践4 - createModuleIdFactory](https://www.jianshu.com/p/717f4cb7e682)
5. [React Native 拆包实践5 - iOS 按需加载jsbundle](https://www.jianshu.com/p/832d7c01b101)
6. [React Native 拆包实践6 - Android 启动流程](https://www.jianshu.com/p/89757051ffe5)
7. [React Native 拆包实践7 - Android 按需加载jsbundle](https://www.jianshu.com/p/ce80c2924292)
