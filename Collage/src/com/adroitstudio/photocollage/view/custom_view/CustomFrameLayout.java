package com.adroitstudio.photocollage.view.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.adroitstudio.photocollage.R;

public class CustomFrameLayout extends FrameLayout {

	private Paint mPaintMKR;

	public CustomFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CustomFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomFrameLayout(Context context) {
		super(context);
		init();
	}

	private void init() {
		mPaintMKR = new Paint();
		mPaintMKR.setAntiAlias(true);
		mPaintMKR.setColor(getResources().getColor(R.color.horizontal_list_bg));
		mPaintMKR.setStrokeWidth((int) ((float) getResources().getDisplayMetrics().widthPixels * 0.01F));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int strokeWidth = (int) mPaintMKR.getStrokeWidth();
		int x = 0;
		boolean temp = false;
		while (!(x > (getWidth() + getHeight()))) {
			if (temp = (!temp)) {
				mPaintMKR.setColor(getResources().getColor(R.color.headder_text_fill));
			} else {
				mPaintMKR.setColor(getResources().getColor(R.color.horizontal_list_bg));
			}
			canvas.drawLine(x, 0 - strokeWidth, x - getHeight(), getHeight() + strokeWidth, mPaintMKR);
			x += strokeWidth;
		}
	}

}
