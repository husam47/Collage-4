package com.adroitstudio.photocollage.view.custom_view.collage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.controller.ImageLoader;
import com.adroitstudio.photocollage.controller.ImageLoader.OnImageLoaded;

public class CollageBackgroundView extends View implements OnImageLoaded, Constants {
	private ImageData mImageDataBackground;
	private Bitmap mBitmapBackground;
	private Paint mPaintBitmapShader;

	public CollageBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public CollageBackgroundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CollageBackgroundView(Context context) {
		super(context);
		init();
	}

	private void init() {
		mPaintBitmapShader = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
		try {
			String[] list = getContext().getAssets().list(ASSETS_BACKGROUNDS);
			if (list.length >= 1) {
				mImageDataBackground = new ImageData(ASSETS_BACKGROUNDS + "/" + list[0], -1, SOURCE_APPLICATION, IMAGE_TYPE_FULL_SIZE);
			}
			if (mImageDataBackground != null) {
				ImageLoader.getInstance(getContext()).execute(mImageDataBackground, this);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to set the new background
	 * 
	 * @param imageData
	 */
	public void onSetCollageBackground(ImageData imageData) {
		ImageLoader.getInstance(getContext()).execute(mImageDataBackground = imageData, this);
	}

	public void onDraw(Canvas canvas) {
		if (mBitmapBackground != null && !mBitmapBackground.isRecycled()) {
			canvas.drawPaint(mPaintBitmapShader);
		} else {
			canvas.drawColor(Color.CYAN);
			if (mImageDataBackground != null) {
				ImageLoader.getInstance(getContext()).execute(mImageDataBackground, this);
			}
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		invalidate();
	}

	@Override
	public void onImageLoaded(Bitmap bitmap, ImageData imageData) {
		if (bitmap == null || bitmap.isRecycled() || mImageDataBackground.equals(imageData)) {
			ImageLoader.getInstance(getContext()).execute(mImageDataBackground, this);
		}
		mBitmapBackground = bitmap;
		mPaintBitmapShader.setShader(new BitmapShader(bitmap, TileMode.REPEAT, TileMode.REPEAT));
		invalidate();
	}

	public void onDrawBackground(Canvas canvas, int width, int height) {
		if (mBitmapBackground != null && !mBitmapBackground.isRecycled()) {
			canvas.drawPaint(mPaintBitmapShader);
		} else {
			canvas.drawColor(Color.BLACK);
		}
	}
}
