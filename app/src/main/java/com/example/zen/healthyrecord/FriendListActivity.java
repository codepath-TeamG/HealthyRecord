package com.example.zen.healthyrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
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

    private DrawerLayout drawerLayout;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.friendToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Friend List");

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);




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
                Toast.makeText(FriendListActivity.this, name + " was clicked!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(FriendListActivity.this, FriendsRecordActivity.class);
                i.putExtra("friendName",name);
                startActivity(i);

            }
        });



    }

    private void setupDrawerContent(NavigationView nvDrawer) {
        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        selectDrawerItem(item);
                        return true;
                    }
                }
        );
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_first_fragment:
                //you can replace the Toast message
                Intent i = new Intent(this, HomeRecordsActivity.class);
                startActivity(i);
                break;
            case R.id.nav_second_fragment:
                //you can replace the Toast message
//                Intent f = new Intent(this, FriendsRecordActivity.class);
                Intent f = new Intent(this, FriendListActivity.class);
                startActivity(f);
                startActivity(f);

                break;
            case R.id.nav_third_fragment:
                //you can replace the Toast message
                Toast.makeText(this,"press 3rd item",Toast.LENGTH_SHORT).show();
                break;
        }
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
