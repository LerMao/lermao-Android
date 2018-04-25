package com.lermao.lmbshop.ui.hodler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lermao.lmbshop.R;
import com.lermao.lmbshop.base.BaseAdapterRV;
import com.lermao.lmbshop.base.BaseHolderRV;
import com.lermao.lmbshop.model.bean.TodayData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PENG on 2018/4/25.
 */

public class HomepageTodayDataHolder extends BaseHolderRV<TodayData.TodayDataBean> {

    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvValue)
    TextView tvValue;

    public HomepageTodayDataHolder(Context context, ViewGroup parent, BaseAdapterRV<TodayData.TodayDataBean> adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.homepage_recycle_item_todaydata);
    }

    @Override
    public void onFindViews(View itemView) {
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void onRefreshView(TodayData.TodayDataBean bean, int position) {
        tvType.setText(bean.getType());
        tvValue.setText(bean.getValue());
    }
}
