package com.example.amitrai.sociallogin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by me on 11/1/2015.
 */
public class Utils {

    public static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }

        return (password.trim().length() > 5) ? true : false;

    }

    public static boolean isMatchedPassword(String firstPassword, String secondPassword) {
        return (firstPassword.trim().equals(secondPassword)) ? true : false;
    }


    /**
     * checks if internet is connected
     * @return
     */
    public static boolean isConnectedToNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
