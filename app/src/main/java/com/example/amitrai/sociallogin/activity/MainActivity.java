package com.example.amitrai.sociallogin.activity;

import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.amitrai.sociallogin.R;
import com.example.amitrai.sociallogin.util.AppLoger;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ResultCallback<People.LoadPeopleResult> {

    private com.google.android.gms.common.SignInButton btn_google = null;
    com.facebook.login.widget.LoginButton btn_facebook = null;

    private String TAG = MainActivity.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;

    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    private int RC_SIGN_IN = 100 ;

    CallbackManager callbackManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, new Fragment_Menu())
//                .addToBackStack(Fragment_Menu.class.getSimpleName())
//                .commit();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();


//        callbackManager = CallbackManager.Factory.create();



//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this).addApi(Plus.API)
//                .addScope(Plus.SCOPE_PLUS_LOGIN).build();


//        linkXml();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    /**
     * initialing views
     */
    private void linkXml(){
//        btn_facebook = (com.facebook.login.widget.LoginButton) findViewById(R.id.facebook);
//        btn_google = (com.google.android.gms.common.SignInButton) findViewById(R.id.google);

//        btn_facebook.setOnClickListener(this);
        btn_google.setOnClickListener(this);


        btn_facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AppLoger.e(TAG, "facebook login success");
            }

            @Override
            public void onCancel() {
                AppLoger.e(TAG, "login canceled");
            }

            @Override
            public void onError(FacebookException e) {
                AppLoger.e(TAG, "error occured");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * attempts google login
     */
    private void validateGoogleLogin(){
        AppLoger.e(TAG, "google clicked");
        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
        else
            mGoogleApiClient.connect();
    }


    /**
     * attempts facebook login
     *
     */
    private void validateFacebookLogin() {
        AppLoger.e(TAG, "facebook clicked");



        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.amitrai.sociallogin",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.facebook){
//            validateFacebookLogin();
//        }else if (v.getId() == R.id.google){
//            validateGoogleLogin();
//        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.e(TAG,"connected"+bundle);
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();
            String personPhoto = currentPerson.getImage().getUrl();
            String personGooglePlusProfile = currentPerson.getUrl();
            String location = currentPerson.getCurrentLocation();

            AppLoger.e(TAG, "name "+personName+"/n"+"profile img  "+personPhoto+"/n profile  "+personGooglePlusProfile+"/n location "+location);
        }
        Plus.PeopleApi.loadVisible(mGoogleApiClient, null)
                .setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

//        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
//                    startIntentSenderForResult(connectionResult.getResolution().getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                Log.e(TAG,""+connectionResult);
            }
//        } else {
//            // Show the signed-out UI
//            Log.e(TAG, "signed out");
//        }
    }

//    @Override
//    public void onReceiveResult(People.LoadPeopleResult result) throws RemoteException {
//
//    }

    @Override
    public void onResult(People.LoadPeopleResult loadPeopleResult) {
        if (loadPeopleResult.getStatus().getStatusCode() == CommonStatusCodes.SUCCESS) {
            PersonBuffer personBuffer = loadPeopleResult.getPersonBuffer();
            try {
                int count = personBuffer.getCount();
                for (int i = 0; i < count; i++) {
                    Log.d(TAG, "Display name: " + personBuffer.get(i).getDisplayName());
                }
            } finally {
                personBuffer.release();
            }
        } else {
            Log.e(TAG, "Error requesting visible circles: " + loadPeopleResult.getStatus());
        }
    }
}
