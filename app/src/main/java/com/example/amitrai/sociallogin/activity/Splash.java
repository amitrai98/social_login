package com.example.amitrai.sociallogin.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.amitrai.sociallogin.R;

/**
 * Created by cynogen on 25/10/15.
 */
public class Splash extends Activity{

    // time in seconds to show splash screen
    private final int SPLASH_TIME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


    }
}
