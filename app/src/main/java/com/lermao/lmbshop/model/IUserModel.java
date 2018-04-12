package com.lermao.lmbshop.model;

/**
 * lermao-Android
 * Created by 小宇宙 on
 * Date:2018-04-12
 * Time:上午8:45
 * Copyright © 2018年 xyz.com All rights reserved.
 */

public interface IUserModel {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param callback 回调
     */
    void login(String username, String password, Callback callback);
}
