package com.rishabhrk.smartgallary;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.imageViewHolder> {
    private ArrayList<String> filepath;
    RecyclerClickListner clickListner;
    Integer height = getScreenHeight();
    Integer width = getScreenWidth();
    RecyclerAdapter(ArrayList<String> file,RecyclerClickListner clickListner)
    {
        this.filepath=file;
        this.clickListner=clickListner;
    }
    @NonNull
    @Override
    public imageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.album,viewGroup,false);
        return new imageViewHolder(view);
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.imageViewHolder imageViewHolder, int i) {

        Log.d("ADebugTag", "width: " + Integer.toString(width));
        Glide.with(imageViewHolder.album.getContext()).load(filepath.get(i)).override(720,720).into(imageViewHolder.album);
    }
    @Override
    public int getItemCount() {
        return filepath.size();
    }

    public class imageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView album;
        int w;
        public imageViewHolder(@NonNull View itemView) {
            super(itemView);
            album=itemView.findViewById(R.id.img_album);
            if(width>1200)
                w=width/4;
            else
                w=width/3;
            album.getLayoutParams().width=w;
            album.getLayoutParams().height=w;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            clickListner.onClick(v,getAdapterPosition());
        }
    }
}