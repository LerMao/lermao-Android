package com.lermao.lmbshop.model;


import io.reactivex.Flowable;

/**
 * Created by PENG on 2018/4/11.
 */

public interface NetworkMatchingFactory {

     <T> void execute(Flowable<T> flowable, OnRequestDataResult result);
}
