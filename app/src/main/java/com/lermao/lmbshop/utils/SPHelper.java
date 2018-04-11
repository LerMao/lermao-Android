package com.lermao.lmbshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * lermao-Android
 * Created by 小宇宙 on
 * Date:2018-04-11
 * Time:上午8:57
 * Copyright © 2018年 xyz.com All rights reserved.
 */

public class SPHelper {
    public static final String APP_PREFERENCE = "lermaobs_reference";
    public static final String APP_HEADER_LANGUAGE = "app_header_language";


    private static SPHelper mInstance;
    private SharedPreferences mPreference;
    private Context mContext;

    private SPHelper(Context context) {
        this.mContext = context;
    }

    public void setmPreference(SharedPreferences mPreference) {
        this.mPreference = mPreference;
    }

    public static SPHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SPHelper(context);
            mInstance.setmPreference(context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE));
        }
        return mInstance;
    }

    public boolean saveString(String key, String value) {
        if (mPreference != null) {
            SharedPreferences.Editor editor = mPreference.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }

    public String getString(String key, String defValue) {
        return mPreference.getString(key, defValue);
    }

    public void clearSingle(String key) {
        mPreference.edit().remove(key).commit();
    }

    public boolean saveBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean getBoolean(String key, Boolean defValue) {
        return mPreference.getBoolean(key, defValue);
    }

    public boolean getBoolean(String key) {
        return mPreference.getBoolean(key, false);
    }

    public boolean saveInt(String key, int value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public int getInt(String key, int def) {
        return mPreference.getInt(key, def);
    }

    public boolean saveLong(String key, long value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public long getLong(String key) {
        return mPreference.getLong(key, -1);
    }

    public long getLong(String key, long defValue) {
        return mPreference.getLong(key, defValue);
    }

}
