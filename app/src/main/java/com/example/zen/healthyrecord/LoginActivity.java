package com.example.zen.healthyrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.DatabaseReference;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private DatabaseReference mDatabase;
    private TextView testName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fabric.with(this, new Crashlytics());

        testName = (TextView) findViewById(R.id.testName);


        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello");
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        writeNewUser("1","Bob","bob@gmail.com");
//        writeNewUser("2","Ted","ted@gmail.com");
//        writeNewPost("1", "Bob", "2017/3/17","Steak", "www.yahoo.com","lunch");
//        mDatabase.child("users").child("1").addValueEventListener(userListener);



//        writeNewPost("1","Bob","3/17","breakfast");
//        deletePost("KfHfeEkwn0ZJ8-Wxk8U","1");
//        mDatabase.child("user-DietRecords").removeValue();



        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }

//    private void writeNewUser(String userId, String name, String email) {
//        User user = new User(name, email);
//
//        mDatabase.child("users").child(userId).setValue(user);
//    }
//
//    ValueEventListener userListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            // Get Post object and use the values to update the UI
//            User user = dataSnapshot.getValue(User.class);
//            testName.setText(user.username);
//            // ...
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            // Getting Post failed, log a message
//            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//            // ...
//        }
//    };
//
//    private void writeNewPost(String userId, String username, String date,String content, String url,String time) {
//        // Create new post at /user-posts/$userid/$postid and at
//        // /posts/$postid simultaneously
//        String key = mDatabase.child("DietRecoreds").push().getKey();
//        DietRecord post = new DietRecord(userId, username, date,content, url,time);
//        Map<String, Object> postValues = post.toMap();
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/DietRecoreds/" + key, postValues);
//        childUpdates.put("/user-DietRecords/" + userId + "/" + key, postValues);
//
//        mDatabase.updateChildren(childUpdates);
//    }

    public void onLogin(View v){
        //create an intent to display the article
        Intent i = new Intent(getApplicationContext(),FirebaseTestActivity.class);
        i.putExtra("mode", 2); // pass arbitrary data to launched activity
        startActivity(i);//launch the activity
    }



}
