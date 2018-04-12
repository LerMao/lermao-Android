package com.lermao.lmbshop;

/**
 * lermao-Android
 * Created by 小宇宙 on
 * Date:2018-04-12
 * Time:上午8:42
 * Copyright © 2018年 xyz.com All rights reserved.
 */

public class ModelManager {
    public static IUserModel provideUserModel() {
        return new UserModel();
    }
}
