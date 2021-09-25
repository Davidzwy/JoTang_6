package com.example.jotang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.RotateAnimation;

import java.util.Random;

public class MyCircleView  extends View  {
    private String mStr = "";
    private String[] contents = new String[]{"微积分", "线性代数", "C语言", "概率论", "计组", "软工"};
    public int[] colors = new int[]{Color.parseColor("#8EE5EE"),
            Color.parseColor("#FFD700"), Color.parseColor("#FFD39B"),
            Color.parseColor("#FF8247"), Color.parseColor("#FF34B3"),
            Color.parseColor("#F0E68C")};
    private int mWidth;
    private Paint mPaint;
    private Context mContext;
    private float startjs = 0f;


    public int getmWidth() {
        return mWidth;
    }

    public MyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mContext = context;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);
        //设置边缘锯齿
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);
        RectF rectF = new RectF(0, 0, mWidth, mWidth);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < colors.length; i++) {
            //给扇形填充颜色
            mPaint.setColor(colors[i]);
            int startjd = i * 60;
            //参数的意思  画扇形    开始的角度    结束的角度     是否有中心    画笔
            canvas.drawArc(rectF, startjd, 60, true, mPaint);
        }
        //字体颜色
        mPaint.setColor(Color.BLACK);
        //字体大小
        mPaint.setTextSize(48);
        //进行循环
        for (int i = 0; i < contents.length; i++) {
            int startjd = i * 60;
            //设定文字的路径
            Path path = new Path();
            path.addArc(rectF, startjd, 60);
            canvas.drawTextOnPath(contents[i], path, 50, 50, mPaint);
        }
        //开始按钮
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(mWidth / 2, mWidth / 2, 30, mPaint);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(40);
        //要得到我们写的字的高和宽  开始按钮那个
        Rect rect = new Rect();
        mPaint.getTextBounds(mStr, 0, mStr.length(), rect);
        int width = rect.width();
        int height = rect.height();
        canvas.drawText(mStr, mWidth / 2 - width / 2, mWidth / 2 + height / 2, mPaint);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(500, 500);
        //得到测量过后的高和宽
        mWidth = getMeasuredWidth();
    }
}
