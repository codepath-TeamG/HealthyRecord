package com.example.zen.healthyrecord.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zen.healthyrecord.DetailsRecordActivity;
import com.example.zen.healthyrecord.R;
import com.example.zen.healthyrecord.model.DietRecord;
import com.example.zen.healthyrecord.models.Exercise;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

/**
 * Created by joanniehuang on 2017/3/19.
 */

public class ExerciseFragment extends ItemFragment{
    private ListView listView;
    private FirebaseAuth mAuth;
    Query firebaserecords;
    String mParam1;
    String author;

    public static ExerciseFragment newInstance(String param1) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle args = new Bundle();
        args.putString("record", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        listView = super.getListView();
//        populateRecords();
        mAuth = FirebaseAuth.getInstance();

        if (getArguments() != null) {
            mParam1 = getArguments().getString("record");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.rcListView);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        if ( mParam1 == null){
            author = user.getEmail();

        } else{
            author =mParam1;
        }
        firebaserecords = mDatabase.child("SportRecoreds").orderByChild("author").equalTo(author);
        adapter = new FirebaseListAdapter<DietRecord>(getActivity(), DietRecord.class, R.layout.list_item_records, firebaserecords) {
            @Override
            protected void populateView(View convertView, DietRecord dietRecord, int position) {

                final TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                TextView tvMemo = (TextView) convertView.findViewById(R.id.tvMemo);
                TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                TextView tvCalories = (TextView) convertView.findViewById(R.id.tvCalories);
                RatingBar status = (RatingBar) convertView.findViewById(R.id.statusBar);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.ivIcon);
                final ProgressBar mProgress = (ProgressBar) convertView.findViewById(R.id.progressBar2);


                tvTitle.setText(dietRecord.content);
                tvMemo.setText(dietRecord.memo);
                tvDate.setText(dietRecord.date);
                tvCalories.setText(dietRecord.calories);
                status.setRating(dietRecord.status);

//                try {
//                    Bitmap imageBitmap = decodeFromFirebaseBase64(dietRecord.url);
//                    imageView.setImageBitmap(imageBitmap);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


//                Picasso.with(getContext()).load(dietRecord.url).resize(75,75).centerCrop().into(imageView);
                mProgress.setVisibility(View.VISIBLE);
                // Hide progress bar on successful load
                Picasso.with(getActivity()).load(dietRecord.url).resize(75,75).centerCrop()
                        .into(imageView, new com.squareup.picasso.Callback() {
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

            }
        };

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                Intent i = new Intent(getActivity(), DetailsRecordActivity.class);
                i.putExtra("date", adapter.getItem(position).date);
                i.putExtra("type", adapter.getItem(position).type);
                i.putExtra("content", adapter.getItem(position).content);
                i.putExtra("memo", adapter.getItem(position).memo);
                i.putExtra("rating", adapter.getItem(position).status);
                i.putExtra("calories", adapter.getItem(position)
                        .calories);
                i.putExtra("quantity", adapter.getItem(position).calories);
//                DietRecord r= adapter.getItem(position);
//                i.putExtra("record",r);
                i.putExtra("imageURL", adapter.getItem(position).url.toString());
//                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("record",0);
//                sharedPreferences.edit()
//                        .putString("record", adapter.getItem(position).url.toString()).apply();


                getActivity().startActivity(i);
            }
        });
    }

    private void populateRecords(){
        Exercise exercise =  new Exercise("2017/3/26", "Sport", "Running", "1"
                , "Running along the riverside", (float)3.0, 2000);
        super.getmRecords().add(exercise);
    }

}
