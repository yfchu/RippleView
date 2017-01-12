package com.yfchu.app.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.yfchu.app.rippleview.R;

/**
 * yfchu
 */
public class RippleView extends Button {

    private float mDownX, mDownY;

    private int rippleColor;
    private float rippeAlpha;
    private float mDensity;
    private float mRadius;

    private Paint mPaint;
    private RadialGradient mRadialGradient;

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDensity = context.getResources().getDisplayMetrics().density;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAlpha(100);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        rippleColor = a.getColor(R.styleable.RippleView_ripple_color, context.getResources().getColor(R.color.colorPrimaryDark));
        rippeAlpha = a.getFloat(R.styleable.RippleView_ripple_alpha, 1.0f);
        a.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDownX = event.getX();
        mDownY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPaint.setAlpha(100);
                setmRadius(dp(50));
                break;
            case MotionEvent.ACTION_MOVE:
                setmRadius(dp(50));
                break;
            case MotionEvent.ACTION_UP:
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(mRadius, getWidth());
                valueAnimator.setDuration(300).start();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        mRadius = (float) valueAnimator.getAnimatedValue();
                        invalidate();
                    }
                });
                ValueAnimator valueAnimator1 = ValueAnimator.ofInt(mPaint.getAlpha(), 0);
                valueAnimator1.setDuration(300).start();
                valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        mPaint.setAlpha((Integer) valueAnimator.getAnimatedValue());
                        invalidate();
                    }
                });
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mDownX, mDownY, mRadius, mPaint);
    }

    private void setmRadius(float radius) {
        this.mRadius = radius;
        mRadialGradient = new RadialGradient(mDownX, mDownY, radius, rippleColor, rippleColor, Shader.TileMode.MIRROR);
        mPaint.setShader(mRadialGradient);
        invalidate();
    }

    private int dp(int dp) {
        return (int) (dp * mDensity);
    }
}
