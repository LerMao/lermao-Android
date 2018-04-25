package com.lermao.lmbshop.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.lermao.lmbshop.base.BaseAdapterRV;
import com.lermao.lmbshop.base.BaseHolderRV;
import com.lermao.lmbshop.model.bean.TodayData;
import com.lermao.lmbshop.ui.hodler.HomepageTodayDataHolder;

import java.util.List;

/**
 * Created by PENG on 2018/4/25.
 */

public class HomepageTodayDataAdapter extends BaseAdapterRV<TodayData.TodayDataBean> {

    public HomepageTodayDataAdapter(Context context, List<TodayData.TodayDataBean> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV<TodayData.TodayDataBean> createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new HomepageTodayDataHolder(context,parent,this,viewType);
    }
}
