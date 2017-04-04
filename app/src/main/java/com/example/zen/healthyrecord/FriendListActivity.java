package com.example.zen.healthyrecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zen.healthyrecord.model.User;

import java.util.List;

public class FriendListActivity extends AppCompatActivity {
    private RecyclerView rvContacts;
    private UserAdapter mAdapter;
    private List<User> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        // Find RecyclerView and bind to adapter
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // allows for optimizations
        rvContacts.setHasFixedSize(true);

        // Define 2 column grid layout
        final GridLayoutManager layout = new GridLayoutManager(FriendListActivity.this, 2);

        // Unlike ListView, you have to explicitly give a LayoutManager to the RecyclerView to position items on the screen.
        // There are three LayoutManager provided at the moment: GridLayoutManager, StaggeredGridLayoutManager and LinearLayoutManager.
        rvContacts.setLayoutManager(layout);

        // get data
        contacts = User.getContacts();

        // Create an adapter
        mAdapter = new UserAdapter(FriendListActivity.this, contacts);

        // Bind adapter to list
        rvContacts.setAdapter(mAdapter);
        getSupportActionBar().setTitle("Friend List");
    }


}
