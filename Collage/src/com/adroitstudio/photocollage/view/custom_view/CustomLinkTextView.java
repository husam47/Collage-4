package com.adroitstudio.photocollage.view.custom_view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class CustomLinkTextView extends TextView {

	public CustomLinkTextView(Context context) {
		super(context);
		init();
	}

	public CustomLinkTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomLinkTextView(Context context, AttributeSet attrs, int defStyle) {
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
		setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) getResources().getDisplayMetrics().widthPixels * 0.035F);
		int padding = (int) ((float) getWidth() * 0.2F);
		setPadding(padding, 0, padding, 0);
	}

}