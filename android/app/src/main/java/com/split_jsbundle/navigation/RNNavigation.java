package com.split_jsbundle.navigation;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.split_jsbundle.MainApplication;

import javax.annotation.Nonnull;

public class RNNavigation extends ReactContextBaseJavaModule {

    public RNNavigation(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "RNNavigation";
    }

    @ReactMethod
    public void navigateTo(String name) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            return;
        }
        MainApplication application = (MainApplication) activity.getApplication();
        if (application == null) {
            return;
        }
        Class<?> klass = application.existingModules().get(name);
        activity.startActivity(new Intent(activity, klass));
    }

    @ReactMethod
    public void goBack() {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            return;
        }
        activity.finish();
    }
}
