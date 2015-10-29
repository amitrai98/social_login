package com.example.amitrai.sociallogin.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amitrai.sociallogin.R;

/**
 * Created by cynogen on 27/10/15.
 */
public class Fragment_Menu extends Fragment implements View.OnClickListener{

    private Button btn_online_practice_test = null,
            btn_notification = null, btn_about_us = null, btn_contact_us = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container,false);

        init(view);
        return view;
    }

    /**
     * initializing views
     */
    private void init(View v){
        btn_online_practice_test = (Button) v.findViewById(R.id.btn_online_practice_test);
        btn_notification = (Button) v.findViewById(R.id.btn_notification);
        btn_about_us = (Button) v.findViewById(R.id.btn_about_us);
        btn_contact_us = (Button) v.findViewById(R.id.btn_contact_us);

        btn_online_practice_test.setOnClickListener(this);
        btn_notification.setOnClickListener(this);
        btn_about_us.setOnClickListener(this);
        btn_contact_us.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_online_practice_test:

                break;

            case R.id.btn_notification:

                break;

            case R.id.btn_about_us:

                break;

            case R.id.btn_contact_us:

                break;


        }

    }
}
