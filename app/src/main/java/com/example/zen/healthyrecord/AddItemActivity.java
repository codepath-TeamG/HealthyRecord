package com.example.zen.healthyrecord;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.zen.healthyrecord.Fragment.DatePickerFragment;
import com.example.zen.healthyrecord.Fragment.TimePickerFragment;
import com.example.zen.healthyrecord.Fragment.addItemPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sharonyu on 2017/3/19.
 */

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener, addItemPage.OnFragmentInteractionListener,
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    Button btnSport;
    Button btnFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_layout);
        btnFood = (Button) findViewById(R.id.btnDiet);
        btnSport = (Button) findViewById(R.id.btnSport);
        btnFood.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "You didn't take photo", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View v) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragAddItem, new addItemPage());
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragAddItem, new addItemPage());
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