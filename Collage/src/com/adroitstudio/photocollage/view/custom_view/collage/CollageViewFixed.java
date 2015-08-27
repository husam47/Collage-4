package com.adroitstudio.photocollage.view.custom_view.collage;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.R;
import com.adroitstudio.photocollage.controller.ClipImageData;
import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.controller.ImageLoader;
import com.adroitstudio.photocollage.controller.ImageLoader.OnImageLoaded;

public class CollageViewFixed extends View implements Constants, OnCollageView, OnImageLoaded {

	protected ImageData mImageDataImage;
	protected Bitmap mBitmapImage;
	protected Matrix mMatrixBitmap;
	protected Paint mPaint;
	protected boolean mIsSingleTapped;
	protected float mBorderStrokeOuter, mBorderStrokeSelected, mPointerFirstX, mPointerFirstY, mLastAngle;
	protected LinkedList<Integer> mPointerId;
	protected int mPointerFirstId, mPointerSecondId;

	public CollageViewFixed(Context context, ImageData imageDataImage) {
		super(context);
		mPointerFirstId = mPointerSecondId = INVALID_POINTER;
		mImageDataImage = imageDataImage;
		mPointerId = new LinkedList<Integer>();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		mPaint.setStyle(Style.STROKE);
		mBorderStrokeOuter = (int) ((float) getResources().getDisplayMetrics().widthPixels * 0.01);
		mBorderStrokeSelected = (int) ((float) getResources().getDisplayMetrics().widthPixels * 0.01);
		mMatrixBitmap = new Matrix();
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
		mPaint.setStrokeWidth(mBorderStrokeOuter);
		mPaint.setColor(Color.WHITE);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		if (mIsSingleTapped) {
			mPaint.setStrokeWidth(mBorderStrokeSelected);
			mPaint.setColor(Color.BLUE);
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
		if (mBitmapImage.getWidth() < getWidth() || mBitmapImage.getHeight() < getHeight()) {
			mMatrixBitmap.reset();
			if ((getWidth() - mBitmapImage.getWidth()) > (getHeight() - mBitmapImage.getHeight())) {
				mMatrixBitmap.setScale(((float) getWidth() / (float) mBitmapImage.getWidth()), ((float) getWidth() / (float) mBitmapImage.getWidth()), 0, 0);
			} else {
				mMatrixBitmap.setScale(((float) getHeight() / (float) mBitmapImage.getHeight()), ((float) getHeight() / (float) mBitmapImage.getHeight()), 0, 0);
			}
		}
		invalidate();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (mBitmapImage != null && !mBitmapImage.isRecycled() && getWidth() > 0 && getHeight() > 0 && mMatrixBitmap != null) {
			mMatrixBitmap.reset();
			if (mBitmapImage.getWidth() < getWidth() || mBitmapImage.getHeight() < getHeight()) {
				if ((getWidth() - mBitmapImage.getWidth()) > (getHeight() - mBitmapImage.getHeight())) {
					mMatrixBitmap.setScale(((float) getWidth() / (float) mBitmapImage.getWidth()), ((float) getWidth() / (float) mBitmapImage.getWidth()), 0, 0);
				} else {
					mMatrixBitmap.setScale(((float) getHeight() / (float) mBitmapImage.getHeight()), ((float) getHeight() / (float) mBitmapImage.getHeight()), 0, 0);
				}
			}
		}
	}

	@Override
	public void onApplyEffect(int effect) {
		ImageCache.getInstance(getContext()).clear(mImageDataImage);
		mImageDataImage.setEffect(effect);
		ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
		postInvalidate();
	}

	@Override
	public void onFlipVerticall() {
		ImageCache.getInstance(getContext()).clear(mImageDataImage);
		mImageDataImage.setVerticalFlip();
		ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
		invalidate();
	}

	@Override
	public void onFlipHorizontally() {
		ImageCache.getInstance(getContext()).clear(mImageDataImage);
		mImageDataImage.setHorizontalFlip();
		ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
		invalidate();
	}

	@Override
	public void onChildMotion(MotionEvent event) {
		switch (MotionEventCompat.getActionMasked(event)) {
		case MotionEvent.ACTION_DOWN:
			mPointerFirstId = mPointerSecondId = INVALID_POINTER;
			onActionDown(event);
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			onActionPointerDown(event);
			break;
		case MotionEvent.ACTION_POINTER_UP:
			onActionPointerUp(event);
			break;
		case MotionEvent.ACTION_MOVE:
			pointerMove(event);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			mPointerFirstId = mPointerSecondId = INVALID_POINTER;
			mPointerFirstX = mPointerFirstY = 0;
			mPointerId.clear();
			break;
		}
		invalidate();
	}

	/**
	 * Method called when down event occur which means user touch the screen
	 * through first pointer first
	 * 
	 * @param event
	 */
	private void onActionDown(MotionEvent event) {
		int pointerIndex = MotionEventCompat.getActionIndex(event);
		mPointerId.clear();
		mPointerFirstX = MotionEventCompat.getX(event, pointerIndex);
		mPointerFirstY = MotionEventCompat.getY(event, pointerIndex);
		mPointerFirstId = MotionEventCompat.getPointerId(event, pointerIndex);
		mPointerId.add(mPointerFirstId);
	}

	/**
	 * Method called when pointer down event occur which means user touch screen
	 * through new pointer other then initial pointer
	 * 
	 * @param event
	 */
	private void onActionPointerDown(MotionEvent event) {
		int pointerIndex = MotionEventCompat.getActionIndex(event);
		int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);
		if (mPointerSecondId == INVALID_POINTER) {
			mPointerSecondId = pointerId;
			mLastAngle = getCurrentAngle(event);
		}
		mPointerId.addLast(pointerId);
	}

