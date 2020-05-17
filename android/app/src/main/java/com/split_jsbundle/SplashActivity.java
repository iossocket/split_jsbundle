package com.split_jsbundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;

public class SplashActivity extends AppCompatActivity implements ReactInstanceManager.ReactInstanceEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ReactInstanceManager reactInstanceManager = ((ReactApplication)getApplication()).getReactNativeHost().getReactInstanceManager();
        reactInstanceManager.addReactInstanceEventListener(this);

        if (!reactInstanceManager.hasStartedCreatingInitialContext()) {
            reactInstanceManager.createReactContextInBackground();
        }
        Log.d("HomeActivity", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("HomeActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HomeActivity", "onResume");
    }

    @Override
    public void onReactContextInitialized(ReactContext context) {
        Log.d("HomeActivity", "onReactContextInitialized");
        startActivity(new Intent(SplashActivity.this, MainAppActivity.class));
    }
}
