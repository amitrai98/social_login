package com.example.amitrai.sociallogin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.amitrai.sociallogin.R;
import com.example.amitrai.sociallogin.fragments.AppLoginFragment;
import com.example.amitrai.sociallogin.fragments.SignUpFragment;
import com.example.amitrai.sociallogin.util.AppLoger;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginFragment;

/**
 * Created by cynogen on 25/10/15.
 */
public class LoginActivity extends ActionBarActivity {

    // tag to be logged
    private String TAG = LoginActivity.class.getSimpleName();

    public static CallbackManager callbackManager ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();

        openLoginFragment();

    }

    /**
     *  open login dialog
     */
    private void openLoginFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new AppLoginFragment())
                .addToBackStack(LoginFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {

        int fragment_count = getSupportFragmentManager().getBackStackEntryCount();
        if(fragment_count>1){
            super.onBackPressed();
        }else{
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Opens signup dialog
     * @param view
     */
    public void signUp(View view){
        AppLoger.e(TAG, "signup working");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SignUpFragment())
                .addToBackStack(SignUpFragment.class.getSimpleName())
                .commit();

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
