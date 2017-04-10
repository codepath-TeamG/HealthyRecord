package com.example.zen.healthyrecord;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zen.healthyrecord.model.User;
import com.example.zen.healthyrecord.models.Detail;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.example.zen.healthyrecord.R.id.userName;
import static java.lang.System.load;


public class DetailsRecordActivity extends AppCompatActivity {
//
    private ImageView ivPhoto;
    private RecyclerView rvDetails;
    private DetailsAdapter dAdapter;
    private List<Detail> details;
    String dateValue;
    String typeValue;
    String contentValue;
    String caloriesValue;
    String memoValue;
    String statusValue;
    private Toolbar toolbar;

    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_record );
        toolbar = (Toolbar) findViewById(R.id.toolbar_homescreen);
        setSupportActionBar(toolbar);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        ivPhoto = (ImageView) findViewById(R.id.photoView);
        Uri imageURL = Uri.parse(getIntent().getStringExtra("imageURL"));
        ivPhoto.setImageURI(imageURL);
        String url = (String) getIntent().getStringExtra("imageURL");

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


        dateValue = getIntent().getStringExtra("date");
        typeValue= getIntent().getStringExtra("type");
        contentValue= getIntent().getStringExtra("content");
        caloriesValue= getIntent().getStringExtra("quantity");
        memoValue= getIntent().getStringExtra("memo");
        float rawRatingValue= getIntent().getExtras().getFloat("rating");
        if (rawRatingValue > 3.5 && rawRatingValue <=5){
            statusValue = "Feel Good" ;
        }else if(rawRatingValue >= 2 && rawRatingValue <= 3.5){
            statusValue = "Feel Normal";
        }else{
            statusValue = "Feel Bad";
        }






        rvDetails = (RecyclerView) findViewById( R.id.rvDetail );
        rvDetails.setHasFixedSize( true );


        rvDetails.setLayoutManager( new LinearLayoutManager( this ) );
        int pos= getIntent().getExtras().getInt("POS_ID");
        if (pos == 0){
            details = Detail.getDetails(dateValue, typeValue,contentValue,caloriesValue,memoValue,statusValue);
            getSupportActionBar().setTitle("Your Food Record");

        } else {
            details = Detail.getDetailsExercise(dateValue, typeValue,contentValue,caloriesValue,memoValue,statusValue);
            getSupportActionBar().setTitle("Your Exercise Record");
        }
        dAdapter = new DetailsAdapter( DetailsRecordActivity.this, details );

        rvDetails.setAdapter( dAdapter );

    }


    public String getDateVlue(){
        String timeValue = getIntent().getStringExtra("date");
        return  timeValue;
    }








}
