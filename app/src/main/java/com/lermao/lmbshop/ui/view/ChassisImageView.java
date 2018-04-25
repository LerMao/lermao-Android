package com.lermao.lmbshop.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lermao.lmbshop.R;

/**
 * Created by PENG on 2018/4/25.
 */

public class ChassisImageView extends View {

    private Bitmap bitmap;
    private Paint paint;
    private int chassisColor;
    private int strokeWidth;
    private boolean INIT_FLAG = false;

    public ChassisImageView(Context context) {
        super(context);
        initPaint();
    }


    public ChassisImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChassisImageView);
        chassisColor = typedArray.getColor(R.styleable.ChassisImageView_chassis_color, Color.BLUE);
        int id = typedArray.getResourceId(R.styleable.ChassisImageView_src, 0);
        if(id != 0){
            bitmap = BitmapFactory.decodeResource(context.getResources(),id);
        }
        initPaint();
    }

    public void setBitmap(Bitmap bitmap, int chassisColor) {
        this.bitmap = bitmap;
        this.chassisColor = chassisColor;
        invalidate();
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate();
    }

    private void initPaint() {
        if (INIT_FLAG) {
            return;
        }
        strokeWidth = 5;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setColor(chassisColor);
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paint.setColor(Color.BLUE);
        int x = getMeasuredWidth() + getPaddingLeft() - getPaddingRight();
        int y = getMeasuredHeight() + getPaddingTop() - getPaddingBottom();
        int radius = Math.min(x, y) / 2 - strokeWidth * 2;
        canvas.drawCircle(x / 2, y / 2, radius, paint);


        int xScale = getMeasuredWidth() / 4;
        int yScale = getMeasuredHeight() / 4;

        int left = getMeasuredWidth() / 2 - xScale;
        int top = getHeight() / 2 - yScale;
        int right = getMeasuredWidth() / 2 + xScale;
        int bottom = getHeight() / 2 + yScale;
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawBitmap(bitmap, null, rect, null);

    }
}
