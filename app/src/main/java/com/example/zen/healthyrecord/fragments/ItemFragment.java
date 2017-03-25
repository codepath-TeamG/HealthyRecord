package com.example.zen.healthyrecord.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zen.healthyrecord.R;
import com.example.zen.healthyrecord.model.DietRecord;
import com.example.zen.healthyrecord.models.Records;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.IOException;
import java.util.ArrayList;


public class ItemFragment extends Fragment {

    private ArrayList<Records> records;
//    private RecordsAdapter adapter;
    private ListView listView;
    FirebaseListAdapter<DietRecord> adapter;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, parent, false);
        listView = (ListView) view.findViewById(R.id.rcListView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query firebaserecords = mDatabase.child("DietRecoreds").orderByChild("date");
        adapter = new FirebaseListAdapter<DietRecord>(getActivity(), DietRecord.class, R.layout.list_item_records, firebaserecords) {
            @Override
            protected void populateView(View convertView, DietRecord dietRecord, int position) {

                TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                TextView tvMemo = (TextView) convertView.findViewById(R.id.tvMemo);
                TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                TextView tvCalories = (TextView) convertView.findViewById(R.id.tvCalories);
                RatingBar status = (RatingBar) convertView.findViewById(R.id.statusBar);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.ivIcon);


                tvTitle.setText(dietRecord.content);
                tvMemo.setText(dietRecord.memo);
                tvDate.setText(dietRecord.date);
                tvCalories.setText(dietRecord.calories);
                status.setRating(dietRecord.status);

                try {
                    Bitmap imageBitmap = decodeFromFirebaseBase64(dietRecord.url);
                    imageView.setImageBitmap(imageBitmap);

                    } catch (IOException e) {
                    e.printStackTrace();
                }

//                Picasso.with(getContext()).load(dietRecord.url).resize(75,75).into(imageView);

            }
        };


        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
//                Intent i = new Intent(getActivity(), DetailsRecordActivity.class);
//                i.putExtra("Time", getAdapter().getItem(position).getTime());
//                i.putExtra("Memo", getAdapter().getItem(position).getMemo());
//                i.putExtra("rating", getAdapter().getItem(position).getStatusRating());
//                i.putExtra("calories", getAdapter().getItem(position).getCalories());
//                i.putExtra("quantity", getAdapter().getItem(position).getQuantity());
//
//                getActivity().startActivity(i);
//            }
//        });

        return view;
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        records = new ArrayList<>();
//        adapter = new RecordsAdapter(getActivity(), records);
    }

    public ListView getListView() {
        return listView;
    }

    public ArrayList<Records> getmRecords() {
        return records;
    }

//    public RecordsAdapter getAdapter() {
//        return adapter;
//    }
}


