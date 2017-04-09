package com.example.zen.healthyrecord.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddItemPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddItemPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddItemPage extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public TextView txtDatePicker;
    public TextView txtTimePicker;
    public Spinner spnFood;
    public EditText etCal;
    public EditText etMemo;
    public RatingBar rtbStatus;
    public ImageView photoView;
    private DatabaseReference mDatabase;
    private String url;
    private FirebaseAuth mAuth;



    private FloatingActionButton btnOpenCamera;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public FragmentAddItemPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAddItemPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAddItemPage newInstance(String param1, String param2) {
        FragmentAddItemPage fragment = new FragmentAddItemPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_item_page, container, false);

        txtDatePicker = (TextView)v.findViewById(R.id.txtDatePicker);
//        txtTimePicker = (TextView)v.findViewById(R.id.txtTimePicker);
        spnFood = (Spinner) v.findViewById(R.id.spnFood);
        etCal = (EditText) v.findViewById(R.id.etCal);
        etMemo = (EditText) v.findViewById(R.id.etMemo);
        rtbStatus = (RatingBar) v.findViewById(R.id.rtbStatus);
        photoView = (ImageView) v.findViewById(R.id.photoView);
        Picasso.with(getContext()).load(R.drawable.food1).resize(600, 400).into(photoView);

        spnFood.getBackground().setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary),PorterDuff.Mode.SRC_ATOP);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String formattedDate = df.format(c.getTime());
        String formattedTime = tf.format(c.getTime());


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),R.layout.spinner_item){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item,parent,false);
                ((TextView)v.findViewById(android.R.id.text1)).setText(getItem(position));
                return v;
            }
        };

        adapter.add("Hamburger");
        adapter.add("Fruit");
        adapter.add("Softdrink");
        adapter.add("Other");

        spnFood.setAdapter(adapter);
        txtDatePicker.setText(formattedDate);
//        txtTimePicker.setText(formattedTime);


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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




    private void writeNewDietPost(String userId, String username, String date,
                                  String content,String url,String calories,String memo,Float status) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("DietRecoreds").push().getKey();
        DietRecord post = new DietRecord(userId, username, date,content, url, calories,memo,status);
        Map<String, Object> postValues = post.toMap();
        mDatabase.child("DietRecoreds").child(key).setValue(postValues);
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/DietRecoreds/" + key, postValues);
//        childUpdates.put("/user-DietRecords/" + userId + "/" + key, postValues);

//        mDatabase.updateChildren(childUpdates);
//        records.add(post);
    }

    public void getValue(){
        String date = txtDatePicker.getText().toString();
//        String time = txtTimePicker.getText().toString();
        String food = spnFood.getSelectedItem().toString();
        String calories = etCal.getText().toString();
        String memo = etMemo.getText().toString();
        Float status = rtbStatus.getRating();

//        photoView.buildDrawingCache();
//        Bitmap bmp= photoView.getDrawingCache();

//        String imgUrl = encodeBitmap(bmp);


//        String url = getArguments().getString("ImageDownloadUrl");

        AddItemActivity activity = (AddItemActivity) getActivity();

        url =activity.getDownloadUrl();


//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("photo",0);
//        String url = sharedPreferences.getString("i", "");

        FirebaseUser user = mAuth.getCurrentUser();

        writeNewDietPost(user.getUid(), user.getEmail(), date,food, url,calories,memo,status);

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
        ((AddItemActivity)getActivity()).mState = 1;
    }
}
