package com.split_jsbundle;

import com.split_jsbundle.rn.AsyncReactActivity;

import javax.annotation.Nullable;

public class SubAppActivity extends AsyncReactActivity {

    @Nullable
    @Override
    protected String getJSBundleName() {
        return "sub_app.jsbundle";
    }

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "rewards";
    }
}
