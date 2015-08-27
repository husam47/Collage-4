package com.adroitstudio.photocollage.view.custom_view.collage;

import java.util.LinkedList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;

public class CollageViewGroup extends ViewGroup implements OnCollageViewGroup, Constants {
	private OnMKRCollageViewGroupCallbackListener mCollageViewGroupCallbackListener;
	private GestureDetector mGestureDetector;
	private ScaleGestureDetector mScaleGestureDetector;
	private OnCollageView mSelectedView;
	private int mCollageType, mCollageLayoutType;
	private boolean mIsSwapped;

	private Random mRandom;

	public CollageViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CollageViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CollageViewGroup(Context context) {
		super(context);
		init();
	}

	private void init() {
		SimpleGestureEvents simpleGestureEvents = new SimpleGestureEvents();
		mGestureDetector = new GestureDetector(getContext(), simpleGestureEvents);
		mScaleGestureDetector = new ScaleGestureDetector(getContext(), simpleGestureEvents);
		mGestureDetector.setIsLongpressEnabled(false);
		mCollageType = COLLAGE_TYPE_FIXED;
		mRandom = new Random();
		setWillNotDraw(false);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (isLayoutImageItem()) {
			Log.e("THE-MKR", "CollageViewGroup.onLayout()" + ">>>layout>>>>>>>>>>>>>");
			LayoutChildUtils.layoutChild(this, mCollageType, mCollageLayoutType);
		}
		LinkedList<View> collageViews = getCollageSmillyes();
		int clipWidth = (int) (((float) getWidth() < getHeight() ? getWidth() : getHeight()) * 0.2F);
		for (int i = 0; i < collageViews.size(); i++) {
			View view = collageViews.get(i);
			if (view.getLeft() == INVALID_LAYOUT || view.getTop() == INVALID_LAYOUT || view.getRight() == INVALID_LAYOUT || view.getBottom() == INVALID_LAYOUT) {
				int x = mRandom.nextInt(getWidth() - clipWidth);
				int y = mRandom.nextInt(getHeight() - clipWidth);
				view.layout(x, y, x + clipWidth, y + clipWidth);
			}
		}
	}

