package com.example.zen.healthyrecord;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zen.healthyrecord.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sharonyu on 2017/4/3.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.VH> {
    private Activity mContext;
    private List<User> mContacts;

    public UserAdapter(Activity context, List<User> contacts) {
        mContext = context;
        if (contacts == null) {
            throw new IllegalArgumentException("contacts must not be null");
        }
        mContacts = contacts;
    }

    // Inflate the view based on the viewType provided.
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new VH(itemView, mContext);
    }

    // Display data at the specified position
    @Override
    public void onBindViewHolder(VH holder, int position) {
        User contact = mContacts.get(position);
        holder.rootView.setTag(contact);
        holder.tvName.setText(contact.getName());
        Picasso.with(mContext).load(contact.getThumbnailDrawable()).fit().centerCrop().into(holder.ivProfile);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a reference to the views for each contact item
    public final static class VH extends RecyclerView.ViewHolder {
        final View rootView;
        final ImageView ivProfile;
        final TextView tvName;
        final View vPalette;

        public VH(View itemView, final Context context) {
            super(itemView);
            rootView = itemView;
            ivProfile = (ImageView)itemView.findViewById(R.id.ivProfile);
            tvName = (TextView)itemView.findViewById(R.id.userName);
            vPalette = itemView.findViewById(R.id.vPalette);

            // Navigate to contact details activity on click of card view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final User contact = (User)v.getTag();
                    if (contact != null) {
                        // Fire an intent when a contact is selected
                        // Pass contact object in the bundle and populate details activity.
                    }
                }
            });
        }
    }
}
