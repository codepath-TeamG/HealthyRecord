package com.example.zen.healthyrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zen.healthyrecord.model.DietRecord;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Map;

public class FirebaseTestActivity extends AppCompatActivity {
    ListView lvRecords;

    ArrayList<DietRecord> records;
    FirebaseListAdapter<DietRecord> adapter;


    private static final String TAG = "FirebaseTestActivity";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);

        lvRecords = (ListView) findViewById(R.id.lvRecords);
//        records = new ArrayList<>();
//        adapter = new RecordListAdapter(this,records);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query firebaserecords = mDatabase.child("DietRecoreds").orderByChild("date");

        adapter = new FirebaseListAdapter<DietRecord>(this, DietRecord.class,R.layout.record_item, firebaserecords) {
            @Override
            protected void populateView(View convertView, DietRecord dietRecord, int position) {

                TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                TextView tvDietTime = (TextView) convertView.findViewById(R.id.tvDietTime);
                TextView tvDietContent = (TextView) convertView.findViewById(R.id.tvDietContent);

                tvDate.setText(dietRecord.date);
                tvDietTime.setText(dietRecord.time);
                tvDietContent.setText(dietRecord.content);


            }

        };



//        public static class DietRecordHolder extends RecyclerView.ViewHolder{
//            private final TextView tvDate;
//            private final TextView tvDietTime;
//            private final TextView tvDietContent;
//
//            public DietRecordHolder(View itemView){
//                super(itemView);
//                tvDate = (TextView) itemView.findViewById(R.id.tvDate);
//                tvDietTime = (TextView) itemView.findViewById(R.id.tvDietTime);
//                tvDietContent = (TextView) itemView.findViewById(R.id.tvDietContent);
//
//            }
//
//            public void setValue(DietRecord dietRecord) {
//                tvDate.setText(dietRecord.date);
//                tvDietTime.setText(dietRecord.time);
//                tvDietContent.setText(dietRecord.content);
//
//            }
//
//        }
        lvRecords.setAdapter(adapter);

        //hook up Listener for grid click
        lvRecords.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //create an intent to display the article
                Intent i = new Intent(getApplicationContext(),EditActivity.class);
                //get the article to display
                DietRecord dietRecord = adapter.getItem(position);
                //pass in that article into intent
//                i.putExtra("url", article.getWebUrl());
                i.putExtra("dietRecord", dietRecord.date);
                //launch the activity
                startActivity(i);
            }
        });


        writeNewPost("1", "Bob", "2017/3/24","Steak", "www.yahoo.com","lunch");
//        updatePost("-KfeHo1Vz9KxeMbF_8E1","1", "Bob", "2017/3/29","Steak", "www.yahoo.com","lunch");

    }


    private void writeNewPost(String userId, String username, String date,String content, String url,String time) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("DietRecoreds").push().getKey();
        DietRecord post = new DietRecord(userId, username, date, content, url, time);
        Map<String, Object> postValues = post.toMap();
        mDatabase.child("DietRecoreds").child(key).setValue(postValues);
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/DietRecoreds/" + key, postValues);
//        childUpdates.put("/user-DietRecords/" + userId + "/" + key, postValues);

//        mDatabase.updateChildren(childUpdates);
//        records.add(post);
    }

    private void updatePost(String key,String userId, String username, String date,String content, String url,String time){
        DietRecord post = new DietRecord(userId, username, date, content, url, time);
        Map<String, Object> postValues = post.toMap();

//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/DietRecoreds/" + key, postValues);
//        childUpdates.put("/user-DietRecords/" + userId + "/" + key, postValues);

        mDatabase.child("DietRecoreds").child(key).setValue(postValues);
    }
}
