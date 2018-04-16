package com.lermao.lmbshop.utils.dialog;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by PENG on 2018/4/13.
 * 用于存储Dialog数据
 * 防止DialogFragment重建Dialog数据丢失
 */

public class DialogProxy {

    private static class ProxyBuilderHolder {
        private static final DialogProxy instance = new DialogProxy();
    }

    public static DialogProxy getInstance() {
        return DialogProxy.ProxyBuilderHolder.instance;
    }

    private Reference<SimpleDialog.Builder> builderReference;

    public void setBuilder(SimpleDialog.Builder builder) {
        builderReference = new WeakReference<>(builder);
    }

    public SimpleDialog.Builder getBuilder() {
        if (builderReference != null) {
            return builderReference.get();
        }
        return null;
    }

    private Map<Integer, Object> weakHashMap = new WeakHashMap<>();

    public void putWeak(int key, Object data) {
        weakHashMap.put(new Integer(key), data);
    }

    public Object getWeak(Integer key) {
        return weakHashMap.get(key);
    }

}
