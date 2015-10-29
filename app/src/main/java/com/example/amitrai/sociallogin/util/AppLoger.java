package com.example.amitrai.sociallogin.util;

import android.util.Log;

/**
 * Created by amitrai on 21/10/15.
 */
public class AppLoger {

    public static boolean LOG_ENABLED = true;

    public static void e(String tag, String message){
        if(LOG_ENABLED)
            Log.e(""+tag, ""+message);
    }
    public static void d(String tag, String message){
        if(LOG_ENABLED)
            Log.d("" + tag, "" + message);
    }
    public static void i(String tag, String message){
        if(LOG_ENABLED)
            Log.i("" + tag, "" + message);
    }
    public static void v(String tag, String message){
        if(LOG_ENABLED)
            Log.v(""+tag, ""+message);
    }

}
