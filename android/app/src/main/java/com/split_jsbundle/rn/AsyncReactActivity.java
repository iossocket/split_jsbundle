package com.split_jsbundle.rn;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

public abstract class AsyncReactActivity extends AppCompatActivity
        implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {

    private static Set<String> loadedJSBundles = new HashSet<>();

    private final AsyncReactActivityDelegate mDelegate;

    protected AsyncReactActivity() {
        mDelegate = createReactActivityDelegate();
    }

    protected @Nullable String getMainComponentName() {
        return null;
    }

    protected @Nullable String getJSBundleName() {
        return null;
    }

    protected AsyncReactActivityDelegate createReactActivityDelegate() {
        return new AsyncReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected ReactRootView createRootView() {
                return new RNGestureHandlerEnabledRootView(AsyncReactActivity.this);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. load jsbundle file
        loadJSBundleFromAssets(() -> {
            // 2. call mDelegate.onCreate
            mDelegate.onCreate(savedInstanceState);
        });
    }

    private void loadJSBundleFromAssets(LoadJSBundleListener listener) {
        CatalystInstance catalystInstance = getReactContext().getCatalystInstance();
        String source = getJSBundleName();
        if (catalystInstance == null || source == null) {
            return;
        }
        if (loadedJSBundles.contains(source)) {
            listener.onJSBundleLoaded();
            return;
        }
        if(!source.startsWith("assets://")) {
            source = "assets://" + source;
        }
        catalystInstance.loadScriptFromAssets(this.getAssets(), source, false);
        loadedJSBundles.add(source);
        listener.onJSBundleLoaded();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDelegate.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDelegate.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelegate.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mDelegate.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDelegate.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mDelegate.onKeyUp(keyCode, event) || super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return mDelegate.onKeyLongPress(keyCode, event) || super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (!mDelegate.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (!mDelegate.onNewIntent(intent)) {
            super.onNewIntent(intent);
        }
    }

    @Override
    public void requestPermissions(
            String[] permissions,
            int requestCode,
            PermissionListener listener) {
        mDelegate.requestPermissions(permissions, requestCode, listener);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {
        mDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected final ReactInstanceManager getReactInstanceManager() {
        return mDelegate.getReactInstanceManager();
    }

    protected final ReactContext getReactContext() {
        return getReactInstanceManager().getCurrentReactContext();
    }
}
