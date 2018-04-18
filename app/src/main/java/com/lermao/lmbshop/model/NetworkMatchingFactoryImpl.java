package com.lermao.lmbshop.model;

import com.lermao.lmbshop.utils.LogUtil;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by PENG on 2018/4/11.
 */

public class NetworkMatchingFactoryImpl implements NetworkMatchingFactory {

    private static final String TAG = "NetworkMatchingFactoryImpl";

    @Override
    public <T> void execute(final Flowable<T> flowable, final OnRequestDataResult result) {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        LogUtil.d(TAG, "网络访问成功");
                        result.onHttpSuccess(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        result.onHttpFail("网络访问错误");
                        LogUtil.d(TAG, throwable.toString());
                    }
                });

    }
}
