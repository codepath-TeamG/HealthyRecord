package com.example.zen.healthyrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.friendToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Friend List");




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


        mAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String name = contacts.get(position).getName();
                String mail = contacts.get(position).getmEmail();
                Toast.makeText(FriendListActivity.this, name + " was clicked!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(FriendListActivity.this, FriendsRecordActivity.class);
                i.putExtra("friendName",name);
                i.putExtra("friendMail",mail);
                startActivity(i);

            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend, menu);
        return true;
    }

    public void turnBackHome(MenuItem item) {
        Intent intent = new Intent(this, HomeRecordsActivity.class);
        startActivity(intent);

    }



}
