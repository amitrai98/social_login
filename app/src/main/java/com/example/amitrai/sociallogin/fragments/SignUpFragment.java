package com.example.amitrai.sociallogin.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amitrai.sociallogin.R;
import com.example.amitrai.sociallogin.util.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {

    private EditText mEtName, mEtEmail, mEtPassword, mEtRepeat;
    private TextView mLogin;
    private Button mSubmit;

    public SignUpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtName = (EditText) view.findViewById(R.id.et_fullname);
        mEtEmail = (EditText) view.findViewById(R.id.et_email);
        mEtPassword = (EditText) view.findViewById(R.id.et_pwd);
        mEtRepeat = (EditText) view.findViewById(R.id.et_retype_pwd);
        mLogin = (TextView) view.findViewById(R.id.tv_login);
        mSubmit = (Button) view.findViewById(R.id.btn_signup);

        mLogin.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                getActivity().onBackPressed();
                break;

            case R.id.btn_signup:
                checkValidation();
                break;
        }
    }

    private void checkValidation() {
        String name = mEtName.getText().toString().trim();
        String email = mEtEmail.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String retype = mEtRepeat.getText().toString().trim();

        if (name.isEmpty()) {
            mEtName.setError(getString(R.string.empty_error));
            return;
        }
        if (email.isEmpty()) {
            mEtEmail.setError(getString(R.string.empty_error));
            return;
        }
        if (!Utils.isValidEmail(email)) {
            mEtEmail.setError(getString(R.string.invalid_email));
        }

        if (password.isEmpty()) {
            mEtPassword.setError(getString(R.string.empty_error));
            return;
        }
        if (!Utils.isValidPassword(password)) {
            mEtPassword.setError("Password require minimum 6 characters");
            return;
        }

        if (!Utils.isMatchedPassword(password, retype)) {
            mEtRepeat.setError("Password does not match");
            return;
        }

        proceedToDashboard();
    }

    private void proceedToDashboard() {

    }
}
