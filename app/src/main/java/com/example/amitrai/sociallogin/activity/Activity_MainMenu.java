package com.example.amitrai.sociallogin.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;

import com.example.amitrai.sociallogin.R;
import com.example.amitrai.sociallogin.adapters.NavigationAdapter;
import com.example.amitrai.sociallogin.fragments.Fragment_Menu;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cynogen on 29/10/15.
 */
public class Activity_MainMenu extends ActionBarActivity{

    private ListView drawer_list = null;
    private android.support.v4.widget.DrawerLayout drawerLayout = null;
    private NavigationAdapter adapter = null;
    private List<String> options_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

//        init();

        openMenuFragment();

        initNavigationDrawer();

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


    private void initNavigationDrawer(){
        // Create the AccountHeader



        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.md_black_1000)
                        .withOnlyMainProfileImageVisible(true)
                .addProfiles(
                        new ProfileDrawerItem().withName("").withEmail("www.test.com").withIcon(getResources().getDrawable(R.drawable.app_icon))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(getResources().getString(R.string.facebook));
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName(getResources().getString(R.string.google));
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName(getResources().getString(R.string.twitter));
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName(getResources().getString(R.string.website));
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName(getResources().getString(R.string.phone_no));
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withName(getResources().getString(R.string.share_app));
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withName(getResources().getString(R.string.logout));

//create the drawer and remember the `Drawer` result object
        new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        item5,
                        item6,
                        item7,
                        new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position){


                            case 1:
                                break;

                            case 2:
                                break;

                            case 3:
                                break;

                            case 4:
                                openUrl();
                                break;

                            case 5:
                                break;

                            case 6:
                                openShareDialog();
                                break;

                            case 7:
                                logOut();
                                break;

                        }

                        return true;
                    }
                })
                .build();


//        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
//        getActionBar().setDisplayHomeAsUpEnabled(true);






//        Drawer drawer = new DrawerBuilder().withAccountHeader(headerResult).withActivity(this).build();




    }


    private void logOut(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void openShareDialog(){
//        Intent share = new Intent(Intent.ACTION_SEND);
//        share.setType("image/jpeg");
//
//
//        share.putExtra(Intent.EXTRA_STREAM,
//                Uri.parse("file:///sdcard/DCIM/Camera/myPic.jpg"));
//
//        startActivity(Intent.createChooser(share, "Share App"));

        try
        { Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        }
        catch(Exception e)
        { //e.toString();
        }
    }


    private void openUrl(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.in"));
        startActivity(browserIntent);
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
