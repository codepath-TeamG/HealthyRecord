package com.example.zen.healthyrecord.fragments;

import android.os.Bundle;
import android.widget.ListView;

import com.example.zen.healthyrecord.models.Food;

/**
 * Created by joanniehuang on 2017/3/19.
 */

public class FoodFragment extends ItemFragment {
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = super.getListView();
        populateRecords();


    }

    private void populateRecords(){
        Food food =  new Food("Lunch", "The Diner", "1"
                , "the food was pretty great with my families", (float)4.5, 2504);
        super.getmRecords().add(food);
    }
}
