package com.example.zen.healthyrecord.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class DietRecord {
    public static final String REF_DIETRECORDS = "DietRecords";
    public String uid;
    public String author;
    public String date;
    public String time;
    public String content;
    public String url;

    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public DietRecord() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public DietRecord(String uid, String author, String date, String content,String url,String time) {
        this.uid = uid;
        this.author = author;
        this.date = date;
        this.content = content;
        this.time = time;
        this.url = url;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("date", date);
        result.put("content", content);
        result.put("time", time);
        result.put("url",url);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }


}

