package com.lermao.lmbshop.ui.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by PENG on 2018/3/2.
 */

public class UIDecoration extends RecyclerView.ItemDecoration {


    private int dividerWidth;
    private Paint paint;

    private int STYLE_FLAG;

    public UIDecoration(int STYLE_FLAG, int color) {
        init(color);
        this.STYLE_FLAG = STYLE_FLAG;
    }

    private void init(int color) {
        paint = new Paint();
        paint.setStrokeWidth(dividerWidth);
        paint.setColor(color);
        paint.setAntiAlias(true);
        dividerWidth = 5;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, dividerWidth);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        switch (STYLE_FLAG) {
            case 1:
                MiddleMiddle(c, parent);
                break;
            case 2:
                for (int i = 0; i < parent.getChildCount(); i++) {
                    View childView = parent.getChildAt(i);
                    c.drawLine(0, childView.getY(), childView.getX() + childView.getWidth(), childView.getY(), paint);
                }
                break;
        }


    }

    private void MiddleMiddle(Canvas c, RecyclerView parent) {
        if (parent.getChildCount() <= 1) {
            return;
        }
        for (int i = 1; i < parent.getChildCount(); i++) {
            View childView = parent.getChildAt(i);
            int margin = (int) (childView.getY() / 10);
            c.drawLine(childView.getX(), childView.getY() + margin, childView.getX(), childView.getY() + childView.getHeight() - margin, paint);
        }
    }
}
