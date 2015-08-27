package com.adroitstudio.photocollage.view.custom_view.collage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

import com.adroitstudio.photocollage.Utils;
import com.adroitstudio.photocollage.controller.ClipImageData;
import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.controller.ImageLoader;

public class CollageViewClipSmilly extends CollageViewFixed {

	public CollageViewClipSmilly(Context context, ImageData imageDataImage) {
		super(context, imageDataImage);
		mPaint.setStrokeWidth(mBorderStrokeSelected);
		mPaint.setColor(Color.BLUE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.argb(0, 0, 0, 0));
		if (mBitmapImage == null || mBitmapImage.isRecycled()) {
			ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
			canvas.drawColor(Color.BLACK);
		} else {
			canvas.drawBitmap(mBitmapImage, mMatrixBitmap, mPaint);
		}
		if (mIsSingleTapped) {
			canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		}
	}

	@Override
	public void onImageLoaded(Bitmap bitmap, ImageData imageData) {
		if (bitmap == null || bitmap.isRecycled() || !mImageDataImage.equals(imageData)) {
			ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
			return;
		}
		mBitmapImage = bitmap;
		mMatrixBitmap.reset();
		int centerX = getLeft() + (getWidth() >> 1);
		int centerY = getTop() + (getHeight() >> 1);
		int newWidth = mBitmapImage.getWidth();
		int newHeight = mBitmapImage.getHeight();
		setLeft(centerX - (newWidth >> 1));
		setTop(centerY - (newHeight >> 1));
		setRight(getLeft() + newWidth);
		setBottom(getTop() + newHeight);
		invalidate();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (mBitmapImage != null && !mBitmapImage.isRecycled() && getHeight() > 0 && getWidth() > 0) {
			mMatrixBitmap.reset();
			int centerX = getLeft() + (getWidth() >> 1);
			int centerY = getTop() + (getHeight() >> 1);
			int newWidth = mBitmapImage.getWidth();
			int newHeight = mBitmapImage.getHeight();
			setLeft(centerX - (newWidth >> 1));
			setTop(centerY - (newHeight >> 1));
			setRight(getLeft() + newWidth);
			setBottom(getTop() + newHeight);
			invalidate();
		}
	}

	@Override
	protected void pointerMove(MotionEvent event) {
		try {
			if (mPointerSecondId == INVALID_POINTER && mPointerFirstId != INVALID_POINTER) {
				float newX = MotionEventCompat.getX(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
				float newY = MotionEventCompat.getY(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
				int disX = (int) (mPointerFirstX - newX);
				int disY = (int) (mPointerFirstY - newY);
				layout(getLeft() - disX, getTop() - disY, getRight() - disX, getBottom() - disY);
				mPointerFirstX = newX;
				mPointerFirstY = newY;
			} else {
				mPointerFirstX = MotionEventCompat.getX(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
				mPointerFirstY = MotionEventCompat.getY(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
				float newAngle = getCurrentAngle(event);
				float rotationDegree = 0;
				if ((rotationDegree = Math.abs(newAngle - mLastAngle)) > 180) {
					rotationDegree = (newAngle > mLastAngle) ? Math.abs(Math.abs(360 - newAngle) + mLastAngle) : Math.abs(Math.abs(360 - mLastAngle) + newAngle);
				}
				setRotation(getRotation() + ((newAngle > mLastAngle) ? -rotationDegree : rotationDegree));
				mLastAngle = newAngle;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onChildScale(float scaleFactor) {
		if (mIsSingleTapped) {
			setScaleX(getScaleX() + (scaleFactor - 1));
			setScaleY(getScaleX() + (scaleFactor - 1));
		} else {
			super.onChildScale(scaleFactor);
		}
	}
}
