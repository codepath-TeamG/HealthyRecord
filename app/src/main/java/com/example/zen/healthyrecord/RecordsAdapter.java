package com.example.zen.healthyrecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zen.healthyrecord.models.Records;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by joanniehuang on 2017/3/17.
 */

public class RecordsAdapter extends ArrayAdapter<Records> {

    private ArrayList<Records> records;
    private Context context;
    private String databasekey;

    public RecordsAdapter(Context context, ArrayList<Records> records)
    {
        super(context,R.layout.list_item_records,records);
        this.context = context;
        this.records = records;

    }

    @Override
    public int getCount() {
        return records.size();
    }

    public Records getItem(int position){
        if(records.get(position).getType() == "Food"){
            return records.get(position);
        }

        return records.get(position);
    }


    //set the getView to populate the data from database
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Records record = records.get(position);


        Calendar calendar = Calendar.getInstance();

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_records, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvMemo = (TextView) convertView.findViewById(R.id.tvMemo);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvCalories = (TextView) convertView.findViewById(R.id.tvCalories);
        RatingBar status = (RatingBar) convertView.findViewById(R.id.statusBar);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivIcon);


        tvTitle.setText(record.getType());
        tvMemo.setText(record.getMemo());
        tvDate.setText(record.getCalendar(calendar));
        tvCalories.setText(record.getCalFormat(record.getCalories()));
        status.setRating(record.getStatusRating());
        Picasso.with(context).load(databasekey).into(imageView);


        return convertView;
    }



}
