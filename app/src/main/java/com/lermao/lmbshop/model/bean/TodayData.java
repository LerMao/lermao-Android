package com.lermao.lmbshop.model.bean;

import java.util.List;

/**
 * Created by PENG on 2018/4/25.
 */

public class TodayData {

    private List<TodayDataBean> dataBeanList;

    private List<String> bannerList;

    private List<ModularBean> modularBeanList;


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
        private String imageUrl;

        public ModularBean() {
        }

        public ModularBean(String title, String imageUrl) {
            this.title = title;
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        public String toString() {
            return "ModularBean{" +
                    "title='" + title + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    '}';
        }
    }
}
