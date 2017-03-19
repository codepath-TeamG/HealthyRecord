package com.example.zen.healthyrecord.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zen.healthyrecord.R;
import com.example.zen.healthyrecord.RecordsAdapter;
import com.example.zen.healthyrecord.models.Records;

import java.util.ArrayList;


public class ItemFragment extends Fragment {

    private ArrayList<Records> mRecords;
    private RecordsAdapter adapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, parent, false);
        listView = (ListView) view.findViewById(R.id.rcListView);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecords = new ArrayList<Records>();
        adapter = new RecordsAdapter(getActivity(), mRecords);
    }

    public ListView getListView() {
        return listView;
    }

    public ArrayList<Records> getmRecords() {
        return mRecords;
    }

    public RecordsAdapter getAdapter() {
        return adapter;
    }
}
