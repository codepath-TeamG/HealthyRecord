package com.example.zen.healthyrecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsRecordActivity extends AppCompatActivity {

    private ImageView ivPhoto;
    private TextView tvDateValue;
    private TextView tvTimeValue;
    private TextView tvFoodValue;
    private TextView tvQuantityValue;
    private TextView tvMemoValue;
    private TextView tvTimeLabel;
    private TextView tvQuantLabel;
    private RatingBar ratingBarValue;
    private String imageLoadURL;
    private TextView tvCaloriesValue;
    private ProgressBar mProgress;

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
        tvQuantLabel = (TextView) findViewById(R.id.tvQuantLabel);
        tvTimeLabel = (TextView) findViewById(R.id.tvTimeLabel);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);

//        DietRecord r = (DietRecord) getIntent().getSerializableExtra("record");
//        Uri imageURL = Uri.parse(getIntent().getStringExtra("imageURL"));
//        ivPhoto.setImageURI(imageURL);
//        Log.d("DEBUG",imageURL.toString());

        String url = (String) getIntent().getStringExtra("imageURL");
//        Picasso.with(this).load(url).placeholder(R.drawable.ic_cancel).into(ivPhoto);

        // Show progress bar

        mProgress.setVisibility(View.VISIBLE);
        // Hide progress bar on successful load
        Picasso.with(this).load(url)
                .into(ivPhoto, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (mProgress != null) {
                            mProgress.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });


//        SharedPreferences sharedPreferences = getSharedPreferences("record",0);
//        String url = sharedPreferences.getString("record", "");
//        try {
//            Bitmap imageBitmap = decodeFromFirebaseBase64(url);
//            ivPhoto.setImageBitmap(imageBitmap);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        tvDateValue.setText(getIntent().getStringExtra("date"));
        tvTimeValue.setText(getIntent().getStringExtra("type"));
        tvFoodValue.setText(getIntent().getStringExtra("content"));
        tvQuantityValue.setText(getIntent().getStringExtra("quantity"));
        tvMemoValue.setText(getIntent().getStringExtra("memo"));
        ratingBarValue.setRating(getIntent().getExtras().getFloat("rating"));

        int pos= getIntent().getExtras().getInt("POS_ID");

        if(pos==0) {
            tvTimeLabel.setText("TYPE");
        }
    }


}
