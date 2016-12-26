package com.example.wzs.flickrgallery.photos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wzs.flickrgallery.R;
import com.example.wzs.flickrgallery.data.PhotoItem;
import com.example.wzs.flickrgallery.photopage.PhotoPagerActivity;
import com.example.wzs.flickrgallery.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZeshuang on 2016/12/22.
 */

public class PhotosFragment extends Fragment implements PhotosContract.View {
    private static final String TAG = "PhotosFragment";
    private RecyclerView mRecyclerView;
    private ArrayList<PhotoItem> mItems = new ArrayList<>();
    private PhotosPresenter mPresenter;
    private PhotoAdapter mPhotoAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }

    @Override
    public void setPresenter(@NonNull PhotosContract.Presenter presenter) {
        mPresenter = (PhotosPresenter) presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_photos_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        setUpAdapter();
        return view;
    }

    private void setUpAdapter() {
        mPhotoAdapter = new PhotoAdapter(mItems);
        if (isAdded()) {
            mRecyclerView.setAdapter(mPhotoAdapter);
        }
    }


    @Override
    public void showItems(List<PhotoItem> items) {
        if (items!=null && items.size()>0){
            mPhotoAdapter.replaceData(items);
        }

    }

    @Override
    public void showError() {

    }

    @Override
    public void pending() {

    }


    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        private List<PhotoItem> mItems;

        public PhotoAdapter(List<PhotoItem> items) {
            mItems = items;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.photos_item, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {
            if (mItems!=null&&mItems.size()>0){
                PhotoItem item = mItems.get(position);
                holder.bindPhotoItem(item);
            }
        }

        @Override
        public int getItemCount() {
            if (mItems!=null&&mItems.size()>0){
                return mItems.size();
            }else
                return 0;
        }

        public void replaceData(List<PhotoItem> items){
            setItems(items);
        }
        private void setItems(List<PhotoItem> items) {
            mItems = items;
            notifyDataSetChanged();
        }
    }

    private class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mIvPhoto;
        private PhotoItem mPhotoItem;

        public PhotoHolder(View itemView) {
            super(itemView);
            mIvPhoto = (ImageView) itemView.findViewById(R.id.iv_photo_item);
        }

        //绑定holder显示图片
        public void bindPhotoItem(PhotoItem item) {
            ImageLoader.loadingImage(getActivity(),mIvPhoto,item.getUrl());
            mPhotoItem = item;
            mIvPhoto.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            getActivity().startActivity(PhotoPagerActivity.newIntent(getActivity(),mPhotoItem.getId()));
        }
    }

}
