package com.example.amitrai.sociallogin.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;

import com.example.amitrai.sociallogin.R;
import com.example.amitrai.sociallogin.adapters.NavigationAdapter;
import com.example.amitrai.sociallogin.fragments.Fragment_Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cynogen on 29/10/15.
 */
public class Activity_MainMenu extends FragmentActivity{

    private ListView drawer_list = null;
    private android.support.v4.widget.DrawerLayout drawerLayout = null;
    private NavigationAdapter adapter = null;
    private List<String> options_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        init();
        options_list.add("Facebook");
        options_list.add("Google+");
        options_list.add("Twitter");
        options_list.add("Website");
        options_list.add("Phone Number");
        options_list.add("Share App");
        options_list.add("Logout");
        openMenuFragment();

    }

    /**
     * initalizing views
     */
    private void init(){
        drawer_list = (ListView) findViewById(R.id.left_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        View headerview = getLayoutInflater().inflate(R.layout.design_drawer_header,null);
        drawer_list.addHeaderView(headerview);
        adapter = new NavigationAdapter(this, options_list);
        drawer_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    /**
     * opens menu fragment
     */
    private void openMenuFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new Fragment_Menu())
                .addToBackStack(Fragment_Menu.class.getSimpleName())
                .commit();
    }
}
