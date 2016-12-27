package com.example.wzs.flickrgallery.photos;


import com.example.wzs.flickrgallery.data.PhotoItem;
import com.example.wzs.flickrgallery.util.FlickFetchr;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WangZeshuang on 2016/12/22.
 */

public class PhotosPresenter implements PhotosContract.Presenter {

    private PhotosContract.View mView;
    private Subscription mSubscription;

    public PhotosPresenter(PhotosContract.View view){
        mView = view;
        mView.setPresenter(this);
    }
    @Override
    public void loadPhotoItems() {
        mView.pending();
        //获得数据
        mSubscription = Observable.create(new Observable.OnSubscribe<ArrayList<PhotoItem>>() {
            @Override
            public void call(Subscriber<? super ArrayList<PhotoItem>> subscriber) {
                try{
                    ArrayList<PhotoItem> items =  new FlickFetchr().fetchItems();
                    subscriber.onNext(items);
                }catch (Exception e){
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<PhotoItem>>() {
                    @Override
                    public void onCompleted() {
                        mView.cleanPending();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(ArrayList<PhotoItem> photoItems) {
                        //显示数据
                        mView.cleanPending();
                        mView.showItems(photoItems);
                    }
                });
    }

    @Override
    public void start() {
        loadPhotoItems();
    }
}
