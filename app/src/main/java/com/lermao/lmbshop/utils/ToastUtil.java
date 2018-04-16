package com.lermao.lmbshop.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lermao.lmbshop.R;


/**
 * Created by PENG on 2018/4/16.
 */

public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context, String message) {
        checkToastInstance(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(getView(context, message));
        toast.show();
    }

    public static void showLongToast(Context context, String message) {
        checkToastInstance(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(getView(context, message));
        toast.show();
    }


    public static View getView(Context context, String message) {
        TextView textView = new TextView(context.getApplicationContext());
        textView.setPadding(25, 15, 25, 15);
        textView.setText(message);
        textView.setBackgroundResource(R.drawable.toast_default_bg);
        return textView;
    }

    public static void showView(Context context, View view) {
        checkToastInstance(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private static void checkToastInstance(Context context) {
        if (toast == null) {
            toast = new Toast(context.getApplicationContext());
            Log.d("ToastUtil", "初始化Toast实例");
        }
    }
}
