package com.lermao.lmbshop.model;

/**
 * lermao-Android
 * Created by 小宇宙 on
 * Date:2018-04-12
 * Time:上午8:46
 * Copyright © 2018年 xyz.com All rights reserved.
 */

public interface Callback {

    /**
     * 成功
     */
    void onSuccess();

    /**
     * 失败
     *
     * @param errorMsg 失败信息
     */
    void onFailure(String errorMsg);
}
