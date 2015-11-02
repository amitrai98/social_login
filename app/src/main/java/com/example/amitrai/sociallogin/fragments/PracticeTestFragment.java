package com.example.amitrai.sociallogin.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amitrai.sociallogin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeTestFragment extends Fragment implements View.OnClickListener {


    public PracticeTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice_test, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_begintest).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_begintest:
                Fragment fragment = new QuestionFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(fragment.getClass().getSimpleName()).commit();
                break;
        }
    }
}
