package com.example.wzs.flickrgallery.data;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by WangZeshuang on 2016/12/27.
 */

public class PhotoLab {
    private static PhotoLab sPhotoLab;
    private ArrayList<PhotoItem> mItems;

    public PhotoLab(Context context) {
        mItems = new ArrayList<>();
    }

    public static PhotoLab get(Context context) {
        if (sPhotoLab == null) {
            sPhotoLab = new PhotoLab(context);
        }
        return sPhotoLab;
    }

    public ArrayList<PhotoItem> getPhotoItems() {
        return mItems;
    }

    public void setPhotoItems(ArrayList<PhotoItem> items){
        mItems = items;
    }

    public PhotoItem getPhoto(String id) {
        for (PhotoItem item : mItems) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

}
