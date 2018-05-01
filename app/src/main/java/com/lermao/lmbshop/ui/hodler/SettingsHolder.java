package com.lermao.lmbshop.ui.hodler;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lermao.lmbshop.R;
import com.lermao.lmbshop.base.BaseAdapterRV;
import com.lermao.lmbshop.base.BaseHolderRV;
import com.lermao.lmbshop.base.Global;
import com.lermao.lmbshop.ui.activity.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PENG on 2018/5/1.
 */

public class SettingsHolder extends BaseHolderRV<String> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvSettings)
    TextView tvSettings;

    public SettingsHolder(Context context, ViewGroup parent, BaseAdapterRV<String> adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.settings_recycle_item);
    }

    @Override
    public void onFindViews(View itemView) {
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void onRefreshView(String bean, int position) {
        tvTitle.setText(bean);
        String setting = SettingsActivity.getSettingsValue(bean);
        if(!TextUtils.isEmpty(setting)){
            int color = 0;
            if(setting.equals("已开启")){
                color = Global.getColor(R.color.settings_recycler_item_select_text_color);
            }else {
                color = Global.getColor(R.color.settings_recycler_item_noteselect_text_color);
            }
            tvSettings.setText(setting);
            tvSettings.setTextColor(color);
            tvSettings.setVisibility(View.VISIBLE);
        }
    }
}
