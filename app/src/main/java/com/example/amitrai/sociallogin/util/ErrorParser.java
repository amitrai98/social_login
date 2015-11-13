package com.example.amitrai.sociallogin.util;

import com.android.volley.VolleyError;
import com.example.amitrai.sociallogin.R;
import com.example.amitrai.sociallogin.app.AppInitializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;


/**
 * Created by sergey on 07.04.15.
 */
public class ErrorParser {

    public static String parseVolleyError(VolleyError volleyError) {

        String message;

        if (volleyError.networkResponse != null) {
            message = new String(volleyError.networkResponse.data);
        } else {
            if (volleyError.getCause() instanceof UnknownHostException) {
                message = AppInitializer.getInstance().getString(R.string.error_message_no_internet_connection);
            } else {
                message = volleyError.getMessage();
            }
        }
        return message == null ? AppInitializer.getInstance().getString(R.string.error_message_no_internet_connection) : message;
    }

    public static JSONObject parseJSONError(String jsonError) throws JSONException {

        return new JSONObject(jsonError);
    }

    public static boolean hasInvalidToken(JSONObject jsonError) throws JSONException {

        return jsonError.has("detail") && jsonError.getString("detail").equals("Invalid token.");
    }
}
