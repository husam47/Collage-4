package com.adroitstudio.photocollage.view.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.adroitstudio.photocollage.Utils;
import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.controller.ImageLoader;
import com.adroitstudio.photocollage.controller.ImageLoader.OnImageLoaded;

public class ThumbImageView extends ImageView implements OnImageLoaded {

	private ImageData mImageData;
	private ImageCache mImageCache;
	private ImageLoader mImageLoader;
	private OnImageCurropted mImageCurropted;

	public ThumbImageView(Context context) {
		super(context);
		init();
	}

	public ThumbImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ThumbImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		mImageCache = ImageCache.getInstance(getContext());
		mImageLoader = ImageLoader.getInstance(getContext());
	}

	@Override
	protected void onDetachedFromWindow() {
		mImageLoader.clear(mImageData);
		mImageCache.clear(mImageData);
		super.onDetachedFromWindow();
	}

	/**
	 * Method to set the image data
	 * 
	 * @param imageData
	 */
	public void setImageData(ImageData imageData) {
		if (imageData != null) {
			mImageData = imageData;
			if (mImageCache.isContain(mImageData)) {
				setImageBitmap(mImageCache.getBitmap(mImageData));
			} else {
				setImageBitmap(Utils.getDefaultThumbBitmap(getContext()));
				mImageLoader.execute(mImageData, this);
			}
		}
	}

	/**
	 * Method to set the image data
	 * 
	 * @param imageData
	 * @param imageCurropted
	 *            Listener to notify about the garbage image
	 */
	public void setImageData(ImageData imageData, OnImageCurropted imageCurropted) {
		mImageCurropted = imageCurropted;
		if (imageData != null) {
			mImageData = imageData;
			if (mImageCache.isContain(mImageData)) {
				setImageBitmap(mImageCache.getBitmap(mImageData));
			} else {
				setImageBitmap(Utils.getDefaultThumbBitmap(getContext()));
				mImageLoader.execute(mImageData, this);
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if (drawable != null && drawable instanceof BitmapDrawable) {
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
			if (bitmap == null || bitmap.isRecycled() || !mImageCache.isContain(mImageData) || bitmap.equals(Utils.getDefaultThumbBitmap(getContext()))) {
				setImageBitmap(Utils.getDefaultThumbBitmap(getContext()));
				mImageLoader.execute(mImageData, this);
			} else {
				setImageBitmap(mImageCache.getBitmap(mImageData));
			}
		} else {
			setImageBitmap(Utils.getDefaultThumbBitmap(getContext()));
			mImageLoader.execute(mImageData, this);
		}
		try {
			super.onDraw(canvas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onImageLoaded(Bitmap bitmap, ImageData imageData) {
		if (mImageData.equals(imageData) && bitmap == null) {
			if (mImageCurropted != null) {
				mImageCurropted.onImageCurropted(mImageData);
				mImageLoader.clear(mImageData);
			}
		} else if (bitmap == null || bitmap.isRecycled() || !mImageData.equals(imageData)) {
			setImageBitmap(Utils.getDefaultThumbBitmap(getContext()));
			mImageLoader.execute(mImageData, this);
		}
		setImageBitmap(bitmap);
	}

	/**
	 * @author THE-MKR Listener to notify that the image is curropted
	 */
	public interface OnImageCurropted {
		/**
		 * Method to return the data of curropted image
		 * 
		 * @param imageData
		 */
		public void onImageCurropted(ImageData imageData);
	}

}
