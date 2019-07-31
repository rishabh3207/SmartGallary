package com.rishabhrk.smartgallary;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    public  MainFragment mainFragment;
    public  FullFragment fullFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        //getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
        getWritePermission();
    }

    public boolean getWritePermission() {
        int currPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (currPermission != PackageManager.PERMISSION_GRANTED) {
            makeRequest();
            return false;
        } else {
            addHomeFragment();
            return true;
        }
    }

    private void addHomeFragment() {
        if(mainFragment==null)
        {
            mainFragment=new MainFragment();
        }
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, mainFragment).
                commit();
    }
    public void addFullFragment(ArrayList<String> mFileList,int position) {
        if(fullFragment==null)
        {
            fullFragment=new FullFragment();
            Bundle bundle=new Bundle();
            bundle.putStringArrayList("File",mFileList);
            bundle.putInt("Position",position);
            fullFragment.setArguments(bundle);
            getSupportActionBar().hide();
        }
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, fullFragment).
                commit();
    }
    private void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1022);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (!(grantResults.length == 0
                || grantResults[0] !=
                PackageManager.PERMISSION_GRANTED)) {
            addHomeFragment();
        }
        else{
            makeRequest();
        }
    }

}