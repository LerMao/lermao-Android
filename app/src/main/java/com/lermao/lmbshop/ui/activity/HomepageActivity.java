package com.lermao.lmbshop.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.lermao.lmbshop.R;
import com.lermao.lmbshop.base.BaseActivity;
import com.lermao.lmbshop.base.Global;
import com.lermao.lmbshop.model.bean.TodayData;
import com.lermao.lmbshop.presenter.HomepagePresenter;
import com.lermao.lmbshop.ui.adapter.HomepageModularAdapter;
import com.lermao.lmbshop.ui.adapter.HomepageTodayDataAdapter;
import com.lermao.lmbshop.ui.view.UIDecoration;
import com.lermao.lmbshop.ui.viewInterface.HomepageInterface;
import com.newrelic.agent.android.NewRelic;
import com.oneapm.agent.android.OneApmAgent;
import com.umeng.analytics.MobclickAgent;

import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomepageActivity extends BaseActivity implements HomepageInterface {

    @BindView(R.id.rvTodayData)
    RecyclerView rvTodayData;
    @BindView(R.id.vf)
    ViewFlipper viewFlipper;
    @BindView(R.id.rvModular)
    RecyclerView rvModular;
    @BindView(R.id.tvTodayMoney)
    TextView tvTodayMoney;
    @BindView(R.id.ivSettings)
    ImageView ivSettings;
    private HomepageTodayDataAdapter todayDataAdapter;
    private HomepagePresenter presenter;


    @Override
    public int getLayoutRes() {
        return R.layout.homepage_activity;
    }

    @Override
    public void initView() {
        init();
        ButterKnife.bind(this);
        Global.setStatusBarColor(this, Global.getColor(R.color.homepage_statusBar_bg_color));
        presenter = new HomepagePresenter(this);
        initRecyclerTodayData();
    }

    private void initRecyclerTodayData() {
        todayDataAdapter = new HomepageTodayDataAdapter(getApplicationContext(), null);
        rvTodayData.setAdapter(todayDataAdapter);
        rvTodayData.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rvTodayData.addItemDecoration(new UIDecoration(UIDecoration.MIDDLE_MIDDLE_HORIZONTAL, Global.getColor(R.color.white)));
        presenter.requestTodayData();
    }

    private void initRecyclerModular(List<TodayData.ModularBean> modularBeanList) {
        HomepageModularAdapter modularAdapter = new HomepageModularAdapter(this, modularBeanList);
        rvModular.setAdapter(modularAdapter);
        rvModular.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rvModular.addItemDecoration(new UIDecoration(UIDecoration.CROSS, Global.getColor(R.color.homepage_rv_modular_line_color)));
    }

    private void initViewFlipper(List<String> bannerList) {
        viewFlipper.removeAllViews();
        ListIterator<String> stringListIterator = bannerList.listIterator();
        int color = Global.getColor(R.color.homepage_viewflipper_text_color);
        while (stringListIterator.hasNext()) {
            String item = stringListIterator.next();
            TextView textView = (TextView) Global.inflate(R.layout.simple_list_item_1);
            textView.setText(item);
            textView.setTextColor(color);
            viewFlipper.addView(textView);
        }
        viewFlipper.startFlipping();
    }

    @Override
    public void initListener() {
        ivSettings.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ivSettings){
            Global.startActivity(SettingsActivity.class);
        }
    }


    private void init() {
        OneApmAgent.init(this.getApplicationContext()).setToken("4A6ACDCE70D98931FD385E0B6B58708F34").start();
        NewRelic.withApplicationToken(
                "AA0bf8eebbd32ed6764ef75773c45b6518216cd986"
        ).start(this.getApplication());
        MobclickAgent.onEvent(HomepageActivity.this, "EvenLogin");
    }

    @Override
    public void onLoadBefore() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String errorMsg) {

    }

    @Override
    public void getTodayData(TodayData todayData) {
        todayDataAdapter.setDatas(todayData.getDataBeanList());
        initViewFlipper(todayData.getBannerList());
        initRecyclerModular(todayData.getModularBeanList());
        tvTodayMoney.setText(todayData.getMoney());
    }


    public HomepagePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
