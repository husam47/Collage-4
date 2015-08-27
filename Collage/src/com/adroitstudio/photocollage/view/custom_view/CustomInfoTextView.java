package com.adroitstudio.photocollage.view.custom_view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class CustomInfoTextView extends TextView {

	public CustomInfoTextView(Context context) {
		super(context);
		init();
	}

	public CustomInfoTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomInfoTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		init();
	}

	private void init() {
		setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font.ttf"));
		setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) getResources().getDisplayMetrics().widthPixels * 0.05F);
		int padding = (int) ((float) getResources().getDisplayMetrics().widthPixels * 0.01F);
		setPadding(0, padding, 0, padding);
	}

}
