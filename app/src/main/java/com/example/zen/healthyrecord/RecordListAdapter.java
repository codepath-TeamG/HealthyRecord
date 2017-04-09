package com.example.zen.healthyrecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zen.healthyrecord.model.DietRecord;

import java.util.List;

public class RecordListAdapter extends ArrayAdapter<DietRecord>{

    public RecordListAdapter(Context context,List<DietRecord> dietRecords){
        super(context, android.R.layout.simple_list_item_1,dietRecords);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the item for position
        DietRecord dietRecord = this.getItem(position);

        //check to see if existing view being reused
        //not using a recycled view -> inflate the layout
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.record_item,parent,false);
        }

        //find the image view
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivDietPhoto);

        //clear out recycled image from convertView form last time
        imageView.setImageResource(0);

        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvDietTime = (TextView) convertView.findViewById(R.id.tvDietTime);
        TextView tvDietContent = (TextView) convertView.findViewById(R.id.tvDietContent);

        tvDate.setText(dietRecord.date);
        tvDietTime.setText(dietRecord.type);
        tvDietContent.setText(dietRecord.content);

        //populate the thumbnail image
        //remote download the image in the background

//        String url = dietRecord.url;
//        if (!TextUtils.isEmpty(url)){
//            Picasso.with(getContext()).load(url).into(imageView);
//        }
        return convertView;

    }
}


