package com.example.zen.healthyrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.zen.healthyrecord.fragments.ExerciseFragment;
import com.example.zen.healthyrecord.fragments.FoodFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/*
* This activity is the record page that shows the food and exercise lists
* */

public class HomeRecordsActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ImageView drawerIcon;
    private String temp;
    RecordsPageAdapter rAdapter;
    ViewPager vpPager;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_records);
        toolbar = (Toolbar) findViewById(R.id.toolbar_homescreen);
        setSupportActionBar(toolbar);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);


        //Setup the drawer view
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);

        vpPager = (ViewPager) findViewById(R.id.viewpager);
        vpPager.setAdapter(new RecordsPageAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(vpPager);

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Brooklyn's Home</font>"));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userName = usernameFromEmail(user.getEmail());
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'></font>"));
        getSupportActionBar().setTitle(userName.toUpperCase() + " 's Home");

        if (savedInstanceState != null) {
            temp = savedInstanceState.getString("temp");
            System.out.println("onCreate: temp = " + temp);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("temp", temp);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    //setting the drawer content on navigation view
    private void setupDrawerContent(NavigationView nvDrawer) {
        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        selectDrawerItem(item);
                        return true;
                    }
                }
        );
    }

    //For the drawer item action setting
    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_first_fragment:
                //you can replace the Toast message
                Toast.makeText(this,"You are currently at your Homepage",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_second_fragment:
                //you can replace the Toast message
//                Intent f = new Intent(this, FriendsRecordActivity.class);
                Intent f = new Intent(this, FriendListActivity.class);
                startActivity(f);

                break;
            case R.id.nav_third_fragment:
                //you can replace the Toast message
                Toast.makeText(this,"log out",Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent g = new Intent(this, LoginActivity.class);
                startActivity(g);

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //handle the Item selected in drawerToggle
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_newpost:
                onAddItem(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(MenuItem item) {
        int pos = vpPager.getCurrentItem();
        Intent i = new Intent(this, AddItemActivity.class);
        i.putExtra("POS_ID", pos);
        startActivity(i);

    }


    //setup the tabs showing on the record page
    public class RecordsPageAdapter extends FragmentPagerAdapter {
        private String tabTitle[] = {"Foods","Exercise"};

        public RecordsPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                FoodFragment foodFragment = new FoodFragment();
                return foodFragment;
            } else {
                ExerciseFragment exerciseFragment = new ExerciseFragment();
                return exerciseFragment;
            }
        }

        //Return the tab title
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }

        @Override
        public int getCount() {
            return tabTitle.length;
        }
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}


