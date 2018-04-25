package com.lermao.lmbshop.presenter;

import com.lermao.lmbshop.R;
import com.lermao.lmbshop.base.BasePresenter;
import com.lermao.lmbshop.model.OnRequestDataResult;
import com.lermao.lmbshop.model.bean.TodayData;
import com.lermao.lmbshop.ui.viewInterface.HomepageInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by PENG on 2018/4/25.
 */

public class HomepagePresenter extends BasePresenter<HomepageInterface> implements OnRequestDataResult{

    private static final String TAG = "HomepagePresenter";

    public HomepagePresenter(HomepageInterface view) {
        super(view);
    }

    public void requestTodayData(){
        Flowable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                        List<TodayData.TodayDataBean> data = new ArrayList<>();
                        data.add(new TodayData.TodayDataBean("今日付款单数","100"));
                        data.add(new TodayData.TodayDataBean("今日浏览量","500"));
                        TodayData todayData = new TodayData();

                        List<String> bannerList = new ArrayList<>();
                        bannerList.add("特朗普：美国将派代表团赴华磋商贸易问题");
                        bannerList.add("英烈保护法草案拟增加条款 打击“精日分子”");
                        bannerList.add("中国2030年或实现载人登月 下一目标：送人到火星");

                        List<TodayData.ModularBean> modularBeanList = new ArrayList<>();
                        modularBeanList.add(new TodayData.ModularBean("订单管理",String.valueOf(R.mipmap.homepage_ic_order)));
                        modularBeanList.add(new TodayData.ModularBean("商品管理",String.valueOf(R.mipmap.homepage_ic_commodity)));
                        modularBeanList.add(new TodayData.ModularBean("资产管理",String.valueOf(R.mipmap.homepage_ic_assets)));
                        modularBeanList.add(new TodayData.ModularBean("短信消息",String.valueOf(R.mipmap.homepage_ic_mail)));

                        todayData.setDataBeanList(data);
                        todayData.setBannerList(bannerList);
                        todayData.setModularBeanList(modularBeanList);
                        onHttpSuccess(todayData);
                    }
                });
    }

    @Override
    public void onHttpSuccess(Object data) {
        if(!isViewAttached()){
            return;
        }
        if(data instanceof TodayData){
            getmView().get().getTodayData((TodayData) data);
        }
    }

    @Override
    public void onHttpFail(String error) {
        if(!isViewAttached()){
            return;
        }
    }
}
