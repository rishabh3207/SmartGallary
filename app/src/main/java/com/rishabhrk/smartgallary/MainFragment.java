package com.rishabhrk.smartgallary;


import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public MainFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    public ArrayList<String> mFileList;
    RecyclerAdapter adapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        // Inflate the layout for this fragment
        //mainUtil= (MainUtil) getActivity();
        //mainUtil.getWritePermission();
        display();
        return view;
    }

    void readFiles() {
        mFileList = new ArrayList<>();
        //    mNameList=new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        ContentResolver cr = getActivity().getContentResolver();
        Cursor cursor = cr.query(
                uri,
                new String[]{MediaStore.Images.Media.DATA},
                null,
                null,
                MediaStore.Images.Media.DEFAULT_SORT_ORDER);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                // String fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                if (filePath != null) {
                    mFileList.add(filePath);
                    //    mNameList.add(fileName);
                }
            }
            cursor.close();
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    public void display()
    {
        Integer width = getScreenWidth();
        int sc=3;
        recyclerView = view.findViewById(R.id.recycler);
        readFiles();
        // Integer[] paths={R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic6,R.drawable.pic7,R.drawable.pic5,R.drawable.pic6,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic6,R.drawable.pic7,R.drawable.pic5,R.drawable.pic6,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic6,R.drawable.pic7,R.drawable.pic5,R.drawable.pic6,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic6,R.drawable.pic7,R.drawable.pic5,R.drawable.pic6};
        if(width>1200)
            sc=4;
        else
            sc=3;
        layoutManager = new GridLayoutManager(this.getContext(), sc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerClickListner listner=new RecyclerClickListner()
        {
            @Override
            public void onClick(View view, int position) {
                ((MainActivity )getActivity()).addFullFragment(mFileList,position);
            }
        };
        adapter = new RecyclerAdapter(mFileList,listner);
        recyclerView.setAdapter(adapter);

    }

}
