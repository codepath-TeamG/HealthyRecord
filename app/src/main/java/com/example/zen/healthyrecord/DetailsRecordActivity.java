package com.example.zen.healthyrecord;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;

import static com.example.zen.healthyrecord.fragments.ItemFragment.decodeFromFirebaseBase64;

public class DetailsRecordActivity extends AppCompatActivity {

    private ImageView ivPhoto;
    private TextView tvDateValue;
    private TextView tvTimeValue;
    private TextView tvFoodValue;
    private TextView tvQuantityValue;
    private TextView tvMemoValue;
    private TextView tvFoodLabel;
    private TextView tvQuantLabel;
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
        tvQuantLabel = (TextView) findViewById(R.id.tvQuantLabel);
        tvFoodLabel = (TextView) findViewById(R.id.tvFoodLabel);

//        DietRecord r = (DietRecord) getIntent().getSerializableExtra("record");
//        Uri imageURL = Uri.parse(getIntent().getStringExtra("imageURL"));
//        ivPhoto.setImageURI(imageURL);
//        Log.d("DEBUG",imageURL.toString());
//        Picasso.with(this).load(r.url).into(ivPhoto);
//        String url = (String) getIntent().getStringExtra("imageURL");
        SharedPreferences sharedPreferences = getSharedPreferences("record",0);
        String url = sharedPreferences.getString("record", "");
        try {
            Bitmap imageBitmap = decodeFromFirebaseBase64(url);
            ivPhoto.setImageBitmap(imageBitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDateValue.setText(getIntent().getStringExtra("date"));
        tvTimeValue.setText(getIntent().getStringExtra("time"));
        tvFoodValue.setText(getIntent().getStringExtra("type"));
        tvQuantityValue.setText(getIntent().getStringExtra("quantity"));
        tvMemoValue.setText(getIntent().getStringExtra("memo"));
        ratingBarValue.setRating(getIntent().getExtras().getFloat("rating"));
        tvCaloriesValue.setText(getIntent().getStringExtra("calories"));

        int pos= getIntent().getExtras().getInt("POS_ID");

        if(pos==0) {
            tvQuantLabel.setText("DURATION");
            tvFoodLabel.setText("EXERCISE");
        }
    }

}
