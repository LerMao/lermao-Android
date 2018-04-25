package com.lermao.lmbshop.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.lermao.lmbshop.base.BaseAdapterRV;
import com.lermao.lmbshop.base.BaseHolderRV;
import com.lermao.lmbshop.model.bean.TodayData;
import com.lermao.lmbshop.ui.hodler.HomepageModularHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */

public class HomepageModularAdapter extends BaseAdapterRV<TodayData.ModularBean> {

    public HomepageModularAdapter(Context context, List<TodayData.ModularBean> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV<TodayData.ModularBean> createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new HomepageModularHolder(context, parent, this, viewType);
    }
}
