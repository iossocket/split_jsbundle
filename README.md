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

#### To be done

Support Android
