package com.example.wzs.flickrgallery.photos;

import com.example.wzs.flickrgallery.BasePresenter;
import com.example.wzs.flickrgallery.BaseView;
import com.example.wzs.flickrgallery.data.PhotoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZeshuang on 2016/12/22.
 */

public interface PhotosContract {
    interface View extends BaseView<Presenter> {

        void showItems(ArrayList<PhotoItem> items);

        void showError();

        void pending();

        void cleanPending();
    }

    interface Presenter extends BasePresenter {

        void loadPhotoItems();
    }
}
