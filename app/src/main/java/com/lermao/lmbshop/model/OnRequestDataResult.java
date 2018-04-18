package com.lermao.lmbshop.model;



/**
 * Created by PENG on 2017/11/29.
 */

public interface OnRequestDataResult<V> {
    void onHttpSuccess(V data);
    void onHttpFail(String error);
}
