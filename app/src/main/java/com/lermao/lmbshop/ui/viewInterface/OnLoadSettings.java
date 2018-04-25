package com.lermao.lmbshop.ui.viewInterface;



public interface OnLoadSettings {

    /**
     * 加载前的操作
     */
    void onLoadBefore();

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
