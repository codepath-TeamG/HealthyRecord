package com.example.zen.healthyrecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailsRecordActivity extends AppCompatActivity {

    private ImageView ivPhoto;
    private TextView tvDateValue;
    private TextView tvTimeValue;
    private TextView tvFoodValue;
    private TextView tvQuantityValue;
    private TextView tvMemoValue;
    private RatingBar ratingBarValue;
    private String imageLoadURL;
    private TextView tvCaloriesValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ivPhoto = (ImageView) findViewById(R.id.photoView);
        tvDateValue = (TextView) findViewById(R.id.tvDateValue);
        tvTimeValue = (TextView) findViewById(R.id.tvTimeValue);
        tvFoodValue = (TextView) findViewById(R.id.tvFoodValue);
        tvQuantityValue = (TextView) findViewById(R.id.tvQuantValue);
        tvMemoValue = (TextView) findViewById(R.id.tvMemoValue);
        ratingBarValue = (RatingBar) findViewById(R.id.ratingBarValue);
        tvCaloriesValue = (TextView) findViewById(R.id.tvCaloriesValue);

        //Picasso.with(this).load(getIntent().getStringExtra("imageURL")).into(ivPhoto);
        tvDateValue.setText(getIntent().getStringExtra("date"));
        tvTimeValue.setText(getIntent().getStringExtra("time"));
        tvFoodValue.setText(getIntent().getStringExtra("type"));
        tvQuantityValue.setText(getIntent().getStringExtra("quantity"));
        tvMemoValue.setText(getIntent().getStringExtra("memo"));
        ratingBarValue.setRating(getIntent().getExtras().getFloat("rating"));
        tvCaloriesValue.setText(getIntent().getStringExtra("calories"));
    }

}
