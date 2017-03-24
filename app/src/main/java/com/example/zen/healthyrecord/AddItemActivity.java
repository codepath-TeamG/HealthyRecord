package com.example.zen.healthyrecord;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.zen.healthyrecord.fragments.DatePickerFragment;
import com.example.zen.healthyrecord.fragments.FoodFragment;
import com.example.zen.healthyrecord.fragments.FragmentAddItemPage;
import com.example.zen.healthyrecord.fragments.FragmentAddItemPageSport;
import com.example.zen.healthyrecord.fragments.TimePickerFragment;
import com.example.zen.healthyrecord.fragments.addButtonFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sharonyu on 2017/3/19.
 */

public class AddItemActivity extends AppCompatActivity implements FragmentAddItemPage.OnFragmentInteractionListener,
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {


    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private Toolbar toolbar;
    private ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState == null) {
            setContentView(R.layout.add_item_layout);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragAddItem, new addButtonFragment(), "SOMETAG").commit();
        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        View headerLayout = nvDrawer.getHeaderView(0);

        setupDrawerContent(nvDrawer);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);

        photoView=(ImageView) findViewById(R.id.photoView);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {

        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

    }

        private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = FragmentAddItemPage.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = FoodFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = addButtonFragment.class;
                break;
            default:
                fragmentClass = FragmentAddItemPage.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragAddItem, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }



    public void camera(View v) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap) extras.get("data");
            photoView = (ImageView)findViewById(R.id.photoView);
            photoView.setImageBitmap(bmp);
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "You didn't take photo", Toast.LENGTH_LONG).show();
        }
    }


    public void changeFragmentFood() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragAddItem, new FragmentAddItemPage());
        ft.commit();
    }

    public void changeFragmentSport() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragAddItem, new FragmentAddItemPageSport());
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragAddItem, new FragmentAddItemPage());
        getFragmentManager().popBackStack();
        ft.commit();

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        TextView txtDatePicker = (TextView) findViewById(R.id.txtDatePicker);
        txtDatePicker.setText(formattedDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String formattedTime = tf.format(c.getTime());
        TextView txtTimePicker = (TextView) findViewById(R.id.txtTimePicker);
        txtTimePicker.setText(formattedTime);
    }
}