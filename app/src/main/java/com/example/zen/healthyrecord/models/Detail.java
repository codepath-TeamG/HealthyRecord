package com.example.zen.healthyrecord.models;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zen.healthyrecord.DetailsRecordActivity;
import com.example.zen.healthyrecord.R;
import com.example.zen.healthyrecord.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharonyu on 2017/4/10.
 */

public class Detail {


    public String getTvLabel() {
        return tvLabel;
    }

    public String getTvValue() {
        return tvValue;
    }

    private String tvLabel;
    private String  tvValue;
    private String  dataValue;

    public int getIconDrawable() {
        return iconDrawable;
    }

    int iconDrawable;

    public Detail(String label, String value, int drawable) {
        tvLabel = label;
        tvValue = value;
        iconDrawable = drawable;
    }

    public String getDataValue() {
        return dataValue;
    }



    private static int lastLabelId = 0;
    private static int lastValueId = 0;

//    public static ArrayList<Detail> createDetailList(int numContacts) {
//        ArrayList<Detail> details = new ArrayList<Detail>();
//
//        for (int i = 1; i <= numContacts; i++) {
//            details.add(new Detail("Person " + ++lastLabelId ,"value" + ++lastValueId));
//        }
//
//        return details;
//
//    }

    public static List<Detail> getDetails(String date, String type, String content, String calories, String memo, String status) {
        List<Detail> details = new ArrayList<>();
        details.add(new Detail("DATE",date,R.drawable.ic_event_black_24dp));
        details.add(new Detail("TYPE",type,R.drawable.ic_food));
        details.add(new Detail("CONTENT",content,R.drawable.ic_restaurant_black_24dp));
        details.add(new Detail("CALORIES",calories,R.drawable.ic_calories));
        details.add(new Detail("MEMO",memo,R.drawable.ic_memo));
        details.add(new Detail("STATUS",status,R.drawable.ic_status));
        return details;
    }

    public static List<Detail> getDetailsExercise(String date, String type, String content, String calories, String memo, String status) {
        List<Detail> details = new ArrayList<>();
        details.add(new Detail("DATE",date,R.drawable.ic_event_black_24dp));
        details.add(new Detail("TYPE",type,R.drawable.ic_sport_type));
        details.add(new Detail("CONTENT",content,R.drawable.ic_sport));
        details.add(new Detail("CALORIES",calories,R.drawable.ic_calories));
        details.add(new Detail("MEMO",memo,R.drawable.ic_memo));
        details.add(new Detail("STATUS",status,R.drawable.ic_status));
        return details;
    }


}
