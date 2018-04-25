package com.lermao.lmbshop.base;

import android.widget.ImageView;

import com.lermao.lmbshop.R;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;



/**
 * Created by PENG on 2018/4/9.
 */

public abstract class BasePresenter<T> {
    protected Reference<T> mView;

    public void loadPicture(ImageView imageView,String url){
        GlideApp.with(Global.mContext)
                .asBitmap()
                .load(url)
                .fitCenter()
                .error(R.drawable.image_fail)
                .load(R.drawable.image_load)
                .into(imageView);
    }

    public void loadPicture(ImageView imageView,Integer url){
        GlideApp.with(Global.mContext)
                .asBitmap()
                .load(url)
                .fitCenter()
                .error(R.drawable.image_fail)
                .load(R.drawable.image_load)
                .into(imageView);
    }


    public BasePresenter(T view) {
        mView = new WeakReference<T>(view);
    }

    public Reference<T> getmView() {
        return mView;
    }

    public boolean isViewAttached() {
        return mView != null && mView.get() != null;
    }

    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}
