package com.example.zen.healthyrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.example.zen.healthyrecord.fragments.ExerciseFragment;
import com.example.zen.healthyrecord.fragments.FoodFragment;


public class HomeRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_records);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        vpPager.setAdapter(new RecordsPageAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(vpPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_newpost:
                onAddItem(item);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void onAddItem(MenuItem item) {
        Intent i = new Intent(this, AddItemActivity.class);
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
}


