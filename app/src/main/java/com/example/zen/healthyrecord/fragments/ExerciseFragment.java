package com.example.zen.healthyrecord.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.example.zen.healthyrecord.models.Exercise;

/**
 * Created by joanniehuang on 2017/3/19.
 */

public class ExerciseFragment extends ItemFragment{
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = super.getListView();
//        populateRecords();
    }

    private void populateRecords(){
        Exercise exercise =  new Exercise("Sport", "Running", "1"
                , "Running along the riverside", (float)3.0, 2000);
        super.getmRecords().add(exercise);
    }

}
