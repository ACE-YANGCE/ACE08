package com.routing.ace.ace08;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by yangc on 2018/6/1.
 */

public class MyRoundLayout  extends FrameLayout {

    private float topLeftRadius;
    private float topRightRadius;
    private float bottomLeftRadius;
    private float bottomRightRadius;

    private Paint roundPaint;
    private Paint imagePaint;

    public MyRoundLayout(Context context) {
        this(context, null);
    }

    public MyRoundLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRoundLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyRoundLayout);
            float roundradius = ta.getDimension(R.styleable.MyRoundLayout_roundradius, 0);
            topLeftRadius = ta.getDimension(R.styleable.MyRoundLayout_topLeftRadius, roundradius);
            topRightRadius = ta.getDimension(R.styleable.MyRoundLayout_topRightRadius, roundradius);
            bottomLeftRadius = ta.getDimension(R.styleable.MyRoundLayout_bottomLeftRadius, roundradius);
            bottomRightRadius = ta.getDimension(R.styleable.MyRoundLayout_bottomRightRadius, roundradius);
            ta.recycle();
        }
        roundPaint = new Paint();
        roundPaint.setColor(Color.WHITE);
        roundPaint.setAntiAlias(true);
        roundPaint.setStyle(Paint.Style.FILL);
        roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        imagePaint = new Paint();
        imagePaint.setXfermode(null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), imagePaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);
        canvas.restore();
    }

    private void drawTopLeft(Canvas canvas) {
        if (topLeftRadius > 0) {
            Path path = new Path();
            path.moveTo(0, topLeftRadius);
            path.lineTo(0, 0);
            path.lineTo(topLeftRadius, 0);
            path.arcTo(new RectF(0, 0, topLeftRadius * 2, topLeftRadius * 2), -90, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (topRightRadius > 0) {
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - topRightRadius, 0);
            path.lineTo(width, 0);
            path.lineTo(width, topRightRadius);
            path.arcTo(new RectF(width - 2 * topRightRadius, 0, width, topRightRadius * 2), 0, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawBottomLeft(Canvas canvas) {
        if (bottomLeftRadius > 0) {
            int height = getHeight();
            Path path = new Path();
            path.moveTo(0, height - bottomLeftRadius);
            path.lineTo(0, height);
            path.lineTo(bottomLeftRadius, height);
            path.arcTo(new RectF(0, height - 2 * bottomLeftRadius, bottomLeftRadius * 2, height), 90, 90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawBottomRight(Canvas canvas) {
        if (bottomRightRadius > 0) {
            int height = getHeight();
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - bottomRightRadius, height);
            path.lineTo(width, height);
            path.lineTo(width, height - bottomRightRadius);
            path.arcTo(new RectF(width - 2 * bottomRightRadius, height - 2 * bottomRightRadius, width, height), 0, 90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    /**
     * 设置左上角圆角弧度
     *
     * @param topLeftRadius
     */
    public void setDrawTopLeft(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        invalidate();
    }

    /**
     * 设置右上角圆角弧度
     *
     * @param topRightRadius
     */
    public void setDrawTopRight(float topRightRadius) {
        this.topRightRadius = topRightRadius;
        invalidate();
    }

    /**
     * 设置左下角圆角弧度
     *
     * @param bottomLeftRadius
     */
    public void setDrawBottomLeft(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        invalidate();
    }

    /**
     * 设置右下角圆角弧度
     *
     * @param bottomRightRadius
     */
    public void setDrawBottomRight(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        invalidate();
    }

    /**
     * 设置左上角与右下角圆角弧度，简称左对角线弧度
     *
     * @param radius
     */
    public void setLeftDiagonal(float radius) {
        this.topLeftRadius = radius;
        this.bottomRightRadius = radius;
        this.topRightRadius = 0.0f;
        this.bottomLeftRadius = 0.0f;
        invalidate();
    }

    /**
     * 设置右上角与左下角圆角弧度，简称右对角线弧度
     *
     * @param radius
     */
    public void setRightDiagonal(float radius) {
        this.topLeftRadius = 0.0f;
        this.bottomRightRadius = 0.0f;
        this.topRightRadius = radius;
        this.bottomLeftRadius = radius;
        invalidate();
    }

    /**
     * 设置左下角与右下角圆角弧度，简称下弧度
     *
     * @param radius
     */
    public void setBottomDiagonal(float radius) {
        this.topLeftRadius = 0.0f;
        this.topRightRadius = 0.0f;
        this.bottomRightRadius = radius;
        this.bottomLeftRadius = radius;
        invalidate();
    }

    /**
     * 设置右上角与左上角圆角弧度，简称上弧度
     *
     * @param radius
     */
    public void setTopDiagonal(float radius) {
        this.topLeftRadius = radius;
        this.topRightRadius = radius;
        this.bottomRightRadius = 0.0f;
        this.bottomLeftRadius = 0.0f;
        invalidate();
    }

    /**
     * 设置所有角度统一圆角弧度
     *
     * @param radius
     */
    public void setAllDiagonal(float radius) {
        this.topLeftRadius = radius;
        this.bottomRightRadius = radius;
        this.topRightRadius = radius;
        this.bottomLeftRadius = radius;
        invalidate();
    }
}
