package com.lermao.lmbshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import java.util.Random;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 全局公共类, 封装：屏幕宽高获取，单位转换等
 *
 * @author WJQ
 */
public class Global {

    public static Context mContext;

    public static float mDensity;

    public static float mScreenWidth;

    public static float mScreenHeight;

    private static View mStatusBarView;

    public static void init(Context context) {
        mContext = context;
        initScreenSize();
    }


    public static <T> void startActivity(Class<T> tClass) {
        startActivity(tClass, null);
    }

    /**
     * Activity跳转
     * @param tClass
     * @param <T>
     */
    public static <T> void startActivity(Class<T> tClass, Bundle bundle) {
        Intent intent = new Intent(mContext, tClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        if (mContext.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            mContext.startActivity(intent);
        }
    }


    private static void initScreenSize() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        mDensity = dm.density;
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;
    }

    public static int dp2px(int dp) {
        return (int) (dp * mDensity);
    }

    public static int sp2px(float spValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public static View inflate(int layoutResID, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(layoutResID, parent, false);
    }

    public static View inflate(int layoutResID) {
        return inflate(layoutResID, null);
    }


    /**
     * 进入全屏
     * @param window
     */
    public static void setFullScreen(Window window) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 退出全屏
     * @param window
     * @param activity
     */
    public static void quitFullScreen(Window window, Activity activity) {
        final WindowManager.LayoutParams attrs = window.getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setAttributes(attrs);
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        Global.setStatusBarColor(activity, Color.BLACK);
    }


    public static String getString(int stringId) {
        return mContext.getResources().getString(stringId);
    }

    public static int getColor(int colorId) {
        return mContext.getResources().getColor(colorId);
    }

    /**
     * 设置全屏沉侵式效果
     */
    public static void setNoStatusBarFullMode(Activity activity) {
        // sdk 4.4
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            if (mStatusBarView != null) {
                ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
                root.removeView(mStatusBarView);
            }
            return;
        }

        // sdk 5.x
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }


    /**
     * 隐藏虚拟按键
     */
    public static void hideBottomUIMenu(Window window) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = window.getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = window.getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 设置控件的paddingTop, 使它不被StatusBar覆盖
     */
    public static void setStatusBarPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int marginTop = getStatusBarHeight(view.getContext());
            view.setPadding(view.getPaddingLeft(), marginTop,
                    view.getPaddingRight(), view.getPaddingBottom());
            return;
        }
    }


    /**
     * @param max 随机数从0开始且小于该数
     * @return 随机数
     */
    public static int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }


    public static void keyboard(boolean isHide,Activity activity){
        if(isHide){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
        }else {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }




    /**
     * 颜色16进制转int值
     *
     * @param colorStr 16进制
     * @return 10进制颜色
     */
    public static int toColorFromString(String colorStr) {
        return Color.parseColor(colorStr);
    }


    /**
     * 通过反射的方式获取状态栏高度，
     * 一般为24dp，有些可能较特殊，所以需要反射动态获取
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int id = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------无法获取到状态栏高度");
        }
        return dp2px(24);
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     * @param statusColor
     */
    public static void setStatusBarColor(Activity activity, int statusColor) {
        // sdk 5.x
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(statusColor);
            return;
        }
    }

}
