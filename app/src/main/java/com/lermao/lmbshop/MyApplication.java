package com.lermao.lmbshop;

import android.app.Application;

import com.lermao.lmbshop.utils.MultiLanguageUtil;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * lermao-Android
 * Created by 小宇宙 on
 * Date:2018-03-31
 * Time:下午9:59
 * Copyright © 2018年 xyz.com All rights reserved.
 */

public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        CrashReport.initCrashReport(getApplicationContext(), "8a21464fb2", false);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        initLanguage();
    }

    /**
     * 初始化多语言工具类
     */
    private void initLanguage(){
        //语言配置需要优先初始化
        MultiLanguageUtil.init(application);
        MultiLanguageUtil.getInstance().setConfiguration();
    }
}
