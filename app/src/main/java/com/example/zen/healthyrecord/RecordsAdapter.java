package com.example.zen.healthyrecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class RecordsAdapter extends BaseAdapter {

    ArrayList<Records> records;
    Records record;
    Context context;
    String databasekey;

    public RecordsAdapter(Context context, ArrayList<Records> records)
    {
        this.context = context;
        this.records = records;

        for(int i=1; i< records.size(); i++){
            record = records.get(i);
        }

    }

    @Override
    public int getCount() {
        if(record != null){
            return records.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //set the getView to populate the data from database
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

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
