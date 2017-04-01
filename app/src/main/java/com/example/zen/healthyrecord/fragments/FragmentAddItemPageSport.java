package com.example.zen.healthyrecord.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zen.healthyrecord.AddItemActivity;
import com.example.zen.healthyrecord.R;
import com.example.zen.healthyrecord.model.DietRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by sharonyu on 2017/3/24.
 */

public class FragmentAddItemPageSport extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView txtDatePicker;
    TextView txtTimePicker;
    TextView txtFood;
    public Spinner spnFood;
    public EditText etCal;
    public EditText etMemo;
    public RatingBar rtbStatus;
    public ImageView photoView;
    private DatabaseReference mDatabase;
    private String url;



    private FloatingActionButton btnOpenCamera;

    // TODO: Rename and change types of parameters


    public FragmentAddItemPageSport () {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentAddItemPageSport newInstance() {
        FragmentAddItemPageSport fragment = new FragmentAddItemPageSport();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_item_page, container, false);

        txtDatePicker = (TextView)v.findViewById(R.id.txtDatePicker);
        txtTimePicker = (TextView)v.findViewById(R.id.txtTimePicker);
        txtFood = (TextView)v.findViewById(R.id.txtFood);
        txtFood.setText("EXERCISE");
        spnFood = (Spinner) v.findViewById(R.id.spnFood);
        etCal = (EditText) v.findViewById(R.id.etCal);
        etMemo = (EditText) v.findViewById(R.id.etMemo);
        rtbStatus = (RatingBar) v.findViewById(R.id.rtbStatus);
        photoView = (ImageView) v.findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.top_photo_healthy);


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String formattedDate = df.format(c.getTime());
        String formattedTime = tf.format(c.getTime());

       // Spinner spinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),R.layout.spinner_item){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item,parent,false);
                ((TextView)v.findViewById(android.R.id.text1)).setText(getItem(position));
                return v;
            }
        };

        adapter.add("Run");
        adapter.add("Yoga");
        spnFood.setAdapter(adapter);
        txtDatePicker.setText(formattedDate);
        txtTimePicker.setText(formattedTime);

        return v;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnOpenCamera = (FloatingActionButton) view.findViewById(R.id.open_camera);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AddItemActivity) getActivity()).camera(view);
            }
        });
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    private void writeNewDietPost(String userId, String username, String date, String time,
                                  String content,String url,String calories,String memo,Float status) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("SportRecoreds").push().getKey();
        DietRecord post = new DietRecord(userId, username, date, time, content, url, calories,memo,status);
        Map<String, Object> postValues = post.toMap();
        mDatabase.child("SportRecoreds").child(key).setValue(postValues);

    }

    public void getValue(){
        String date = txtDatePicker.getText().toString();
        String time = txtTimePicker.getText().toString();
        String food = spnFood.getSelectedItem().toString();
        String calories = etCal.getText().toString();
        String memo = etMemo.getText().toString();
        Float status = rtbStatus.getRating();

//        photoView.buildDrawingCache();
//        Bitmap bmp= photoView.getDrawingCache();

//        String imgUrl = encodeBitmap(bmp);
        AddItemActivity activity = (AddItemActivity) getActivity();

        url =activity.getDownloadSportUrl();

        writeNewDietPost("1", "Bob", date,time,food, url,calories,memo,status);

    }

    public String encodeBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        return imageEncoded;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AddItemActivity)getActivity()).mState = 2;
    }
}
