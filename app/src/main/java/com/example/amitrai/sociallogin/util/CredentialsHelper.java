package com.example.amitrai.sociallogin.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.Serializable;

/**
 * Created by sergey on 01.04.15.
 */
public class CredentialsHelper implements Serializable{

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "token";

    private Context mContext;
    private SharedPreferences mPreferences;

    public CredentialsHelper(Context context) {
        this.mContext = context;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static CredentialsHelper get(Context context) {
        return new CredentialsHelper(context);
    }

    public void saveCredentials(CredentialsHolder credentialsHolder) {

        mPreferences
                .edit()
                .putString(EMAIL, credentialsHolder.email)
                .putString(PASSWORD, credentialsHolder.password)
                .apply();
    }

    public void clearCredentials() {

        mPreferences
                .edit()
                .remove(EMAIL)
                .remove(PASSWORD)
                .apply();
    }

    public CredentialsHolder getCredentials() {

        String email = mPreferences
                .getString(EMAIL, null);

        String password = mPreferences
                .getString(PASSWORD, null);

        if (email != null && password != null) {

            return new CredentialsHolder(email, password);
        }
        return null;
    }

    public void saveToken(String token) {
        mPreferences.edit()
                .putString(TOKEN, token)
                .apply();
    }

    public String getToken() {
        return mPreferences.getString(TOKEN, null);
    }

    public static class CredentialsHolder implements Serializable {

        protected String email;
        protected String password;

        public CredentialsHolder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
