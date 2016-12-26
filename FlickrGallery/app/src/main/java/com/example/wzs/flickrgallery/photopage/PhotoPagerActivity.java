package com.example.wzs.flickrgallery.photopage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.wzs.flickrgallery.R;
import com.example.wzs.flickrgallery.data.PhotoItem;
import com.example.wzs.flickrgallery.photos.PhotosContract;
import com.example.wzs.flickrgallery.photos.PhotosPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZeshuang on 2016/12/26.
 */

public class PhotoPagerActivity extends FragmentActivity implements PhotosContract.View {
    private static final String EXTRA_PHOTO_ID = "com.example.wzs.flickrgallery.photopage.photo_id";
    private static final String EXTRA_PHOTO_LIST = "com.example.wzs.flickrgallery.photopage.photo_list";

    private ViewPager mViewPager;
    private List<PhotoItem> mList;
    private String mPhotoId;
    private PhotosPresenter mPresenter;

    public static Intent newIntent(Context context, String id) {

        Intent intent = new Intent(context, PhotoPagerActivity.class);
        intent.putExtra(EXTRA_PHOTO_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_pager);
        mPhotoId = getIntent().getStringExtra(EXTRA_PHOTO_ID);
        mList = getIntent().getParcelableArrayListExtra(EXTRA_PHOTO_LIST);
        mPresenter = new PhotosPresenter(this);
        mPresenter.start();
        initView();

    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.activity_photo_pager_view_pager);
        if (mList != null) {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getId().equals(mPhotoId)) {
                    mViewPager.setCurrentItem(i);
                    break;
                }
            }
        }
    }

    @Override
    public void showItems(List<PhotoItem> items) {
        mList = items;
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                PhotoItem item = mList.get(position);
                return PhotoPageFragment.newInstance(item);
            }

            @Override
            public int getCount() {
                if (mList != null) {
                    return mList.size();
                } else
                    return 0;
            }
        });
    }

    @Override
    public void showError() {

    }

    @Override
    public void pending() {

    }

    @Override
    public void setPresenter(PhotosContract.Presenter presenter) {

    }
}
