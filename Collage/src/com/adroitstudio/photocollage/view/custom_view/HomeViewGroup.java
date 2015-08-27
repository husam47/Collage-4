package com.adroitstudio.photocollage.view.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class HomeViewGroup extends ViewGroup {

	public HomeViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public HomeViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public HomeViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (getChildCount() == 3) {
			int minDim = getHeight();
			if ((3 * minDim) > getWidth()) {
				minDim = (int) ((float) getWidth() * 0.33F);
			}
			int smallDim = (int) ((float) minDim * 0.8F);
			int space = (int) ((float) (getWidth() - ((2 * smallDim) + minDim)) * 0.25F);
			getChildAt(0).layout(space, (getHeight() >> 1) - (smallDim >> 1), space + smallDim, (getHeight() >> 1) + (smallDim >> 1));
			getChildAt(1).layout((getWidth() >> 1) - (minDim >> 1), (getHeight() >> 1) - (minDim >> 1), (getWidth() >> 1) + (minDim >> 1), (getHeight() >> 1) + (minDim >> 1));
			getChildAt(2).layout(getWidth() - (space + smallDim), (getHeight() >> 1) - (smallDim >> 1), getWidth() - space, (getHeight() >> 1) + (smallDim >> 1));
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		float minSide = (float) height;
		if ((3 * minSide) > width) {
			minSide = (int) ((float) width * 0.33F);
		}
		int smallMeasureSpec = MeasureSpec.makeMeasureSpec((int) (minSide * 0.8F), MeasureSpec.EXACTLY);
		int largMeasureSpec = MeasureSpec.makeMeasureSpec((int) minSide, MeasureSpec.EXACTLY);
		getChildAt(0).measure(smallMeasureSpec, smallMeasureSpec);
		getChildAt(1).measure(largMeasureSpec, largMeasureSpec);
		getChildAt(2).measure(smallMeasureSpec, smallMeasureSpec);
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

}
