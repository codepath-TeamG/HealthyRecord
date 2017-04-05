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

// Container class to hold Contact information.
public class Friend implements Serializable {
    private String mName;
    private int mThumbnailDrawable;
    private String mNumber;
    public String mUid;
    public String mEmail;
    public Map<String, Boolean> stars = new HashMap<>();

    public Friend() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Friend(String name, int thumbnailDrawable, String number,String uid,String email) {
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
    public static List<Friend> getContacts() {
        List<Friend> contacts = new ArrayList<>();
        contacts.add(new Friend("Adam", R.drawable.uphoto, "4153508881","1","adom@gmail.com"));
        contacts.add(new Friend("Sarah", R.drawable.uphoto, "4153508882","2","sarah@gmail.com"));
        contacts.add(new Friend("Bob", R.drawable.uphoto, "4153508883","3","bob@gmail.com"));
        contacts.add(new Friend("John", R.drawable.uphoto, "4153508884","4","joho@gmail.com"));
        return contacts;
    }

    // Returns a random contact
    public static Friend getRandomContact(Context context) {

        Resources resources = context.getResources();

        TypedArray contactNames = resources.obtainTypedArray(R.array.contact_names);
        int name = (int) (Math.random() * contactNames.length());

        TypedArray contactThumbnails = resources.obtainTypedArray(R.array.contact_thumbnails);
        int thumbnail = (int) (Math.random() * contactThumbnails.length());

        TypedArray contactNumbers = resources.obtainTypedArray(R.array.contact_numbers);
        int number = (int) (Math.random() * contactNumbers.length());

        TypedArray contactIDs = resources.obtainTypedArray(R.array.contact_uids);
        int ID = (int) (Math.random() * contactIDs.length());

        TypedArray contactEmails = resources.obtainTypedArray(R.array.contact_emails);
        int email = (int) (Math.random() * contactEmails.length());

        return new Friend(contactNames.getString(name), contactThumbnails.getResourceId(thumbnail, R.drawable.uphoto),
                contactNumbers.getString(number), contactIDs.getString(ID), contactEmails.getString(email));
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
