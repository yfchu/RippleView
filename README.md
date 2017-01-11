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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPaint.setAlpha(100);
                mDownX = event.getX();
                mDownY = event.getY();
                setmRadius(dp(50));
                break;
            case MotionEvent.ACTION_MOVE:
                mDownX = event.getX();
                mDownY = event.getY();
                setmRadius(dp(50));
                break;
            case MotionEvent.ACTION_UP:
                mDownX = event.getX();
                mDownY = event.getY();
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(mRadius, getWidth());
                valueAnimator.setDuration(500).start();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        mRadius = (float) valueAnimator.getAnimatedValue();
                        if (mPaint.getAlpha() >= 5)
                            mPaint.setAlpha(mPaint.getAlpha() - 5);
                        invalidate();
                    }
                });
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mPaint.setAlpha(0);
                        invalidate();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

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
