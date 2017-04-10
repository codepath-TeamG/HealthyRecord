package com.example.zen.healthyrecord;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zen.healthyrecord.model.User;
import com.example.zen.healthyrecord.models.Detail;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static java.lang.System.load;


public class DetailsRecordActivity extends AppCompatActivity {
//
    private ImageView ivPhoto;

//    private TextView tvTimeValue;

//    private TextView tvQuantityValue;
//    private TextView tvMemoValue;
//    private TextView tvTimeLabel;
//    private TextView tvQuantLabel;
//    private RatingBar ratingBarValue;
//    private String imageLoadURL;
//    private TextView tvCaloriesValue;
    private RecyclerView rvDetails;
    private DetailsAdapter dAdapter;
    private List<Detail> details;
    String dateValue;
    String typeValue;
    String contentValue;
    String caloriesValue;
    String memoValue;
    String statusValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_record );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        ivPhoto = (ImageView) findViewById(R.id.photoView);
        Uri imageURL = Uri.parse(getIntent().getStringExtra("imageURL"));
        ivPhoto.setImageURI(imageURL);
        String url = (String) getIntent().getStringExtra("imageURL");
        Picasso.with(this).load(url).into(ivPhoto);

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

        } else {
            details = Detail.getDetailsExercise(dateValue, typeValue,contentValue,caloriesValue,memoValue,statusValue);
        }
        dAdapter = new DetailsAdapter( DetailsRecordActivity.this, details );

        rvDetails.setAdapter( dAdapter );

    }


    public String getDateVlue(){
        String timeValue = getIntent().getStringExtra("date");
        return  timeValue;
    }





//        DietRecord r = (DietRecord) getIntent().getSerializableExtra("record");
//        Uri imageURL = Uri.parse(getIntent().getStringExtra("imageURL"));
//        ivPhoto.setImageURI(imageURL);
//        Log.d("DEBUG",imageURL.toString());

//        String url = (String) getIntent().getStringExtra("imageURL");
//        Picasso.with(this).load(url).into(ivPhoto);


//        SharedPreferences sharedPreferences = getSharedPreferences("record",0);
//        String url = sharedPreferences.getString("record", "");
//        try {
//            Bitmap imageBitmap = decodeFromFirebaseBase64(url);
//            ivPhoto.setImageBitmap(imageBitmap);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        tvDateValue.setText(getIntent().getStringExtra("date"));
//        tvTimeValue.setText(getIntent().getStringExtra("type"));
//        tvFoodValue.setText(getIntent().getStringExtra("content"));
//        tvQuantityValue.setText(getIntent().getStringExtra("quantity"));
//        tvMemoValue.setText(getIntent().getStringExtra("memo"));
//        ratingBarValue.setRating(getIntent().getExtras().getFloat("rating"));
//        tvFoodValue.setText(getIntent().getStringExtra("type"));
//        tvQuantityValue.setText(getIntent().getStringExtra("quantity"));
//        tvMemoValue.setText(getIntent().getStringExtra("memo"));
//        ratingBarValue.setRating(getIntent().getExtras().getFloat("rating"));
////        tvCaloriesValue.setText(getIntent().getStringExtra("calories"));


//                    int pos= getIntent().getExtras().getInt("POS_ID");
//
//                    if(pos==0) {
//                        tvTimeLabel.setText("TYPE");
//                    }
//    }


}
