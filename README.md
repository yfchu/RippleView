# RippleView
按钮的波纹效果

![image](https://github.com/yfchu/RippleView/blob/master/apk/2.gif)   
```xml
		//xml
		<com.yfchu.app.customview.RippleView
        android:layout_width="200dp"
        android:layout_height="100dp"
        android:text="Click me"
        android:background="@drawable/bc"
        app:ripple_alpha="1.0"
        app:ripple_color="#50000000" />
```

```xml
	//attrs
	<declare-styleable name="RippleView">
        <attr name="ripple_color" format="color"/>
        <attr name="ripple_alpha" format="float"/>
    </declare-styleable>
```

```java  
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
	
	//根据半径重绘
	private void setmRadius(float radius) {
        this.mRadius = radius;
        mRadialGradient = new RadialGradient(mDownX, mDownY, radius, rippleColor, rippleColor, Shader.TileMode.MIRROR);
        mPaint.setShader(mRadialGradient);
        invalidate();
    }
```
