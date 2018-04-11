package com.lermao.lmbshop.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.lermao.lmbshop.R;

import java.util.Locale;

/**
 * lermao-Android
 * Created by 小宇宙 on
 * Date:2018-04-11
 * Time:上午8:53
 * Copyright © 2018年 xyz.com All rights reserved.
 */

public class MultiLanguageUtil {
    private static final String TAG = "MultiLanguageUtil";
    private static MultiLanguageUtil instance;
    private Context mContext;
    public static final String SAVE_LANGUAGE = "save_language";

    public static void init(Context mContext) {
        if (instance == null) {
            synchronized (MultiLanguageUtil.class) {
                if (instance == null) {
                    instance = new MultiLanguageUtil(mContext);
                }
            }
        }
    }

    public static MultiLanguageUtil getInstance() {
        if (instance == null) {
            throw new IllegalStateException("You must be init MultiLanguageUtil first");
        }
        return instance;
    }

    private MultiLanguageUtil(Context context) {
        this.mContext = context;
    }

    public Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context);
        } else {
            return context;
        }
    }


    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResources(Context context) {
        Resources resources = context.getResources();
        Locale locale = getLanguageLocale();

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    /**
     * 设置语言
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setConfiguration() {
        Locale targetLocale = getLanguageLocale();
        SPHelper.getInstance(mContext).saveString(SPHelper.APP_HEADER_LANGUAGE, getHttpHeaderLanuage());
        Configuration configuration = mContext.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(targetLocale);
        } else {
            configuration.locale = targetLocale;
        }
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public String getHttpHeaderLanuage() {
        int languageType = SPHelper.getInstance(mContext).getInt(MultiLanguageUtil.SAVE_LANGUAGE, 0);
        if (languageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
            Locale sysType = getSysLocale();
            if (sysType.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                return "en";
            } else if (sysType.equals(Locale.TRADITIONAL_CHINESE) ||
                    (sysType.getLanguage().equals(Locale.TRADITIONAL_CHINESE.getLanguage()) && sysType.getScript().equals("Hant"))) {
                return "zh-Hant";
            } else if (TextUtils.equals(sysType.getLanguage(), Locale.CHINA.getLanguage())) { //zh
                if (TextUtils.equals(sysType.getCountry(), Locale.CHINA.getCountry())) {  //适配华为mate9  zh_CN_#Hans
                    return "zh-Hans";
                }
            } else {
                return "zh-Hans";
            }
        } else if (languageType == LanguageType.LANGUAGE_EN) {
            return "en";
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            return "zh-Hans";
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
            return "zh-Hant";
        }
        return "zh-Hans";
    }

    /**
     * 如果不是英文、简体中文、繁体中文，默认返回简体中文
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Locale getLanguageLocale() {
        int languageType = SPHelper.getInstance(mContext).getInt(MultiLanguageUtil.SAVE_LANGUAGE, 0);
        if (languageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
            Locale sysType = getSysLocale();
            if (sysType.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                return Locale.ENGLISH;
            } else if (sysType.equals(Locale.TRADITIONAL_CHINESE) ||
                    (sysType.getLanguage().equals(Locale.TRADITIONAL_CHINESE.getLanguage()) && sysType.getScript().equals("Hant"))) {
                return Locale.TRADITIONAL_CHINESE;
            } else if (TextUtils.equals(sysType.getLanguage(), Locale.CHINA.getLanguage())) { //zh
                if (TextUtils.equals(sysType.getCountry(), Locale.CHINA.getCountry())) {  //适配华为mate9  zh_CN_#Hans
                    return Locale.SIMPLIFIED_CHINESE;
                }
            } else {
                return Locale.SIMPLIFIED_CHINESE;
            }
        } else if (languageType == LanguageType.LANGUAGE_EN) {
            return Locale.ENGLISH;
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            return Locale.SIMPLIFIED_CHINESE;
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
            return Locale.TRADITIONAL_CHINESE;
        }
        Log.e(TAG, "getLanguageLocale" + languageType + languageType);
        getSystemLanguage(getSysLocale());
        return Locale.ENGLISH;
    }

    private String getSystemLanguage(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();

    }

    //7.0以上获取方式需要特殊处理一下
    public Locale getSysLocale() {
        if (Build.VERSION.SDK_INT < 24) {
            return mContext.getResources().getConfiguration().locale;
        } else {
            return mContext.getResources().getConfiguration().getLocales().get(0);
        }
    }

    /**
     * 更新语言
     *
     * @param languageType 通过getTypeFromStr(String selectedLanguage)
     *                     函数获取
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void updateLanguage(int languageType) {
        SPHelper.getInstance(mContext).saveInt(MultiLanguageUtil.SAVE_LANGUAGE, languageType);
        SPHelper.getInstance(mContext).saveString(SPHelper.APP_HEADER_LANGUAGE, getHttpHeaderLanuage());
        MultiLanguageUtil.getInstance().setConfiguration();
    }

    public int getTypeFromStr(String selectedLanguage) {
        if (selectedLanguage.equals(mContext.getString(R.string.lanuage_english))) {
            return LanguageType.LANGUAGE_EN;
        } else if (selectedLanguage.equals(mContext.getString(R.string.lanuage_chinese))) {
            return LanguageType.LANGUAGE_CHINESE_SIMPLIFIED;
        } else if (selectedLanguage.equals(mContext.getString(R.string.lanuage_fanti))) {
            return LanguageType.LANGUAGE_CHINESE_TRADITIONAL;
        }
        return LanguageType.LANGUAGE_FOLLOW_SYSTEM;
    }

    public String getLanguageName(Context context) {
        int languageType = SPHelper.getInstance(mContext).getInt(MultiLanguageUtil.SAVE_LANGUAGE, LanguageType.LANGUAGE_FOLLOW_SYSTEM);
        if (languageType == LanguageType.LANGUAGE_EN) {
            return mContext.getString(R.string.lanuage_english);
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            return mContext.getString(R.string.lanuage_chinese);
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
            return mContext.getString(R.string.lanuage_fanti);
        }
        return mContext.getString(R.string.lanuage_auto);
    }

    /**
     * 获取到用户保存的语言类型
     *
     * @return
     */
    public int getLanguageType() {
        int languageType = SPHelper.getInstance(mContext).getInt(MultiLanguageUtil.SAVE_LANGUAGE, LanguageType.LANGUAGE_FOLLOW_SYSTEM);
        if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            return LanguageType.LANGUAGE_CHINESE_SIMPLIFIED;
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
            return LanguageType.LANGUAGE_CHINESE_TRADITIONAL;
        } else if (languageType == LanguageType.LANGUAGE_EN) {
            return LanguageType.LANGUAGE_EN;
        }
        Log.e(TAG, "getLanguageType" + languageType);
        return languageType;
    }

    public class LanguageType {
        public static final int LANGUAGE_FOLLOW_SYSTEM = 0;
        public static final int LANGUAGE_CHINESE_SIMPLIFIED = 1;
        public static final int LANGUAGE_CHINESE_TRADITIONAL = 2;
        public static final int LANGUAGE_EN = 3;
    }
}
