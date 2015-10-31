package com.example.amitrai.sociallogin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.amitrai.sociallogin.R;
import com.example.amitrai.sociallogin.util.AppLoger;

/**
 * Created by cynogen on 25/10/15.
 */
public class LoginActivity extends Activity {

    // tag to be logged
    private String TAG = LoginActivity.class.getSimpleName();

    private Button btn_loging = null, btn_signup = null, btn_google = null, btn_facebook = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_loging = (Button) findViewById(R.id.btn_login);

        startActivity(new Intent(LoginActivity.this, Activity_MainMenu.class));

        btn_loging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Activity_MainMenu.class));
            }
        });

    }

    /**
     *  open login dialog
     * @param view
     */
    public void attemptLogin(View view){
        AppLoger.e(TAG, "login working");
    }

    /**
     * Opens signup dialog
     * @param view
     */
    public void signUp(View view){
        AppLoger.e(TAG, "signup working");
    }


    /**
     * login with google account
     * @param view
     */
    public void googleLogin(View view){
        AppLoger.e(TAG, "google working");
    }

    /**
     * login with facebook
     * @param view
     */
    public void facebookLogin(View view){
        AppLoger.e(TAG, "facebook working");
    }
}
