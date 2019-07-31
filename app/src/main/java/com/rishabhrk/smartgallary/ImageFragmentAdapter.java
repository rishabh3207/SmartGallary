package com.rishabhrk.smartgallary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class ImageFragmentAdapter extends PagerAdapter {

    ArrayList<String> file;
    Context context;
    LayoutInflater inflater;

    public ImageFragmentAdapter(Context context,ArrayList<String> file)
    {
        this.context=context;
        this.file=file;
    }
    @Override
    public int getCount() {
        return file.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.fragment_image,null);
        PhotoView imageView=(PhotoView) v.findViewById(R.id.imageView);
        Uri uri=Uri.parse(file.get(position));
        //imageView.setImageURI(uri);
        Glide.with(this.context).load(uri).into(imageView);
        ViewPager vp=(ViewPager)container;
        vp.addView(v,0);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        ViewPager viewPager=(ViewPager)container;
        View v=(View)object;
        viewPager.removeView(v);
    }
}
