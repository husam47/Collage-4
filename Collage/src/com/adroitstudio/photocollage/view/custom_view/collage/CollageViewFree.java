package com.adroitstudio.photocollage.view.custom_view.collage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;

import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.controller.ImageLoader;

public class CollageViewFree extends CollageViewFixed {
	private boolean mIsViewLock;

	public CollageViewFree(Context context, ImageData imageDataImage) {
		super(context, imageDataImage);
		mIsViewLock = false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mIsViewLock) {
			mPaint.setStrokeWidth(mBorderStrokeSelected);
			mPaint.setColor(Color.RED);
			canvas.drawRect(0 + mBorderStrokeSelected, 0 + mBorderStrokeSelected, getWidth() - mBorderStrokeSelected, getHeight() - mBorderStrokeSelected, mPaint);
		}
	}

	@Override
	public void onChildScale(float scaleFactor) {
		if (!mIsViewLock) {
			setScaleX(getScaleX() + (scaleFactor - 1));
			setScaleY(getScaleY() + (scaleFactor - 1));
		} else {
			super.onChildScale(scaleFactor);
		}
	}

	@Override
	protected void pointerMove(MotionEvent event) {
		try {
			if (!mIsViewLock) {
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
					Log.e("THE-MKR", "CollageViewFree.pointerMove() SCALE FACTOR " + getScaleX()+"     "+getScaleY());
				}
			} else {
				super.pointerMove(event);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLockView() {
		mIsViewLock = true;
		invalidate();
	}

	@Override
	public void onUnlockView() {
		mIsViewLock = false;
		invalidate();
	}

	@Override
	public boolean onIsViewLocked() {
		return mIsViewLock;
	}

	@Override
	public boolean onUnselected() {
		if (super.onUnselected() || mIsViewLock) {
			mIsViewLock = false;
			invalidate();
			return true;
		}
		return false;
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

}
