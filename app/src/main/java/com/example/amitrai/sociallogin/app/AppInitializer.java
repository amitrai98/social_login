package com.example.amitrai.sociallogin.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;

/**
 * Created by amitrai on 21/10/15.
 */
public class AppInitializer extends Application {

    // context of the application
    public static AppInitializer instance = null;

    public static RequestQueue requestQueue = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        requestQueue = Volley.newRequestQueue(this);
    }

    /**
     * returns instance of the application
     * @return
     */
    public static AppInitializer getInstance() {
        return instance;
    }

    public static RequestQueue getRequestQueue(){
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(instance);
        return requestQueue;
    }
}
