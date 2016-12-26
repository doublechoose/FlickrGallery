package com.example.wzs.flickrgallery.photopage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wzs.flickrgallery.R;
import com.example.wzs.flickrgallery.data.PhotoItem;
import com.example.wzs.flickrgallery.util.ImageLoader;


/**
 * Created by WangZeshuang on 2016/12/26.
 */

public class PhotoPageFragment extends Fragment {
    private static final String ARG_PHOTO_ITEM = "photo_item";
    private PhotoItem mPhotoItem;
    private ImageView mImageView;
    private TextView mTvCaption;

    public static Fragment newInstance(PhotoItem item) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PHOTO_ITEM,item);

        PhotoPageFragment fragment = new PhotoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoItem = getArguments().getParcelable(ARG_PHOTO_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_pager, container, false);
        mImageView = (ImageView) view.findViewById(R.id.iv_photo_item);
        mTvCaption = (TextView) view.findViewById(R.id.tv_caption);
        if (mPhotoItem!=null){
            ImageLoader.loadingImage(getActivity(),mImageView,mPhotoItem.getUrl());
            mTvCaption.setText(mPhotoItem.getCaption());
        }
        return view;
    }
}
