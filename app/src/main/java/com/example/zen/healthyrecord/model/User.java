package com.example.zen.healthyrecord.model;

/**
 * Created by Zen on 2017/3/15.
 */

import java.io.Serializable;
import java.util.List;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.zen.healthyrecord.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Container class to hold Contact information.
public class User implements Serializable {
    private String mName;
    private int mThumbnailDrawable;
    private String mNumber;

    public User(String name, int thumbnailDrawable, String number) {
        mName = name;
        mThumbnailDrawable = thumbnailDrawable;
        mNumber = number;
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

    // Returns a list of contacts
    public static List<User> getContacts() {
        List<User> contacts = new ArrayList<>();
        contacts.add(new User("Adam", R.drawable.uphoto, "4153508881"));
        contacts.add(new User("Sarah", R.drawable.uphoto, "4153508882"));
        contacts.add(new User("Bob", R.drawable.uphoto, "4153508883"));
        contacts.add(new User("John", R.drawable.uphoto, "4153508884"));
        return contacts;
    }

    // Returns a random contact
    public static User getRandomContact(Context context) {

        Resources resources = context.getResources();

        TypedArray contactNames = resources.obtainTypedArray(R.array.contact_names);
        int name = (int) (Math.random() * contactNames.length());

        TypedArray contactThumbnails = resources.obtainTypedArray(R.array.contact_thumbnails);
        int thumbnail = (int) (Math.random() * contactThumbnails.length());

        TypedArray contactNumbers = resources.obtainTypedArray(R.array.contact_numbers);
        int number = (int) (Math.random() * contactNumbers.length());

        return new User(contactNames.getString(name), contactThumbnails.getResourceId(thumbnail, R.drawable.uphoto), contactNumbers.getString(number));
    }
}

