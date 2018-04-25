package com.lermao.lmbshop.model.bean;

import java.util.List;

/**
 * Created by PENG on 2018/4/25.
 */

public class TodayData {

    private List<TodayDataBean> dataBeanList;

    private List<String> bannerList;

    private List<ModularBean> modularBeanList;

    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<ModularBean> getModularBeanList() {
        return modularBeanList;
    }

    public void setModularBeanList(List<ModularBean> modularBeanList) {
        this.modularBeanList = modularBeanList;
    }

    public List<String> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<String> bannerList) {
        this.bannerList = bannerList;
    }

    public List<TodayDataBean> getDataBeanList() {
        return dataBeanList;
    }


    public void setDataBeanList(List<TodayDataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    public static class TodayDataBean{
        private String type;
        private String value;

        public TodayDataBean(String type, String value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "TodayData{" +
                    "type='" + type + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class ModularBean{
        private String title;
        private int id;

        public ModularBean(String title, int id) {
            this.title = title;
            this.id = id;
        }

        public ModularBean() {
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "ModularBean{" +
                    "title='" + title + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
