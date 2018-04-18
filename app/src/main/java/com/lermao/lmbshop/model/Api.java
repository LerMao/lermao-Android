package com.lermao.lmbshop.model;

import com.lermao.lmbshop.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PENG on 2018/4/9.
 */

public class Api {
    public ApiService service;

    public Api() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance() {
        return ApiHolder.instance;
    }

    private static class ApiHolder{
        private static final Api instance = new Api();
    }

//    public Flowable<SplashBean> getSplashBean(String url) {
//        return service.getSplashBean(url);
//    }
}
