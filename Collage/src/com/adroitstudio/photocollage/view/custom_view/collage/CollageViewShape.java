package com.adroitstudio.photocollage.view.custom_view.collage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;

import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.controller.ImageLoader;

public class CollageViewShape extends CollageViewFixed {
	private ImageData mImageDataShape;
	private Matrix mMatrixShape;
	private Paint mPaintShape;
	private PorterDuffXfermode mPorterDuffXfermode;
	private Bitmap mBitmapShape, mBitmapView;

	public CollageViewShape(Context context, ImageData imageDataImage, ImageData imageDataShape) {
		super(context, imageDataImage);
		mPorterDuffXfermode = new PorterDuffXfermode(Mode.DST_IN);
		mPaintShape = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		mMatrixShape = new Matrix();
		mImageDataShape = imageDataShape;
	}

	/**
	 * Method to create shape bitmap
	 */
	private void createFinalShapeBitmap() {
		if (mBitmapView != null) {
			mBitmapView.recycle();
			mBitmapView = null;
		}
		if (getHeight() > 0 && getWidth() > 0) {
			if (mBitmapImage != null && !mBitmapImage.isRecycled() && mBitmapShape != null && !mBitmapShape.isRecycled()) {
				mBitmapView = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
				Canvas canvas = new Canvas(mBitmapView);
				canvas.drawColor(Color.argb(0, 0, 0, 0));
				mPaintShape.reset();
				mPaintShape.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
				canvas.drawBitmap(mBitmapImage, mMatrixBitmap, mPaintShape);
				mPaintShape.setXfermode(mPorterDuffXfermode);
				canvas.drawBitmap(mBitmapShape, mMatrixShape, mPaintShape);
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.argb(0, 0, 0, 0));
		if (mBitmapImage == null || mBitmapImage.isRecycled()) {
			ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
		}
		if (mBitmapShape == null || mBitmapShape.isRecycled()) {
			ImageLoader.getInstance(getContext()).execute(mImageDataShape, this);
		}
		if (mBitmapView != null && !mBitmapView.isRecycled()) {
			canvas.drawBitmap(mBitmapView, 0, 0, mPaint);
		} else {
			ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
			ImageLoader.getInstance(getContext()).execute(mImageDataShape, this);
		}
		if (mIsSingleTapped) {
			mPaint.setStrokeWidth(mBorderStrokeSelected);
			mPaint.setColor(Color.BLUE);
			canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		if (mBitmapView != null) {
			mBitmapView.recycle();
		}
		super.onDetachedFromWindow();
	}

	@Override
	public void onChildMotion(MotionEvent event) {
		super.onChildMotion(event);
		createFinalShapeBitmap();
		invalidate();
	}

	@Override
	public Bitmap onGetCollageItemBitmap(int width, int height) {
		ImageCache imageCache = ImageCache.getInstance(getContext());
		// DRAW BITMAP
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		Bitmap bitmapView = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapView);
		canvas.drawARGB(0, 0, 0, 0);
		Bitmap bitmapPic = super.onGetCollageItemBitmap(width, height);
		if (bitmapPic != null && bitmapPic.isRecycled()) {
			canvas.drawBitmap(bitmapPic, 0, 0, paint);
			bitmapPic.recycle();
		}

		// GET BITMAP OF SHAPE
		ImageData imageDataShape = new ImageData(mImageDataShape.getPath(), mImageDataShape.getImageId(), mImageDataShape.getSource(), IMAGE_TYPE_FULL_SIZE);
		Bitmap cacheBitmapShape = imageCache.getBitmap(imageDataShape);
		Bitmap bitmapShape = Bitmap.createScaledBitmap(cacheBitmapShape, (int) ((float) (mBitmapShape.getWidth() * width) / (float) getWidth()), (int) ((float) (mBitmapShape.getHeight() * height) / (float) getHeight()), true);
		if (!cacheBitmapShape.equals(bitmapShape)) {
			imageCache.clear(imageDataShape);
		}

		paint.setXfermode(mPorterDuffXfermode);
		if (bitmapShape != null && !bitmapShape.isRecycled()) {
			canvas.drawBitmap(bitmapShape, 0, 0, paint);
			bitmapShape.recycle();
		}
		return bitmapView;
	}

	@Override
	public void onImageLoaded(Bitmap bitmap, ImageData imageData) {
		if (bitmap == null || bitmap.isRecycled() || !(mImageDataImage.equals(imageData) || mImageDataShape.equals(imageData))) {
			ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
			ImageLoader.getInstance(getContext()).execute(mImageDataShape, this);
			invalidate();
			return;
		}
		if (mImageDataImage.equals(imageData)) {
			mBitmapImage = bitmap;
			if (mBitmapImage.getWidth() < getWidth() || mBitmapImage.getHeight() < getHeight()) {
				mMatrixBitmap.reset();
				if ((getWidth() - mBitmapImage.getWidth()) > (getHeight() - mBitmapImage.getHeight())) {
					mMatrixBitmap.setScale(((float) getWidth() / (float) mBitmapImage.getWidth()), ((float) getWidth() / (float) mBitmapImage.getWidth()), 0, 0);
				} else {
					mMatrixBitmap.setScale(((float) getHeight() / (float) mBitmapImage.getHeight()), ((float) getHeight() / (float) mBitmapImage.getHeight()), 0, 0);
				}
			}
		}
		if (mImageDataShape.equals(imageData)) {
			mBitmapShape = bitmap;
			mMatrixShape.reset();
			mMatrixShape.setScale(((float) getWidth() / (float) mBitmapShape.getWidth()), ((float) getHeight() / (float) mBitmapShape.getHeight()));
		}
		createFinalShapeBitmap();
		invalidate();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (mBitmapImage != null && !mBitmapImage.isRecycled()) {
			if (mBitmapImage.getWidth() < getWidth() || mBitmapImage.getHeight() < getHeight()) {
				mMatrixBitmap.reset();
				if ((getWidth() - mBitmapImage.getWidth()) > (getHeight() - mBitmapImage.getHeight())) {
					mMatrixBitmap.setScale(((float) getWidth() / (float) mBitmapImage.getWidth()), ((float) getWidth() / (float) mBitmapImage.getWidth()), 0, 0);
				} else {
					mMatrixBitmap.setScale(((float) getHeight() / (float) mBitmapImage.getHeight()), ((float) getHeight() / (float) mBitmapImage.getHeight()), 0, 0);
				}
			}
		}
		if (mBitmapShape != null && !mBitmapShape.isRecycled()) {
			mMatrixShape.reset();
			mMatrixShape.setScale(((float) getWidth() / (float) mBitmapShape.getWidth()), ((float) getHeight() / (float) mBitmapShape.getHeight()));
		}
		createFinalShapeBitmap();
		invalidate();
	}

}
