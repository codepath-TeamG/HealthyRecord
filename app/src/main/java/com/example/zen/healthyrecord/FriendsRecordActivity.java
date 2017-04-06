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

/**
 * Created by sharonyu on 2017/3/30.
 */

public class FriendsRecordActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ImageView drawerIcon;
    RecordsPageAdapter rAdapter;
    ViewPager vpPager;
    public String fName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_records);
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

        fName = getIntent().getStringExtra("friendName");


        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'></font>"));
        getSupportActionBar().setTitle(fName + " 's Home");



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
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()){
            case R.id.nav_first_fragment:
                //you can replace the Toast message
                Intent intent = new Intent(this,HomeRecordsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.nav_second_fragment:
                //you can replace the Toast message
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
        getMenuInflater().inflate(R.menu.menu_friend_home,menu);
        return true;
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
                FoodFragment foodFragment = FoodFragment.newInstance(fName);
                return foodFragment;
            } else {
                ExerciseFragment exerciseFragment = ExerciseFragment.newInstance(fName);
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

    public void sendEmail(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        String userMail = getIntent().getStringExtra("friendMail");
        intent.putExtra(Intent.EXTRA_EMAIL, userMail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, ""));
        }


    }
}
