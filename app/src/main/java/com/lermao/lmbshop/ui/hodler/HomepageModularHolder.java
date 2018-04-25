package com.lermao.lmbshop.ui.hodler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lermao.lmbshop.R;
import com.lermao.lmbshop.base.BaseAdapterRV;
import com.lermao.lmbshop.base.BaseHolderRV;
import com.lermao.lmbshop.model.bean.TodayData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/25.
 */

public class HomepageModularHolder extends BaseHolderRV<TodayData.ModularBean>{

    @BindView(R.id.tvModular)
    TextView tvModular;
    @BindView(R.id.ivModular)
    ImageView ivModular;

    public HomepageModularHolder(Context context, ViewGroup parent, BaseAdapterRV<TodayData.ModularBean> adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.homepage_recycle_item_modular);
    }

    @Override
    public void onFindViews(View itemView) {
        ButterKnife.bind(this, itemView);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        int a = adapter.getListData().size()/2;
        params.height = parent.getHeight()/a;
    }

    @Override
    protected void onRefreshView(TodayData.ModularBean bean, int position) {
        ivModular.setImageResource(bean.getId());
        tvModular.setText(bean.getTitle());
    }
}
