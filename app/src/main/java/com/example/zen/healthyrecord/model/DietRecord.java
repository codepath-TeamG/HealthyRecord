package com.example.zen.healthyrecord.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class DietRecord implements Serializable {
    public static final String REF_DIETRECORDS = "DietRecords";
    public String uid;
    public String author;
    public String date;
    public String time;
    public String content;
    public String url;
    public String calories;
    public String memo;
    public Float status;

    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public DietRecord() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public DietRecord(String uid, String author, String date, String time, String content,String url,String calories,String memo,Float status) {
        this.uid = uid;
        this.author = author;
        this.date = date;
        this.time = time;
        this.content = content;
        this.url = url;
        this.calories = calories;
        this.memo = memo;
        this.status = status;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("date", date);
        result.put("time", time);
        result.put("content", content);
        result.put("url",url);
        result.put("calories", calories);
        result.put("memo",memo);
        result.put("status", status);

        return result;
    }


}

