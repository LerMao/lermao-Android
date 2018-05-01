package com.lermao.lmbshop.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.lermao.lmbshop.base.BaseAdapterRV;
import com.lermao.lmbshop.base.BaseHolderRV;
import com.lermao.lmbshop.ui.hodler.SettingsHolder;

import java.util.List;

/**
 * Created by PENG on 2018/5/1.
 */

public class SettingsAdapter extends BaseAdapterRV {

    public SettingsAdapter(Context context, List listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new SettingsHolder(context,parent,this,viewType);
    }
}
