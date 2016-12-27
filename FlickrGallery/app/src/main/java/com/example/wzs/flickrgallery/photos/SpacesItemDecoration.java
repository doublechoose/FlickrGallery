package com.example.wzs.flickrgallery.photos;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by WangZeshuang on 2016/12/27.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public SpacesItemDecoration(int space){
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(mSpace,mSpace,0,mSpace);
        if (parent.getChildAdapterPosition(view)==0){
            outRect.top = mSpace;
        }
        super.getItemOffsets(outRect, view, parent, state);
    }
}
