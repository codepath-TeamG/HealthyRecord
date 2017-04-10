package com.example.zen.healthyrecord;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zen.healthyrecord.models.Detail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sharonyu on 2017/4/10.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder>  {

    private Activity mContext;
    private List<Detail> details;

    public DetailsAdapter(Activity context, List<Detail> detail) {
        mContext = context;
        if (detail == null) {
            throw new IllegalArgumentException("contacts must not be null");
        }
        details = detail;
    }

    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new ViewHolder(itemView, mContext);

    }

    @Override
    public void onBindViewHolder(DetailsAdapter.ViewHolder holder, int position) {
        Detail detail = details.get(position);
        TextView tvLabel = holder.tvLabel;
        tvLabel.setText(detail.getTvLabel());
        TextView tvValue = holder.tvValue;
        tvValue.setText(detail.getTvValue());
        Picasso.with(mContext).load(detail.getIconDrawable()).fit().centerCrop().into(holder.ivPhoto);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType( position );
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId( position );
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView ivPhoto;
        final TextView tvLabel;
        final TextView tvValue;


        public ViewHolder(final View itemView, final Context context) {
            super(itemView);
            ivPhoto = (ImageView)itemView.findViewById(R.id.ivDetailIcon);
            tvLabel = (TextView)itemView.findViewById(R.id.tvLabel);
            tvValue = (TextView)itemView.findViewById(R.id.tvValue);

        }


    }
}
