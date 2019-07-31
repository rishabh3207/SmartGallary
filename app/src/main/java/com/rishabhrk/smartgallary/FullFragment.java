package com.rishabhrk.smartgallary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FullFragment extends Fragment {
    ViewPager viewPager;
    ArrayList<String> mFileList;
    int position;
    public FullFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
            position = getArguments().getInt("Position");
            mFileList=getArguments().getStringArrayList("File");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_full, container, false);
        viewPager=(ViewPager) view.findViewById(R.id.pager);
        ImageFragmentAdapter imageFragmentAdapter=new ImageFragmentAdapter(getActivity().getApplicationContext(),mFileList);
        viewPager.setAdapter(imageFragmentAdapter);
        viewPager.setCurrentItem(position,true);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        return view;
    }
}
