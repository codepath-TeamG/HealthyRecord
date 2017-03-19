package com.example.zen.healthyrecord.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zen.healthyrecord.R;
import com.example.zen.healthyrecord.RecordsAdapter;
import com.example.zen.healthyrecord.models.Food;
import com.example.zen.healthyrecord.models.Records;

import java.util.ArrayList;


public class ItemFragment extends Fragment {

    private ArrayList<Records> records;
    private RecordsAdapter adapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, parent, false);
        listView = (ListView) view.findViewById(R.id.rcListView);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        records = new ArrayList<>();
        records.add(new Food("Lunch", "The Diner", "1"
                , "the food was pretty great with my families", (float)4.5, 2504));

        adapter = new RecordsAdapter(getActivity(), records);
    }

    public ListView getListView() {
        return listView;
    }

    public ArrayList<Records> getmRecords() {
        return records;
    }

    public RecordsAdapter getAdapter() {
        return adapter;
    }
}
