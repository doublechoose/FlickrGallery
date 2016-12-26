package com.example.wzs.flickrgallery.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wzs.flickrgallery.R;

/**
 * Created by WangZeshuang on 2016/12/26.
 */

public class ImageLoader {

    public static void loadingImage(Context context, ImageView imageView,String url){
        Glide.with(context).load(url)
                .placeholder(R.drawable.bill_up_close)
                .into(imageView);
    }
}
