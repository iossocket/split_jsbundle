package com.split_jsbundle;

import android.os.Bundle;
import com.split_jsbundle.rn.AsyncReactActivity;

import javax.annotation.Nullable;

public class MainAppActivity extends AsyncReactActivity {

    @Nullable
    @Override
    protected String getJSBundleName() {
        return "main_app.jsbundle";
    }

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "split_jsbundle";
    }
}
