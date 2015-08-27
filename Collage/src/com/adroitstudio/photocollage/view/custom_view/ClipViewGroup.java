package com.adroitstudio.photocollage.view.custom_view;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.MKRPath;
import com.adroitstudio.photocollage.controller.ClipImageData;
import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.controller.ImageLoader;
import com.adroitstudio.photocollage.controller.ImageLoader.OnImageLoaded;

public class ClipViewGroup extends ViewGroup implements OnScaleGestureListener, OnImageLoaded, Constants {
	private static final int INVALID_POINTER = -9999;
	private ScaleGestureDetector mScaleGestureDetector;
	private float mPointerFirstX, mPointerFirstY;
	private LinkedList<Integer> mPointerId;
	private int mPointerFirstId, mPointerSecondId;
	private ClipView mClipView;
	private MKRPath mPath;
	private Rect mRectImage;
	private ImageData mImageDataImage;
	private Bitmap mBitmapImage;
	private Paint mPaint;

	public ClipViewGroup(Context context) {
		super(context);
		init();
	}

	public ClipViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ClipViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		mScaleGestureDetector = new ScaleGestureDetector(getContext(), this);
		mPath = new MKRPath();
		mPointerId = new LinkedList<Integer>();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth((int) ((float) getResources().getDisplayMetrics().widthPixels * 0.01));
		mRectImage = new Rect();
		setWillNotDraw(false);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		if (mClipView != null && mRectImage.width() > 0 && mRectImage.height() > 0) {
			int dim = (int) ((float) (mRectImage.width() < mRectImage.height() ? mRectImage.width() : mRectImage.height()) * 0.2F);
			int l = (getWidth() >> 1) - (dim >> 1);
			int t = (getHeight() >> 1) - (dim >> 1);
			mClipView.layout(l, t, l + dim, t + dim);
		}
	}

	public void onSetImageData(ImageData imageData) {
		ImageCache.getInstance(getContext()).clear(mImageDataImage);
		mImageDataImage = imageData;
		ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
		mBitmapImage = null;
		invalidate();
	}

	public void onSetClipImageData(ImageData imageData) {
		if (mClipView == null) {
			mClipView = new ClipView(getContext(), imageData);
			addView(mClipView);
		}
		mClipView.setImageData(imageData);
		requestLayout();
	}

	public void onSetClipPath() {
		if (mClipView != null) {
			removeView(mClipView);
			mClipView = null;
		}
		mPath.reset();
		invalidate();
	}

	public ImageData onGetFinalImageData() {
		if (mClipView != null) {
			int centerX = mClipView.getLeft() + (mClipView.getWidth() >> 1);
			int centerY = mClipView.getTop() + (mClipView.getHeight() >> 1);
			int scaledWidth = (int) ((float) mClipView.getWidth() * mClipView.getScaleX());
			int scaledHeight = (int) ((float) mClipView.getHeight() * mClipView.getScaleY());
			int cropLeft = centerX - (scaledWidth >> 1);
			int cropTop = centerY - (scaledHeight >> 1);
			int cropRight = cropLeft + scaledWidth;
			int cropBottam = cropTop + scaledHeight;
			float leftCropPer = (float) (cropLeft - mRectImage.left) / (float) mRectImage.width();
			float rightCropPer = (float) (cropRight - mRectImage.left) / (float) mRectImage.width();
			float topCropPer = (float) (cropTop - mRectImage.top) / (float) mRectImage.height();
			float bottomCropPer = (float) (cropBottam - mRectImage.top) / (float) mRectImage.height();
			ClipImageData clipImageData = new ClipImageData(mImageDataImage.getPath(), mImageDataImage.getImageId(), mImageDataImage.getSource(), IMAGE_TYPE_SIZE_1234, mImageDataImage.getEffect(), leftCropPer, topCropPer, rightCropPer, bottomCropPer, mClipView.getImageData(), mImageDataImage.isHorizontalFlip(), mImageDataImage.isVerticalFlip());
			return clipImageData;
		} else {
			float leftCropPer = (float) (mPath.getmMinX() - mRectImage.left) / (float) mRectImage.width();
			float rightCropPer = (float) (mPath.getmMaxX() - mRectImage.left) / (float) mRectImage.width();
			float topCropPer = (float) (mPath.getmMinY() - mRectImage.top) / (float) mRectImage.height();
			float bottomCropPer = (float) (mPath.getmMaxY() - mRectImage.top) / (float) mRectImage.height();
			ClipImageData clipImageData = new ClipImageData(mImageDataImage.getPath(), mImageDataImage.getImageId(), mImageDataImage.getSource(), IMAGE_TYPE_SIZE_1234, mImageDataImage.getEffect(), leftCropPer, topCropPer, rightCropPer, bottomCropPer, mPath, mImageDataImage.isHorizontalFlip(), mImageDataImage.isVerticalFlip());
			return clipImageData;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mBitmapImage == null || mBitmapImage.isRecycled()) {
			if (mImageDataImage != null) {
				ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
			}
		} else {
			canvas.drawBitmap(mBitmapImage, null, mRectImage, mPaint);
		}
		if (mClipView == null) {
			canvas.drawPath(mPath, mPaint);
		}
		canvas.drawRect(mRectImage, mPaint);
	}

	@Override
	public void onImageLoaded(Bitmap bitmap, ImageData imageData) {
		if (bitmap == null || bitmap.isRecycled() || !imageData.equals(mImageDataImage)) {
			ImageLoader.getInstance(getContext()).execute(mImageDataImage, this);
			return;
		}
		mBitmapImage = bitmap;
		float scaleX = (float) getWidth() / (float) mBitmapImage.getWidth();
		int width = (int) ((float) mBitmapImage.getWidth() * scaleX);
		int height = (int) ((float) mBitmapImage.getHeight() * scaleX);
		if (height >= getHeight()) {
			float scaleY = (float) getHeight() / (float) mBitmapImage.getHeight();
			width = (int) ((float) mBitmapImage.getWidth() * scaleY);
			height = (int) ((float) mBitmapImage.getHeight() * scaleY);
		}
		mRectImage.left = (getWidth() >> 1) - (width >> 1);
		mRectImage.top = (getHeight() >> 1) - (height >> 1);
		mRectImage.right = mRectImage.left + width;
		mRectImage.bottom = mRectImage.top + height;
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mScaleGestureDetector.onTouchEvent(event);
		switch (MotionEventCompat.getActionMasked(event)) {
		case MotionEvent.ACTION_DOWN:
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
			if (mClipView == null) {
				mPath.lineTo(mPath.getInitX(), mPath.getInitY());
			}
			break;
		}
		invalidate();
		return true;
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
		mPath.reset();
		mPath.moveTo(event.getX(), event.getY());
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
		int pointerIndex = MotionEventCompat.getActionIndex(event);
		int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);
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
			if (mPointerSecondId == INVALID_POINTER && mPointerFirstId != INVALID_POINTER) {
				float newX = MotionEventCompat.getX(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
				float newY = MotionEventCompat.getY(event, MotionEventCompat.findPointerIndex(event, mPointerFirstId));
				if (mClipView != null) {
					int disX = (int) (mPointerFirstX - newX);
					int disY = (int) (mPointerFirstY - newY);

					int centerX = mClipView.getLeft() + (mClipView.getWidth() >> 1);
					int centerY = mClipView.getTop() + (mClipView.getHeight() >> 1);

					int scaledWidth = (int) ((float) mClipView.getWidth() * mClipView.getScaleX());
					int scaledHeight = (int) ((float) mClipView.getHeight() * mClipView.getScaleY());

					int left = centerX - (scaledWidth >> 1);
					int right = left + scaledWidth;
					int top = centerY - (scaledHeight >> 1);
					int bottam = top + scaledHeight;

					if ((left - disX) <= mRectImage.left) {
						disX -= mRectImage.left - (left - disX) - 1;
					} else if ((right - disX) >= mRectImage.right) {
						disX += (right - disX) - mRectImage.right + 1;
					}
					if ((top - disY) <= mRectImage.top) {
						disY -= mRectImage.top - (top - disY) - 1;
					} else if ((bottam - disY) >= mRectImage.bottom) {
						disY += (bottam - disY) - mRectImage.bottom + 1;
					}
					mClipView.layout(mClipView.getLeft() - disX, mClipView.getTop() - disY, mClipView.getRight() - disX, mClipView.getBottom() - disY);
				} else {
					float x = newX;
					float y = newY;
					if (x <= mRectImage.left) {
						x = mRectImage.left + 1;
					}
					if (y <= mRectImage.top) {
						y = mRectImage.top + 1;
					}
					if (x >= mRectImage.right) {
						x = mRectImage.right - 1;
					}
					if (y >= mRectImage.bottom) {
						y = mRectImage.bottom - 1;
					}
					mPath.lineTo(x, y);
				}
				mPointerFirstX = newX;
				mPointerFirstY = newY;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		if (mClipView != null) {
			float scalFactorX = mClipView.getScaleX() + detector.getScaleFactor() - 1;
			float scalFactorY = mClipView.getScaleY() + detector.getScaleFactor() - 1;
			if (((int) ((float) mClipView.getWidth() * scalFactorX)) <= mRectImage.width() && ((int) ((float) mClipView.getHeight() * scalFactorY)) <= mRectImage.height()) {
				mClipView.setScaleX(mClipView.getScaleX() + detector.getScaleFactor() - 1);
				mClipView.setScaleY(mClipView.getScaleY() + detector.getScaleFactor() - 1);
				int centerX = mClipView.getLeft() + (mClipView.getWidth() >> 1);
				int centerY = mClipView.getTop() + (mClipView.getHeight() >> 1);
				int scaledWidth = (int) ((float) mClipView.getWidth() * mClipView.getScaleX());
				int scaledHeight = (int) ((float) mClipView.getHeight() * mClipView.getScaleY());
				int left = centerX - (scaledWidth >> 1) - 2;
				int right = left + scaledWidth + 4;
				int top = centerY - (scaledHeight >> 1) - 2;
				int bottam = top + scaledHeight + 4;

				if (left < mRectImage.left) {
					mClipView.layout(mClipView.getLeft() + (mRectImage.left - left), mClipView.getTop(), mClipView.getRight() + (mRectImage.left - left), mClipView.getBottom());
				} else if (right > mRectImage.right) {
					mClipView.layout(mClipView.getLeft() - (right - mRectImage.right), mClipView.getTop(), mClipView.getRight() - (right - mRectImage.right), mClipView.getBottom());
				}
				if (top < mRectImage.top) {
					mClipView.layout(mClipView.getLeft(), mClipView.getTop() + (mRectImage.top - top), mClipView.getRight(), mClipView.getBottom() + (mRectImage.top - top));
				} else if (bottam > mRectImage.bottom) {
					mClipView.layout(mClipView.getLeft(), mClipView.getTop() - (bottam - mRectImage.bottom), mClipView.getRight(), mClipView.getBottom() - (bottam - mRectImage.bottom));
				}
			}
		}
		return true;
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {

	}

}
