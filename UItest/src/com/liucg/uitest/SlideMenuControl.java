package com.liucg.uitest;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class SlideMenuControl extends ViewGroup {
	
	private float mLastnX;
	private final static int TOUCH_STATE_REST = 0;
    private final static int TOUCH_STATE_SCROLLING = 1;

    public int mTouchState = TOUCH_STATE_REST;
    
    private Scroller mScroller;

	public SlideMenuControl(Context context, AttributeSet attrs) {
		 this(context, attrs, 0);
	}

	public SlideMenuControl(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		/*postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				x = findViewById(R.id.fragment_titles).getWidth();
				scrollTo(x, 0);	
				Log.d("Runnable", "ok");
			}
		},1000);*/
		mScroller = new Scroller(getContext());
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int childleft = 0;
		final int count = getChildCount();
		for(int i= 0;i<count;i++){
			final View view = getChildAt(i);
			if(view.getVisibility() != View.GONE){
				final int childWith = view.getMeasuredWidth();
				view.layout(childleft, 0, childleft+childWith,view.getMeasuredHeight());
				childleft += childWith;
				
			}
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		View view1 = findViewById(R.id.fragment_titles);
		view1.measure(view1.getLeft()+view1.getRight(), heightMeasureSpec);
		View view2 = findViewById(R.id.framelayout_details);
		view2.measure(view2.getLeft()+view2.getRight(), heightMeasureSpec);
	}


	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		View child;
		for(int i=0;i<getChildCount();i++){
			child = getChildAt(i);
			child.setFocusable(true);
			child.setClickable(true);
		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		final float x = event.getX();
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastnX = x;
			Log.e("mLastMotionX1", Integer.toString((int) mLastnX));
			break;
		case MotionEvent.ACTION_MOVE:
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				final int dX = (int) (mLastnX - x);
				mLastnX = x;
				if(dX<0){
					if(getScrollX()>0){
						scrollBy(Math.max(-getScrollX(), dX),0);
					}			
				}else if (dX>0) {
					final int maxDx =getChildAt(0).getRight()-getScrollX();
					scrollBy(maxDx,0);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		
		return true;			
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		final int action = ev.getAction();
		if ((action == MotionEvent.ACTION_MOVE) && (mTouchState != TOUCH_STATE_REST)) {
			return true;
		}

		final float x = ev.getX();

		switch (action) {
		case MotionEvent.ACTION_MOVE:
			final int xDiff = (int) Math.abs(x - mLastnX);
			if (xDiff > 0) {
				mTouchState = TOUCH_STATE_SCROLLING;
			}
			break;

		case MotionEvent.ACTION_DOWN:
			mLastnX = x;
			Log.e("mLastMotionX0", Integer.toString((int) mLastnX));
			 mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SCROLLING;
			break;

		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchState = TOUCH_STATE_REST;
			break;
		}
		return false;
	}
}
