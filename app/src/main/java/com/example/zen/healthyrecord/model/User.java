package com.example.zen.healthyrecord.model;

/**
 * Created by Zen on 2017/3/15.
 */

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.zen.healthyrecord.R;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Container class to hold Detail information.
public class User implements Serializable {
    private String mName;
    private int mThumbnailDrawable;
    private String mNumber;
    public String mUid;
    public String mEmail;
    public Map<String, Boolean> stars = new HashMap<>();

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public User(String name, int thumbnailDrawable, String number,String uid,String email) {
        mName = name;
        mThumbnailDrawable = thumbnailDrawable;
        mNumber = number;
        mUid = uid;
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public int getThumbnailDrawable() {
        return mThumbnailDrawable;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getmUid() {
        return mUid;
    }

    public String getmEmail() {
        return mEmail;
    }

    // Returns a list of contacts
    public static List<User> getContacts() {
        List<User> contacts = new ArrayList<>();
        contacts.add(new User("Adam", R.drawable.uphoto5, "4153508881","1","adam@gmail.com"));
        contacts.add(new User("Sarah", R.drawable.uphoto2, "4153508882","2","sarah@gmail.com"));
        contacts.add(new User("Bob", R.drawable.uphoto3, "4153508883","3","bob@gmail.com"));
        contacts.add(new User("John", R.drawable.uphoto4, "4153508884","4","joho@gmail.com"));
        return contacts;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", mUid);
        result.put("name", mName);
        result.put("email", mEmail);
        result.put("number", mNumber);
        result.put("photo", mThumbnailDrawable );

        return result;
    }
}

