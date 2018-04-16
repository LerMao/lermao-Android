package com.lermao.lmbshop.utils.dialog;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.lermao.lmbshop.R;

import java.util.List;

/**
 * Created by PENG on 2018/4/14.
 */

public class MultiStyleDialog extends BaseDialogFragment implements View.OnClickListener {

    private static final String TAG = "MultiStyleDialog";

    private int STYLE_FLAG = 0;

    private String FALG = "STYLE_FLAG";


    private static final int STYLE_CUSTOM = 0;
    private static final int STYLE_LIST = 1;
    private static final int STYLE_PICTURE = 2;
    private static final int STYLE_LOAD = 3;


    public void showList(DialogListDataBen listDataBen, FragmentManager manager, String tag) {
        STYLE_FLAG = STYLE_LIST;
        DialogProxy.getInstance().putWeak(STYLE_LIST, listDataBen);
        show(manager, tag);
    }

    public void showPicture(Bitmap pictureBitmap, FragmentManager manager, String tag) {
        STYLE_FLAG = STYLE_PICTURE;
        DialogProxy.getInstance().putWeak(STYLE_PICTURE, getRoundedCornerBitmap(pictureBitmap));
        show(manager, tag);
    }

    public void showLoad(FragmentManager manager, String tag) {
        STYLE_FLAG = STYLE_LOAD;
        show(manager, tag);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            STYLE_FLAG = savedInstanceState.getInt(FALG);
            Log.d(TAG, "STYLE_FLAG：" + STYLE_FLAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        switch (STYLE_FLAG) {
            case STYLE_CUSTOM:
                break;
            case STYLE_LIST:
                return createList();
            case STYLE_PICTURE:
                return createPicture(inflater);
            case STYLE_LOAD:
                return createLoad();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private View createLoad() {
        ProgressBar progressBar = new ProgressBar(getContext().getApplicationContext());
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return progressBar;
    }

    /**
     * 创建图片Diloag
     *
     * @param inflater
     * @return
     */
    private View createPicture(LayoutInflater inflater) {
        Bitmap pictureBitmap = (Bitmap) DialogProxy.getInstance().getWeak(STYLE_PICTURE);

        if (pictureBitmap == null) {
            Log.d(TAG, "pictureBitmap null");
            return null;
        }

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.dialog_picture, null);
        ImageView ivDel = linearLayout.findViewById(R.id.ivDel);
        ivDel.setOnClickListener(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1);
        if (pictureBitmap != null) {
            ImageView ivContent = new ImageView(getContext().getApplicationContext());
            ivContent.setImageBitmap(pictureBitmap);
            linearLayout.addView(ivContent, 0, params);
        }

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return linearLayout;
    }

    /**
     * 创建ListViewDialog
     *
     * @return
     */
    private ListView createList() {
        DialogListDataBen listDataBen = (DialogListDataBen) DialogProxy.getInstance().getWeak(STYLE_LIST);

        if (listDataBen == null) {
            Log.d(TAG, "listDataBen null");
            return null;
        }

        List<String> dataList = listDataBen.getDatas();
        if (dataList == null || dataList.size() == 0) {
            Log.i(TAG, "createList -- dataList Null");
        }
        ListView listView = new ListView(getActivity().getApplicationContext());
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.dialog_list_item,
                R.id.tvContent,
                dataList);
        listView.setAdapter(arrayAdapter);
        listView.setDivider(new ColorDrawable(Color.GRAY));
        listView.setDividerHeight(1);
        listView.setOnItemClickListener(listDataBen.getOnItemClickListener());
        listView.setOnItemLongClickListener(listDataBen.getOnItemLongClickListener());
        return listView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FALG, STYLE_FLAG);
    }

    /**
     * Bitmap圆角处理
     *
     * @param bitmap
     * @return
     */
    public Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 12;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            switch (STYLE_FLAG) {
                case STYLE_LIST:
                    width = (int) (dm.widthPixels * 0.5);
                    break;
                case STYLE_PICTURE:
                    width = (int) (dm.widthPixels * 0.75);
                    height = (int) (dm.heightPixels * 0.7);
                    break;

            }
            dialog.getWindow().setLayout(width, height);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDel:
                dismiss();
        }
    }
}
