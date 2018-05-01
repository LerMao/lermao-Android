package com.lermao.lmbshop.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lermao.lmbshop.R;
import com.lermao.lmbshop.base.BaseActivity;
import com.lermao.lmbshop.base.Global;
import com.lermao.lmbshop.ui.adapter.SettingsAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {


    @BindView(R.id.ivLeftArrow)
    ImageView ivLeftArrow;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlSettings)
    RecyclerView rlSettings;


    private static Map<String,String> settingsMap = new HashMap<>();

    public static void setSettingsVaule(String key,String instance){
        settingsMap.put(key,instance);
    }

    public static String getSettingsValue(String key){
        return settingsMap.get(key);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.settings_activity;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initTitle();
        initRecycler();

    }

    private void initRecycler() {
        List<String> settingList = Arrays.asList( getResources().getStringArray(R.array.settings_recycle_titles));
        setSettingsVaule(settingList.get(1),"已开启");
        setSettingsVaule(settingList.get(2),"未开启");

        SettingsAdapter adapter = new SettingsAdapter(this,settingList);
        rlSettings.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rlSettings.setAdapter(adapter);
    }

    private void initTitle() {
        String title = getResources().getString(R.string.settings_title);
        tvTitle.setText(title);
        tvTitle.setTextColor(Global.getColor(R.color.settings_title_text_color));
    }

    @Override
    public void initListener() {
        ivLeftArrow.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivLeftArrow) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
