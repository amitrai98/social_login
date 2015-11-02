package com.example.amitrai.sociallogin.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitrai.sociallogin.R;
import com.example.amitrai.sociallogin.activity.Activity_MainMenu;
import com.example.amitrai.sociallogin.util.AppLoger;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AppLoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AppLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppLoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private com.facebook.login.widget.LoginButton btn_facebook = null;
    private Button btn_google = null, btn_loging = null;
    private TextView btn_signup = null ;
    private EditText edt_username, edt_password= null ;

    private final String TAG = AppLoginFragment.class.getSimpleName();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppLoginFragment newInstance(String param1, String param2) {
        AppLoginFragment fragment = new AppLoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private void initView(View view){

        btn_loging = (Button) view.findViewById(R.id.btn_login);
        btn_facebook = (LoginButton) view.findViewById(R.id.btn_facebook);
        btn_google = (Button) view.findViewById(R.id.btn_google);
        btn_signup = (TextView) view.findViewById(R.id.btn_signup);
        edt_username = (EditText) view.findViewById(R.id.edt_username);
        edt_password = (EditText) view.findViewById(R.id.edt_password);

        LoginButton loginButton = (LoginButton) view.findViewById(R.id.btn_facebook);
        CallbackManager callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AppLoger.e(TAG, "login success");
            }

            @Override
            public void onCancel() {
                AppLoger.e(TAG, "login canceled");
            }

            @Override
            public void onError(FacebookException error) {
                AppLoger.e(TAG, "an error occured");
            }
        });



//        btn_google.setOnClickListener(this);
//        btn_facebook.setOnClickListener(this);


//        startActivity(new Intent(LoginActivity.this, Activity_MainMenu.class));

        btn_loging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                if (username.length() > 0 && username.equalsIgnoreCase("test") && password.length() > 0 && password.equalsIgnoreCase("test")) {

                    Activity act = getActivity();
                    act.startActivity(new Intent(act, Activity_MainMenu.class));
                    act.finish();
                } else {
                    Toast.makeText(getActivity(), "Username or password is invalid", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBarActivity act = (ActionBarActivity) getActivity();
                act.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SignUpFragment())
                        .addToBackStack(SignUpFragment.class.getSimpleName())
                        .commit();
            }
        });
    }

    public AppLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        FacebookSdk.sdkInitialize(getActivity());
        CallbackManager callbackManager = CallbackManager.Factory.create();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_facebook:
//
//                break;
//            case R.id.btn_google:
//
//                break;
//        }
//    }

    /**
     * attempt to login with facebook
     */
    private void loginWithFacebook(){

    }

    /**
     * attempt to login with facebook
     */
    private void loginWithGoogle(){

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
