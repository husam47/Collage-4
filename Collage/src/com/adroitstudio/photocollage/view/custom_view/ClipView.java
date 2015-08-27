package com.adroitstudio.photocollage.view.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.controller.ImageLoader;
import com.adroitstudio.photocollage.controller.ImageLoader.OnImageLoaded;

public class ClipView extends View implements OnImageLoaded {
	private ImageData mImageData;
	private Bitmap mBitmapImage;
	private Matrix mMatrixBitmap;
	private Paint mPaint;

	public ClipView(Context context, ImageData imageData) {
		super(context);
		mImageData = imageData;
		mMatrixBitmap = new Matrix();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		mPaint.setAlpha(150);
	}

	/**
	 * Method to add new image data
	 * 
	 * @param imageData
	 */
	public void setImageData(ImageData imageData) {
		mImageData = imageData;
		ImageLoader.getInstance(getContext()).execute(mImageData, this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mBitmapImage == null || mBitmapImage.isRecycled()) {
			ImageLoader.getInstance(getContext()).execute(mImageData, this);
			canvas.drawColor(Color.BLUE);
		} else {
			canvas.drawBitmap(mBitmapImage, mMatrixBitmap, mPaint);
		}
	}

	@Override
	public void onImageLoaded(Bitmap bitmap, ImageData imageData) {
		if (bitmap == null || bitmap.isRecycled() || !imageData.equals(mImageData)) {
			ImageLoader.getInstance(getContext()).execute(mImageData, this);
			return;
		}
		mBitmapImage = bitmap;
		mMatrixBitmap.reset();
		mMatrixBitmap.setScale(((float) getWidth() / (float) mBitmapImage.getWidth()), ((float) getHeight() / (float) mBitmapImage.getHeight()), 0, 0);
		invalidate();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (mBitmapImage != null && !mBitmapImage.isRecycled() && getWidth() > 0 && getHeight() > 0 && mMatrixBitmap != null) {
			mMatrixBitmap.reset();
			mMatrixBitmap.setScale(((float) getWidth() / (float) mBitmapImage.getWidth()), ((float) getHeight() / (float) mBitmapImage.getHeight()), 0, 0);
			invalidate();
		}
	}

	/**
	 * Method to return the image data of current view
	 * 
	 * @return
	 */
	public ImageData getImageData() {
		return mImageData;
	}

}
