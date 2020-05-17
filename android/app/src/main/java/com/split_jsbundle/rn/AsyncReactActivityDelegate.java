package com.split_jsbundle.rn;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;

import javax.annotation.Nullable;

public class AsyncReactActivityDelegate extends ReactActivityDelegate {

    public AsyncReactActivityDelegate(Activity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
    }

    public AsyncReactActivityDelegate(ReactActivity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return super.getReactNativeHost();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void loadApp(String appKey) {
        super.loadApp(appKey);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
