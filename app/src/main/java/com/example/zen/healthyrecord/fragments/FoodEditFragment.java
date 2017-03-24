package com.example.zen.healthyrecord.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zen.healthyrecord.R;

/**
 * Created by sharonyu on 2017/3/23.
 */

public class FoodEditFragment extends FragmentAddItemPage {

    public FoodEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_item_page, container, false);

        return v;
    }
}
