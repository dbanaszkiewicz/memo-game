package com.example.damian.memo_game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.RequestOptions;
import com.example.damian.memo_game.model.PhotoModel;

import java.util.List;

public class PhotoGalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<PhotoModel> data;

    public PhotoGalleryAdapter(Context context, List<PhotoModel> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.photo_list_item, parent, false);
        viewHolder = new MyItemHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Glide.with(context).load(data.get(position).getUrl())
                .apply(new RequestOptions().override(120, 120).centerCrop())
        //                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(((MyItemHolder) holder).mImg);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder {
        ImageView mImg;


        public MyItemHolder(View itemView) {
            super(itemView);

            mImg = itemView.findViewById(R.id.item_img);
        }

    }


}