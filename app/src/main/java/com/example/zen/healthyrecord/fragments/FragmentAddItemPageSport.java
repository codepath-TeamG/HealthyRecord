package com.example.zen.healthyrecord.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zen.healthyrecord.AddItemActivity;
import com.example.zen.healthyrecord.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.zen.healthyrecord.R.id.photoView;
import static com.example.zen.healthyrecord.R.id.txtFood;

/**
 * Created by sharonyu on 2017/3/24.
 */

public class FragmentAddItemPageSport extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView txtDatePicker;
    TextView txtTimePicker;
    TextView txtFood;
    TextView txtQuan;
    ImageView photoView;






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
        txtQuan = (TextView)v.findViewById(R.id.txtQuan);
        txtFood.setText("EXERCISE");
        txtQuan.setText("DURATION");
        photoView = (ImageView)v.findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.top_photo_healthy);



        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String formattedDate = df.format(c.getTime());
        String formattedTime = tf.format(c.getTime());

        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),R.layout.spinner_item){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item,parent,false);
                ((TextView)v.findViewById(android.R.id.text1)).setText(getItem(position));
                return v;
            }
        };

        adapter.add("Fruit");
        adapter.add("Softdrink");
        spinner.setAdapter(adapter);
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

}
