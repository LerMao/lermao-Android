package com.lermao.lmbshop.ui.viewInterface;

import com.lermao.lmbshop.model.bean.TodayData;

/**
 * Created by PENG on 2018/4/25.
 */

public interface HomepageInterface extends OnLoadSettings{
    void getTodayData(TodayData todayData);
}
