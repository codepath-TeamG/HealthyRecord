package com.example.zen.healthyrecord.models;

/**
 * Created by joanniehuang on 2017/3/17.
 */

public class Food extends Records{

    public Food(String time,String type,String quantity,String memo,float statusRating,int calories) {
        super(time,type,quantity,memo,statusRating,calories);
        setType("Food");
    }


}
