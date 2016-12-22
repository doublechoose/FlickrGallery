package com.example.wzs.flickrgallery.photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.wzs.flickrgallery.R;
import com.example.wzs.flickrgallery.util.ActivityUtils;

public class PhotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        PhotosFragment fragment = (PhotosFragment) fm
                .findFragmentById(R.id.fragment_container);

        if (fragment== null){
            fragment = PhotosFragment.newInstance();
            ActivityUtils.addFragmentToActivity(fm,fragment,R.id.fragment_container);
        }
        new PhotosPresenter(fragment);
    }

}
