package com.lermao.lmbshop.utils.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lermao.lmbshop.R;


/**
 * Created by PENG on 2018/4/13.
 */


public class SimpleDialog extends BaseDialogFragment implements View.OnClickListener {

    private String title;
    private String message;
    private String determine;
    private String cancel;
    private SimpleDialogOnClickListener onDetermineListener;
    private SimpleDialogOnClickListener onCancelListener;
    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnShowListener onShowListener;
    private Integer titleTextSize;
    private Integer titleTextColor;
    private Integer messageTextSize;
    private Integer messageTextColor;
    private Integer determineTextSize;
    private Integer determineTextColor;
    private Integer cancelTextSize;
    private Integer cancelTextColor;
    private boolean cancelable;
    private FragmentManager manager;
    private String tag;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            create();
        }
    }

    private SimpleDialog create() {
        Builder builder = DialogProxy.getInstance().getBuilder();
        title = builder.title;
        message = builder.message;
        determine = builder.determine;
        cancel = builder.cancel;
        onDetermineListener = builder.onDetermineListener;
        onCancelListener = builder.onCancelListener;
        onDismissListener = builder.onDismissListener;
        onShowListener = builder.onShowListener;
        titleTextColor = builder.titleTextColor;
        titleTextSize = builder.titleTextSize;
        messageTextSize = builder.messageTextSize;
        messageTextColor = builder.messageTextColor;
        determineTextColor = builder.determineTextColor;
        determineTextSize = builder.determineTextSize;
        cancelTextColor = builder.cancelTextColor;
        cancelTextSize = builder.cancelTextSize;
        cancelable = builder.cancelable;
        this.manager = builder.manager;
        this.tag = builder.tag;
        return this;
    }

    public void showDialog() {
        show(manager, tag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.dialog_simple, null);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        TextView tvCancel = view.findViewById(R.id.tvCancel);
        TextView tvDetermine = view.findViewById(R.id.tvDetermine);


        if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(message) || !TextUtils.isEmpty(determine) || !TextUtils.isEmpty(cancel)) {
            setTextViewAttribute(tvTitle, title, titleTextColor, titleTextSize, null);
            setTextViewAttribute(tvMessage, message, messageTextColor, messageTextSize, null);
            setTextViewAttribute(tvDetermine, determine, determineTextColor, determineTextSize, onDetermineListener);
            setTextViewAttribute(tvCancel, cancel, cancelTextColor, cancelTextSize, onCancelListener);
        }


        setCancelable(cancelable);
        return view;
    }


    private void setTextViewAttribute(final TextView textView, String content, Integer textColor, Integer textSize, final SimpleDialogOnClickListener onClickListener) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setText(content);
        if (textColor != null && textColor != 0) {
            textView.setTextColor(textColor);
        }
        if (textSize != null && textSize != 0) {
            textView.setTextSize(textSize);
        }
        if (textView.getId() == R.id.tvCancel || textView.getId() == R.id.tvDetermine) {
            if (onClickListener != null) {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClick(SimpleDialog.this, textView);
                    }
                });
            } else {
                textView.setOnClickListener(this);
            }
        }
    }


    @Override
    public void onClick(View v) {
        dismiss();
    }


    public static final class Builder {
        private String title;
        private int titleTextSize;
        private int titleTextColor;
        private String message;
        private int messageTextSize;
        private int messageTextColor;
        private String determine;
        private int determineTextSize;
        private int determineTextColor;
        private String cancel;
        private int cancelTextSize;
        private int cancelTextColor;
        private SimpleDialogOnClickListener onDetermineListener;
        private SimpleDialogOnClickListener onCancelListener;
        private DialogInterface.OnDismissListener onDismissListener;
        private DialogInterface.OnShowListener onShowListener;
        private boolean cancelable = true;
        private FragmentManager manager;
        private String tag;

        public Builder(FragmentManager manager, String tag) {
            this.manager = manager;
            this.tag = tag;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setTitle(String val, int titleTextSize, int titleTextColor) {
            title = val;
            this.titleTextSize = titleTextSize;
            this.titleTextColor = titleTextColor;
            return setTitle(val);
        }

        public Builder setTitle(String val) {
            title = val;
            return this;
        }

        public Builder setMessage(String val, int messageTextSize, int messageTextColor) {
            this.messageTextSize = messageTextSize;
            this.messageTextColor = messageTextColor;
            return setMessage(val);
        }

        public Builder setMessage(String val) {
            message = val;
            return this;
        }

        public Builder setDetermine(String val) {
            determine = val;
            return this;
        }

        public Builder setDetermine(String val, int determineTextSize, int determineTextColor) {
            this.determineTextSize = determineTextSize;
            this.determineTextColor = determineTextColor;
            return setDetermine(val);
        }

        public Builder setCancel(String val) {
            cancel = val;
            return this;
        }

        public Builder setCancel(String val, int cancelTextSize, int cancelTextColor) {
            this.cancelTextColor = cancelTextColor;
            this.cancelTextSize = cancelTextSize;
            return setCancel(val);
        }

        public Builder setOnDetermineListener(SimpleDialogOnClickListener val) {
            onDetermineListener = val;
            return this;
        }

        public Builder setOnCancelListener(SimpleDialogOnClickListener val) {
            onCancelListener = val;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener val) {
            onDismissListener = val;
            return this;
        }

        public Builder setOnShowListener(DialogInterface.OnShowListener val) {
            onShowListener = val;
            return this;
        }

        public SimpleDialog build() {
            DialogProxy.getInstance().setBuilder(this);
            return DialogUtil.getSimpleDialogInstance().create();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
