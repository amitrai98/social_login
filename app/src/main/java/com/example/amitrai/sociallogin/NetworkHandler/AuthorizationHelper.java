package com.example.amitrai.sociallogin.NetworkHandler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.amitrai.sociallogin.app.AppInitializer;
import com.example.amitrai.sociallogin.app.Constants;
import com.example.amitrai.sociallogin.util.ErrorParser;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by sergey on 30.03.15.
 */
public class AuthorizationHelper {

    public static final String ERROR_SEPARATOR = ":";
    public static final String USERNAME_FIELD = "username";
    public static final String EMAIL_FIELD = "email";
    private static final String LOGIN_URL = Constants.LOGIN_URL;
    private static final String REGISTRATION_URL =  Constants.REGISTRATION_URL;
    private static final String TAG = AuthorizationHelper.class.getSimpleName();
    private static final String PASSWORD_FIELD = "password";
    private static final String PASSWORD1_FIELD = "password1";
    private static final String PASSWORD2_FIELD = "password2";
    private static final String GENDER_ID_FIELD = "gender_id";
    private static final String PROGRAM_ID_FIELD = "program_id";
    private static final String TOKEN_FIELD = "key";
    private OnErrorCallback mOnErrorCallback = new OnErrorCallback() {
        @Override
        public void onError(String error) {
            Log.e(TAG, error);
        }
    };
    private Context mContext;

    public AuthorizationHelper(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * login processor
     * @param email
     * @param password
     * @param callback
     */
    public void login(String email, String password, @NonNull final OnLoginCallback callback) {

        JSONObject object = new JSONObject();
        try {
            object.put(EMAIL_FIELD, email);
            object.put(PASSWORD_FIELD, password);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                LOGIN_URL,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            Log.d(TAG, "Login response:\n" + jsonObject.toString(4));

                            String token = jsonObject.getString(TOKEN_FIELD);
                            callback.onLoggedIn(token);

                        } catch (JSONException e) {
                            mOnErrorCallback.onError(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = ErrorParser.parseVolleyError(volleyError);
                        Log.e(TAG, "Login error: " + message);
                        mOnErrorCallback.onError(message);
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 8,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppInitializer.getRequestQueue().add(request);
    }


    /**
     * registration processor
     * @param username
     * @param email
     * @param password
     * @param callback
     */
    public void register(String username, String email, String password,
                 @NonNull final OnRegisterCallback callback) {

        JSONObject object = new JSONObject();
        try {
            object.put(USERNAME_FIELD, username);
            object.put(EMAIL_FIELD, email);
            object.put(PASSWORD1_FIELD, password);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                REGISTRATION_URL,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            Log.d(TAG, "Registration response:\n" + jsonObject.toString(4));
                            callback.onRegistered();

                        } catch (JSONException e) {
                            mOnErrorCallback.onError(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String errorMessage = ErrorParser.parseVolleyError(volleyError);

                        Log.e(TAG, "Registration error: " + errorMessage);
                        mOnErrorCallback.onError(errorMessage);
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 8,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppInitializer.getRequestQueue().add(request);
    }


    public static void requestLogin(String username, String password, final OnLoginCallback callback, final OnErrorCallback onerror){

        JSONObject object = new JSONObject();
        try {
            object.put(USERNAME_FIELD, username);
            object.put(PASSWORD_FIELD, password);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                LOGIN_URL,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            Log.d(TAG, "Login response:\n" + jsonObject.toString(4));

//                            String token = jsonObject.getString(TOKEN_FIELD);
                            callback.onLoggedIn("");

                        } catch (JSONException e) {
                            onerror.onError(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = ErrorParser.parseVolleyError(volleyError);
                        Log.e(TAG, "Login error: " + message);
                        onerror.onError(message);
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 8,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppInitializer.getRequestQueue().add(request);

    }

    public void setOnErrorCallback(OnErrorCallback OnErrorCallback) {
        this.mOnErrorCallback = OnErrorCallback;
    }

    public interface OnErrorCallback {
        public void onError(String error);
    }

    public interface OnRegisterCallback {
        public void onRegistered();
    }

    public interface OnLoginCallback {
        public void onLoggedIn(String token);
    }
}