	/**
	 * Method to validate is layout image item or note
	 * 
	 * @return TRUE if need to layout , FALSE otherwise
	 */
	private boolean isLayoutImageItem() {
		LinkedList<View> collageViews = getCollageViews();
		for (int i = 0; i < collageViews.size(); i++) {
			View view = collageViews.get(i);
			Log.e("THE-MKR", "CollageViewGroup.isLayoutImageItem()" + "      " + i + "   " + view.getLeft() + "   " + INVALID_LAYOUT);
			Log.e("THE-MKR", "CollageViewGroup.isLayoutImageItem()" + "      " + i + "   " + view.getTop() + "   " + INVALID_LAYOUT);
			Log.e("THE-MKR", "CollageViewGroup.isLayoutImageItem()" + "      " + i + "   " + view.getRight() + "   " + INVALID_LAYOUT);
			Log.e("THE-MKR", "CollageViewGroup.isLayoutImageItem()" + "      " + i + "   " + view.getBottom() + "   " + INVALID_LAYOUT);
			if (view.getLeft() == INVALID_LAYOUT || view.getTop() == INVALID_LAYOUT || view.getRight() == INVALID_LAYOUT || view.getBottom() == INVALID_LAYOUT) {
				Log.e("THE-MKR", "CollageViewGroup.isLayoutImageItem()" + "      " + i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to get the array of View !instanceOf CollageSmilly
	 * 
	 * @param viewGroup
	 * @return
	 */
	private LinkedList<View> getCollageViews() {
		LinkedList<View> views = new LinkedList<View>();
		for (int i = 0; i < getChildCount(); i++) {
			View childAt = getChildAt(i);
			if (childAt instanceof OnCollageView && !(childAt instanceof CollageViewClipSmilly)) {
				views.add(childAt);
			}
		}
		return views;
	}

	/**
	 * Method to get the array of View instanceOf CollageSmilly
	 * 
	 * @param viewGroup
	 * @return
	 */
	private LinkedList<View> getCollageSmillyes() {
		LinkedList<View> views = new LinkedList<View>();
		for (int i = 0; i < getChildCount(); i++) {
			View childAt = getChildAt(i);
			if (childAt instanceof OnCollageView && childAt instanceof CollageViewClipSmilly) {
				views.add(childAt);
			}
		}
		return views;
	}

	/**
	 * Method to set the callback listener to listen the event occur inside the
	 * view group
	 * 
	 * @param collageViewGroupCallbackListener
	 */
	public void setCollageViewGroupCallbackListener(OnMKRCollageViewGroupCallbackListener collageViewGroupCallbackListener) {
		mCollageViewGroupCallbackListener = collageViewGroupCallbackListener;
	}

	// =========================================================================================================
	// OVER RIDDED METHOD
	// =========================================================================================================

	@Override
	public View onGetSelectedView() {
		return (View) mSelectedView;
	}

	@Override
	public void onApplyEffect(int effect, boolean isOnSingalImage) {
		if (isOnSingalImage) {
			if (mSelectedView != null && !(mSelectedView instanceof CollageViewClipSmilly)) {
				mSelectedView.onApplyEffect(effect);
			}
		} else {
			for (int i = 0; i < getChildCount(); i++) {
				if (getChildAt(i) instanceof OnCollageView && !(getChildAt(i) instanceof CollageViewClipSmilly)) {
					((OnCollageView) getChildAt(i)).onApplyEffect(effect);
				}
			}
		}
	}

	@Override
	public void onFlipVerticall() {
		if (mSelectedView != null) {
			mSelectedView.onFlipVerticall();
		}
	}

	@Override
	public void onFlipHorizontally() {
		if (mSelectedView != null) {
			mSelectedView.onFlipHorizontally();
		}
	}

	@Override
	public void onDeleteItem() {
		if (mSelectedView != null) {
			mSelectedView.onUnselected();
			if (!(mSelectedView instanceof CollageViewClipSmilly)) {
				if (mCollageType == COLLAGE_TYPE_FIXED) {
					mCollageLayoutType = LAYOUT_A1;
					LinkedList<View> collageViews = getCollageViews();
					for (int i = 0; i < collageViews.size(); i++) {
						collageViews.get(i).layout(INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT);
					}
				}
			}
			removeView((View) mSelectedView);
			ImageCache.getInstance(getContext()).clear(mSelectedView.onGetImageData());
			mSelectedView = null;
			if (mCollageViewGroupCallbackListener != null) {
				mCollageViewGroupCallbackListener.onMKRCollageViewGroupOnItemUnselected();
			}
			invalidate();
		}
	}

	@Override
	public void onSwapImageItem() {
		if (mSelectedView != null && !(mSelectedView instanceof CollageViewClipSmilly)) {
			mIsSwapped = true;
		} else {
			Toast.makeText(getContext(), "Invalid Operation", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public Bitmap onGetCollageBitmap(int width, int height) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawColor(Color.argb(0, 0, 0, 0));
		for (int i = 0; i < getChildCount(); i++) {
			if (getChildAt(i) instanceof OnCollageView) {
				View view = getChildAt(i);
				int viewWidth = (int) ((float) (view.getWidth() * width) / (float) getWidth());
				int viewHeight = (int) ((float) (view.getHeight() * height) / (float) getHeight());

				Bitmap viewBitmap = ((OnCollageView) view).onGetCollageItemBitmap(viewWidth, viewHeight);
				int xTr = (int) ((float) (view.getLeft() * width) / (float) getWidth());
				int yTr = (int) ((float) (view.getTop() * height) / (float) getHeight());
				canvas.translate(xTr, yTr);
				canvas.drawBitmap(viewBitmap, ((OnCollageView) view).onGetViewMatrix(), paint);
				canvas.translate(-xTr, -yTr);
				viewBitmap.recycle();
			}
		}
		return bitmap;
	}

	@Override
	public void onChangeCollageLayout(int layoutType) {
		mCollageLayoutType = layoutType;
		LinkedList<View> collageViews = getCollageViews();
		for (int i = 0; i < collageViews.size(); i++) {
			collageViews.get(i).layout(INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT);
		}
		requestLayout();
	}

	@Override
	public void onChangeCollageType(int collageType) {
		mCollageType = collageType;
		LinkedList<View> collageViews = getCollageViews();
		for (int i = 0; i < collageViews.size(); i++) {
			collageViews.get(i).layout(INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT);
		}
		requestLayout();
	}

	@Override
	public void onChangeCollageRatio() {
		for (int i = 0; i < getChildCount(); i++) {
			getChildAt(i).layout(INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT);
		}
		requestLayout();
	}

	@Override
	public void onAddCollageImageItem(LinkedList<ImageData> imageItemsList) {
		LayoutChildUtils.addCollageImageItem(this, imageItemsList, mCollageType, mCollageLayoutType);
	}

	@Override
	public void onAddCollageClipItem(ImageData imageItem) {
		if (mSelectedView != null) {
			mSelectedView.onUnselected();
		}
		CollageViewClipSmilly collageViewClipSmilly = new CollageViewClipSmilly(getContext(), imageItem);
		addView(collageViewClipSmilly);
		collageViewClipSmilly.layout(INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT);
		invalidate();
	}

	@Override
	public void onAddCollageClipArt(ImageData imageItem) {
		if (mSelectedView != null) {
			mSelectedView.onUnselected();
		}
		CollageViewClipSmilly collageViewClipSmilly = new CollageViewClipSmilly(getContext(), imageItem);
		addView(collageViewClipSmilly);
		collageViewClipSmilly.layout(INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT);
		invalidate();
	}

	@Override
	public void onSetCollageFrameBackground(ImageData imageData) {
		Toast.makeText(getContext(), "FRAME BACKGROUND", Toast.LENGTH_SHORT).show();
	}

	@Override
	public OnCollageView onGetCollageViewItem(MotionEvent event) {
		for (int i = getChildCount() - 1; i >= 0; i--) {
			View view = getChildAt(i);
			if (view instanceof OnCollageView && ((OnCollageView) view).onIsViewTapped(event)) {
				return (OnCollageView) view;
			}
		}
		return null;
	}

	@Override
	public void onSetCollageItemBorder(float border) {
		LinkedList<View> collageViews = getCollageViews();
		for (int i = 0; i < collageViews.size(); i++) {
			View view = collageViews.get(i);
			view.setScaleX(border);
			view.setScaleY(border);
		}
	}

	@Override
	public void onSetCollageBorder(float border) {
		setScaleX(border);
		setScaleY(border);
	}

	@Override
	public float onGetCollageItemBorder() {
		LinkedList<View> collageViews = getCollageViews();
		for (int i = 0; i < collageViews.size();) {
			View view = collageViews.get(i);
			return view.getScaleX();
		}
		return 1;
	}

	@Override
	public float onGetCollageBorder() {
		return getScaleX();
	}

	@Override
	public boolean onBackPressed() {
		if (mSelectedView != null && mSelectedView.onUnselected()) {
			if (mCollageViewGroupCallbackListener != null) {
				mCollageViewGroupCallbackListener.onMKRCollageViewGroupOnItemUnselected();
			}
			return true;
		}
		return false;
	}

	@Override
	public ImageData onGetSelectedChildData() {
		if (mSelectedView != null) {
			return mSelectedView.onGetImageData();
		}
		return null;
	}

	@Override
	public void onLockView() {
		if (mSelectedView != null) {
			mSelectedView.onLockView();
		}
	}

	@Override
	public void onUnlockView() {
		if (mSelectedView != null) {
			mSelectedView.onUnlockView();
		}
	}

	@Override
	public boolean onIsViewLocked() {
		if (mSelectedView != null) {
			return mSelectedView.onIsViewLocked();
		}
		return false;
	}

	// =========================================================================================================
	// VIEW GROUP METHOD HANDLE TOUCH EVENT
	// =========================================================================================================

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mGestureDetector.onTouchEvent(event);
		mScaleGestureDetector.onTouchEvent(event);
		if (mSelectedView != null) {
			mSelectedView.onChildMotion(event);
		}
		return true;
	}

	private class SimpleGestureEvents extends SimpleOnGestureListener implements OnScaleGestureListener {

		@Override
		public boolean onDown(MotionEvent event) {
			return true;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent event) {
			if (mIsSwapped) {
				OnCollageView tempCollageItem = onGetCollageViewItem(event);
				if (tempCollageItem != null && !(tempCollageItem instanceof CollageViewClipSmilly)) {
					ImageData imageDataTemp = tempCollageItem.onGetImageData();
					tempCollageItem.onSetImageData(mSelectedView.onGetImageData());
					mSelectedView.onSetImageData(imageDataTemp);
					mSelectedView.onUnselected();
					if (mSelectedView != null && mCollageViewGroupCallbackListener != null) {
						mSelectedView.onUnselected();
						mSelectedView = null;
						mCollageViewGroupCallbackListener.OnMKRCollageViewGroupSwapCompleted();
					}
				} else {
					Toast.makeText(getContext(), "Invalid operation", Toast.LENGTH_SHORT).show();
				}
				mIsSwapped = false;
			} else {
				if (mSelectedView != null) {
					mSelectedView.onUnselected();
				}
				mSelectedView = onGetCollageViewItem(event);
				if (mSelectedView != null && mCollageViewGroupCallbackListener != null) {
					mSelectedView.onSingalTap();
					mCollageViewGroupCallbackListener.OnMKRCollageViewGroupOnItemSelected();
				} else if (mSelectedView == null && mCollageViewGroupCallbackListener != null) {
					mCollageViewGroupCallbackListener.onMKRCollageViewGroupOnItemUnselected();
				}
			}
			return true;
		}

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			if (mSelectedView != null) {
				mSelectedView.onChildScale(detector.getScaleFactor());
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

	/**
	 * @author THE-MKR Interface to notify parent activity about the events
	 *         inside view grup
	 */
	public interface OnMKRCollageViewGroupCallbackListener {

		/**
		 * Method to notify activity that the swap is completed
		 */
		public void OnMKRCollageViewGroupSwapCompleted();

		/**
		 * Method to notify activity that the collage item is selected
		 */
		public void OnMKRCollageViewGroupOnItemSelected();

		/**
		 * Method to notify activity that the collage item is unselected
		 */
		public void onMKRCollageViewGroupOnItemUnselected();
	}

}
