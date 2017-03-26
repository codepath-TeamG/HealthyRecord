package com.example.zen.healthyrecord.models;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by joanniehuang on 2017/3/19.
 */

public abstract class Records {
    private String date;
    private String time;
    private String type;
    private String quantity;
    private String memo;
    private float statusRating;
    private int calories;
    private String imageURL;

    public Records(String date, String time, String type, String quantity, String memo, float statusRating, int calories, String imageURL) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.quantity = quantity;
        this.memo = memo;
        this.statusRating = statusRating;
        this.calories = calories;
        this.imageURL = imageURL;
    }

    public String getCaloriesFormat(int caloriesInt){
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(caloriesInt);

    }

    public String getCalendar(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(calendar.getTime());
    }


    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getMemo() {
        return memo;
    }

    public float getStatusRating() {
        return statusRating;
    }

    public int getCalories() {
        return calories;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageURL() {
        return imageURL;
    }
}