	/**
	 * Method called when pointer up event occur which means user remove
	 * pointer, but not last pointer
	 * 
	 * @param event
	 */
	private void onActionPointerUp(MotionEvent event) {
		int pointerId = MotionEventCompat.getPointerId(event, MotionEventCompat.getActionIndex(event));
		mPointerId.remove(Integer.valueOf(pointerId));
		if (mPointerFirstId == pointerId) {
			if (mPointerSecondId != INVALID_POINTER) {
				mPointerFirstId = mPointerSecondId;
				for (int i = 0; i < mPointerId.size(); i++) {
					Integer id = mPointerId.get(i);
					if ((mPointerSecondId = (id != mPointerSecondId) ? id : INVALID_POINTER) != INVALID_POINTER) {
						break;
					}
				}
			} else if (mPointerId.size() > 0) {
				mPointerFirstId = mPointerId.get(0);
			} else {
				mPointerFirstId = INVALID_POINTER;
			}
			mPointerFirstX = MotionEventCompat.getX(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
			mPointerFirstY = MotionEventCompat.getY(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
		} else if (mPointerSecondId == pointerId) {
			for (int i = 0; i < mPointerId.size(); i++) {
				Integer id = mPointerId.get(i);
				if ((mPointerSecondId = (id != mPointerFirstId) ? id : INVALID_POINTER) != INVALID_POINTER) {
					break;
				}
			}
		}
	}

	/**
	 * Method for moving the pointter
	 * 
	 * @param event
	 */
	protected void pointerMove(MotionEvent event) {
		try {
			float newX = MotionEventCompat.getX(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
			float newY = MotionEventCompat.getY(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
			if (mPointerSecondId == INVALID_POINTER && mPointerFirstId != INVALID_POINTER) {
				int transX = -((int) (mPointerFirstX - newX));
				int transY = -((int) (mPointerFirstY - newY));
				mMatrixBitmap.postTranslate(getRotatedXPoint(0, 0, transX, transY, -getRotation()), getRotatedYPoint(0, 0, transX, transY, -getRotation()));
				mPointerFirstX = newX;
				mPointerFirstY = newY;
			} else {
				mPointerFirstX = newX;
				mPointerFirstY = newY;
				float newAngle = getCurrentAngle(event);
				float rotationDegree = 0;
				if ((rotationDegree = Math.abs(newAngle - mLastAngle)) > 180) {
					rotationDegree = (newAngle > mLastAngle) ? Math.abs(Math.abs(360 - newAngle) + mLastAngle) : Math.abs(Math.abs(360 - mLastAngle) + newAngle);
				}
				float[] matrixPoint = new float[9];
				mMatrixBitmap.getValues(matrixPoint);
				mMatrixBitmap.postRotate((newAngle > mLastAngle) ? -rotationDegree : rotationDegree, 0 - matrixPoint[0] + (getWidth() >> 1), 0 - matrixPoint[4] + (getHeight() >> 1));
				mLastAngle = newAngle;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to get the qudrant of Second Pointer
	 * 
	 * @param event
	 */
	protected float getCurrentAngle(MotionEvent event) {
		float pointer2X = MotionEventCompat.getX(event, MotionEventCompat.findPointerIndex(event, mPointerSecondId));
		float pointer2Y = MotionEventCompat.getY(event, MotionEventCompat.findPointerIndex(event, mPointerSecondId));
		if (pointer2Y <= mPointerFirstY) {
			if (pointer2X >= mPointerFirstX) {
				return (float) Math.toDegrees(Math.atan2(mPointerFirstY - pointer2Y, pointer2X - mPointerFirstX));
			} else {
				return 180 - (float) Math.toDegrees(Math.atan2(mPointerFirstY - pointer2Y, mPointerFirstX - pointer2X));
			}
		} else {
			if (pointer2X >= mPointerFirstX) {
				return 360 - (float) Math.toDegrees(Math.atan2(pointer2Y - mPointerFirstY, pointer2X - mPointerFirstX));
			} else {
				return 180 + (float) Math.toDegrees(Math.atan2(pointer2Y - mPointerFirstY, mPointerFirstX - pointer2X));
			}
		}
	}

	@Override
	public void onChildScale(float scaleFactor) {
		float[] matrixPoint = new float[9];
		mMatrixBitmap.getValues(matrixPoint);
		mMatrixBitmap.postScale(scaleFactor, scaleFactor, 0 - matrixPoint[0] + (getWidth() >> 1), 0 - matrixPoint[4] + (getHeight() >> 1));
	}

	@Override
	public void onSingalTap() {
		mIsSingleTapped = true;
		invalidate();
	}

	@Override
	public void onLockView() {
		// DO nothing
	}

	@Override
	public void onUnlockView() {
		// DO nothing
	}

	@Override
	public boolean onIsViewLocked() {
		return true;
	}

	@Override
	public boolean onUnselected() {
		if (mIsSingleTapped) {
			mIsSingleTapped = false;
			invalidate();
			return true;
		}
		return false;
	}

	@Override
	public void onSetCollageItemFrame(ImageData imageData) {

	}

	@Override
	public void onSetImageData(ImageData imageData) {
		mImageDataImage = imageData;
		ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
	}

	@Override
	public ImageData onGetImageData() {
		return mImageDataImage;
	}

	@Override
	public Bitmap onGetCollageItemBitmap(int width, int height) {
		ImageCache imageCache = ImageCache.getInstance(getContext());
		// SET BITMAP MATRIX
		Matrix matrix = new Matrix();
		float[] matrixValue = new float[9];
		float[] newMatrixValue = new float[9];
		mMatrixBitmap.getValues(matrixValue);
		newMatrixValue[Matrix.MSCALE_X] = matrixValue[Matrix.MSCALE_X];
		newMatrixValue[Matrix.MSCALE_Y] = matrixValue[Matrix.MSCALE_Y];
		newMatrixValue[Matrix.MSKEW_X] = matrixValue[Matrix.MSKEW_X];
		newMatrixValue[Matrix.MSKEW_Y] = matrixValue[Matrix.MSKEW_Y];
		newMatrixValue[Matrix.MTRANS_X] = (int) ((float) (matrixValue[Matrix.MTRANS_X] * width) / (float) getWidth());
		newMatrixValue[Matrix.MTRANS_Y] = (int) ((float) (matrixValue[Matrix.MTRANS_Y] * height) / (float) getHeight());
		newMatrixValue[Matrix.MPERSP_0] = matrixValue[Matrix.MPERSP_0];
		newMatrixValue[Matrix.MPERSP_1] = matrixValue[Matrix.MPERSP_1];
		newMatrixValue[Matrix.MPERSP_2] = matrixValue[Matrix.MPERSP_2];
		matrix.setValues(newMatrixValue);
		// GET BITMAP & RESIZE BITMAP
		ImageData imageDataOrignalPic = null;
		if (mImageDataImage instanceof ClipImageData) {
			ClipImageData clipImageData = (ClipImageData) mImageDataImage;
			if (clipImageData.getClipPath() != null) {
				imageDataOrignalPic = new ClipImageData(clipImageData.getPath(), clipImageData.getImageId(), clipImageData.getSource(), IMAGE_TYPE_FULL_SIZE, clipImageData.getEffect(), clipImageData.getmLeft(), clipImageData.getmTop(), clipImageData.getmRight(), clipImageData.getmBottam(), clipImageData.getClipPath(), clipImageData.isHorizontalFlip(), clipImageData.isVerticalFlip());
			} else {
				ImageData clipImage = clipImageData.getClipImage();
				imageDataOrignalPic = new ClipImageData(clipImageData.getPath(), clipImageData.getImageId(), clipImageData.getSource(), IMAGE_TYPE_FULL_SIZE, clipImageData.getEffect(), clipImageData.getmLeft(), clipImageData.getmTop(), clipImageData.getmRight(), clipImageData.getmBottam(), new ImageData(clipImage.getPath(), clipImage.getImageId(), clipImage.getSource(), IMAGE_TYPE_FULL_SIZE), clipImageData.isHorizontalFlip(), clipImageData.isVerticalFlip());
			}
		} else {
			imageDataOrignalPic = new ImageData(mImageDataImage.getPath(), mImageDataImage.getImageId(), mImageDataImage.getSource(), IMAGE_TYPE_FULL_SIZE, mImageDataImage.getEffect(), mImageDataImage.isHorizontalFlip(), mImageDataImage.isVerticalFlip());
		}

		Bitmap bitmapInCachay = imageCache.getBitmap(imageDataOrignalPic);
		Bitmap pic = Bitmap.createScaledBitmap(bitmapInCachay, (int) ((float) (mBitmapImage.getWidth() * width) / (float) getWidth()), (int) ((float) (mBitmapImage.getHeight() * height) / (float) getHeight()), true);
		if (!bitmapInCachay.equals(pic)) {
			imageCache.clear(imageDataOrignalPic);
		}
		// DRAW BITMAP
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		paint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.header_stroke));

		Bitmap bitmapView = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapView);
		if (pic != null && !pic.isRecycled()) {
			canvas.drawBitmap(pic, matrix, paint);
			pic.recycle();
		}
		return bitmapView;
	}

	@Override
	public Matrix onGetViewMatrix() {
		return getMatrix();
	}

	@Override
	public boolean onIsViewTapped(MotionEvent event) {
		int viewCenterX = getLeft() + (getWidth() >> 1);
		int viewCenterY = getTop() + (getHeight() >> 1);
		int viewScalledWidth = (int) ((float) getWidth() * getScaleX());
		int viewScalledHeight = (int) ((float) getHeight() * getScaleY());
		int viewLeft = viewCenterX - (viewScalledWidth >> 1);
		int viewTop = viewCenterY - (viewScalledHeight >> 1);
		int viewRight = viewLeft + viewScalledWidth;
		int viewBottam = viewTop + viewScalledHeight;
		int pointX = getRotatedXPoint(viewCenterX, viewCenterY, event.getX(), event.getY(), -getRotation());
		int pointY = getRotatedYPoint(viewCenterX, viewCenterY, event.getX(), event.getY(), -getRotation());
		if (viewLeft <= pointX && pointX <= viewRight && viewTop <= pointY && pointY <= viewBottam) {
			return true;
		}
		return false;
	}

	/**
	 * Method to get the new X position after apply rotation
	 * 
	 * @param centerX
	 *            pivote X
	 * @param centerY
	 *            pivote Y
	 * @param x
	 *            old x
	 * @param y
	 *            old y
	 * @param angel
	 *            angle
	 * @return
	 */
	private int getRotatedXPoint(float centerX, float centerY, float x, float y, double angel) {
		return (int) ((double) (x - centerX) * Math.cos(Math.toRadians(angel)) - (double) (y - centerY) * Math.sin(Math.toRadians(angel)) + centerX);
	}

	/**
	 * Method to get the new Y position after apply rotation
	 * 
	 * @param centerX
	 *            pivote X
	 * @param centerY
	 *            pivote Y
	 * @param x
	 *            old x
	 * @param y
	 *            old y
	 * @param angel
	 *            angle
	 * @return
	 */
	private int getRotatedYPoint(float centerX, float centerY, float x, float y, double angel) {
		return (int) ((double) (x - centerX) * Math.sin(Math.toRadians(angel)) + (double) (y - centerY) * Math.cos(Math.toRadians(angel)) + centerY);
	}
}
