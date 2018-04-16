package com.lermao.lmbshop.utils.dialog;

/**
 * Created by ff on 2018/4/16.
 */

public class DialogUtil {

    private static class SimpleDialogFragmentHolder {
        private static final SimpleDialog instance = new SimpleDialog();
    }

    public static SimpleDialog getSimpleDialogInstance() {
        return SimpleDialogFragmentHolder.instance;
    }

    private static class MultiStyleDialoggFragmentHolder {
        private static final MultiStyleDialog instance = new MultiStyleDialog();
    }

    public static MultiStyleDialog getMultiStyleDialogInstance() {
        return MultiStyleDialoggFragmentHolder.instance;
    }
}
