package com.example.zen.healthyrecord.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zen.healthyrecord.DetailsRecordActivity;
import com.example.zen.healthyrecord.R;
import com.example.zen.healthyrecord.RecordsAdapter;
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
        adapter = getAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                Intent i = new Intent(getActivity(), DetailsRecordActivity.class);
                i.putExtra("date", adapter.getItem(position).getDate());
                i.putExtra("time", adapter.getItem(position).getTime());
                i.putExtra("type", adapter.getItem(position).getType());
                i.putExtra("memo", adapter.getItem(position).getMemo());
                i.putExtra("rating", adapter.getItem(position).getStatusRating());
                i.putExtra("calories", adapter.getItem(position)
                        .getCaloriesFormat(adapter.getItem(position).getCalories()));
                i.putExtra("quantity", adapter.getItem(position).getQuantity());
                i.putExtra("imageURL", adapter.getItem(position).getImageURL());


                getActivity().startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        records = new ArrayList<>();
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
