package com.example.zen.healthyrecord.models;

import java.util.ArrayList;

/**
 * Created by joanniehuang on 2017/3/17.
 */

public class Food extends Records{

    static ArrayList results;

    public Food(String time,String type,String quantity,String memo,float statusRating,int calories) {
        super(time,type,quantity,memo,statusRating,calories);
        setType("Food");
    }


    //arrayList
    /*
    public static ArrayList<Food> FoodArray(Array array){
        results = new ArrayList<>();

        for(int i = 0; i< array.getLength(array); i++){
            results.add(new Food());
        }

        return results;
    }*/

    //handle the date to get the relative time showing
    /*
    public String getRelativeTimeAgo(String rawDate){
        String format = "";

    }*/

    //calories formatting with thousands decimals

}
