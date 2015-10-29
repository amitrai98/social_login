package com.example.amitrai.sociallogin.app;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by amitrai on 21/10/15.
 */
public class AppInitializer extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
