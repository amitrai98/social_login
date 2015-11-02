package com.example.amitrai.sociallogin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.amitrai.sociallogin.R;

/**
 * Created by cynogen on 25/10/15.
 */
public class Splash extends Activity{

    // time in seconds to show splash screen
    private final int SPLASH_TIME = 1;

    private Handler handler=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNextActivity();
            }
        }, SPLASH_TIME * 1000);
    }

    /**
     * Start new SearchActivity
     */
    private void startNextActivity(){
        Intent searchActivityIntent=new Intent(this, LoginActivity.class);
        startActivity(searchActivityIntent);
        finish();
    }
}
