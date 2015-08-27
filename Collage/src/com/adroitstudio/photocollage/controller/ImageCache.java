package com.adroitstudio.photocollage.controller;

import java.util.LinkedList;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.util.Log;

import com.adroitstudio.photocollage.AssetsUtil;
import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.Utils;
import com.themkr.nativeeffect.NativeEffects;

public class ImageCache implements Constants {

	private static ImageCache imageCache;
	private MKRLruCache cache;
	private LinkedList<ImageData> mKeys;
	private int mImageThumSize, mImageSize1234, mImageSize56789;
	private Options mOptions;
	private AssetManager mAssetManager;
	private ContentResolver mContentResolver;
	private Context mContext;

	/**
	 * Image Cache Constructor
	 * 
	 * @param context
	 */
	private ImageCache(Context context) {
		mContext = context;
		cache = new MKRLruCache(getMaxSize());
		mKeys = new LinkedList<ImageData>();
		mOptions = new Options();
		mOptions.inPreferredConfig = Config.ARGB_8888;
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		float minDimension = (float) (displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels);
		mImageSize56789 = mImageThumSize = (int) (minDimension * 0.2F);
		mImageSize1234 = (int) (minDimension * 0.3F);
		mAssetManager = context.getAssets();
		mContentResolver = context.getContentResolver();
	}

	/**
	 * Method to get Image Cache instance
	 * 
	 * @param context
	 * @return Image Cache
	 */
	public static ImageCache getInstance(Context context) {
		return imageCache = (imageCache == null ? new ImageCache(context) : imageCache);
	}

	/**
	 * Method to get MaxSize for cache
	 * 
	 * @return
	 */
	private int getMaxSize() {
		return (int) (Runtime.getRuntime().maxMemory() / 4);
	}

	/**
	 * Method to get bitmap
	 * 
	 * @param key
	 * @return Return bitmap if cache contain the bitmap else return Default
	 *         bitmap
	 */
	public Bitmap getBitmap(ImageData key) {
		return cache.get(key);
	}

	/**
	 * Method weather the image cache contain the key or note
	 * 
	 * @param key
	 * @return return TRUE in image cache have bitmap else return FALSE
	 */
	public boolean isContain(ImageData key) {
		return mKeys.contains(key);
	}

	/**
	 * Method to clear cachay
	 */
	public void clear() {
		cache.evictAll();
		mKeys.clear();
	}

	/**
	 * Method to remove data from cache
	 * 
	 * @param path
	 */
	public void clear(ImageData path) {
		if (path != null) {
			cache.remove(path);
			mKeys.remove(path);
		}
	}

	/**
	 * @author THE-MKR LRU CACHAY
	 */
	private class MKRLruCache extends LruCache<ImageData, Bitmap> {

		public MKRLruCache(int maxSize) {
			super(getMaxSize());
		}

		@Override
		protected int sizeOf(ImageData key, Bitmap value) {
			return value.getHeight() * value.getWidth() * 4;
		}

		@Override
		protected void entryRemoved(boolean evicted, ImageData key, Bitmap oldValue, Bitmap newValue) {
			mKeys.remove(key);
			if (oldValue != null) {
				oldValue.recycle();
			}
			oldValue = null;
		}

		/**
		 * Method to create bitmap
		 */
		protected Bitmap create(ImageData key) {
			Bitmap bitmap = (key.getSource() == SOURCE_APPLICATION) ? createBitmapFromAssets(key) : createBitmapFromFile(key);
			if (key instanceof ClipImageData) {
				ClipImageData clipImageData = (ClipImageData) key;
				int left = (int) ((float) bitmap.getWidth() * clipImageData.getmLeft());
				int top = (int) ((float) bitmap.getHeight() * clipImageData.getmTop());
				int width = (int) ((float) bitmap.getWidth() * clipImageData.getmRight()) - left;
				int height = (int) ((float) bitmap.getHeight() * clipImageData.getmBottam()) - top;
				// CREATE A SECTOR OF IMAGE
				Bitmap bitmapImage = Bitmap.createBitmap(bitmap, left, top, width, height);
				if (!bitmapImage.equals(bitmap)) {
					bitmap.recycle();
				}
				// BITMAP SHAPE
				Bitmap bitmapShapeTemp = null;
				if (clipImageData.getClipImage() != null) {
					bitmapShapeTemp = getBitmap(clipImageData.getClipImage());
				} else {
					bitmapShapeTemp = Utils.getPathBitmap(clipImageData.getClipPath(), bitmapImage.getWidth(), bitmapImage.getHeight());
				}
				// CREATE SCALED IMAGE
				Bitmap bitmapShape = Bitmap.createScaledBitmap(bitmapShapeTemp, bitmapImage.getWidth(), bitmapImage.getHeight(), true);
				// CREATE A SHAPPED BITMAP
				Bitmap bitmapFinal = Bitmap.createBitmap(bitmapImage.getWidth(), bitmapImage.getHeight(), Config.ARGB_8888);
				Canvas canvas = new Canvas(bitmapFinal);
				canvas.drawColor(Color.argb(0, 0, 0, 0));
				Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
				canvas.drawBitmap(bitmapImage, 0, 0, paint);
				paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
				canvas.drawBitmap(bitmapShape, 0, 0, paint);
				// RECYCLE BITMAP
				bitmapImage.recycle();
				bitmapShape.recycle();
				bitmap = bitmapFinal;
			}

			if (key.getEffect() != NativeEffects.NO_EFFECT) {
				Bitmap bitmapNew = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
				NativeEffects.getEffectedBitmap(mContext, bitmap, bitmapNew, key.getEffect());
				bitmap.recycle();
				bitmap = bitmapNew;
			}

			if (key.isHorizontalFlip()) {
				Bitmap flipBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
				Canvas canvas = new Canvas(flipBitmap);
				Matrix flipHorizontalMatrix = new Matrix();
				flipHorizontalMatrix.setScale(-1, 1);
				flipHorizontalMatrix.postTranslate(bitmap.getWidth(), 0);
				canvas.drawBitmap(bitmap, flipHorizontalMatrix, new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
				bitmap.recycle();
				bitmap = flipBitmap;
			}
			if (key.isVerticalFlip()) {
				Bitmap flipBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
				Canvas canvas = new Canvas(flipBitmap);
				Matrix flipHorizontalMatrix = new Matrix();
				flipHorizontalMatrix.setScale(1, -1);
				flipHorizontalMatrix.postTranslate(0, bitmap.getHeight());
				canvas.drawBitmap(bitmap, flipHorizontalMatrix, new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
				bitmap.recycle();
				bitmap = flipBitmap;
			}
			if (bitmap != null && !bitmap.isRecycled()) {
				mKeys.add(key);
			}
			return bitmap;
		}

		/**
		 * Method to create bitmap from assets
		 * 
		 * @param key
		 * @return
		 */
		private Bitmap createBitmapFromAssets(ImageData key) {
			Bitmap bitmap = null;
			mOptions.inJustDecodeBounds = true;
			mOptions.inSampleSize = 1;
			try {
				BitmapFactory.decodeStream(AssetsUtil.open(mAssetManager, key.getPath()), null, mOptions);
				switch (key.getImageType()) {
				case IMAGE_TYPE_THUMBNAIL_MICRO:
					mOptions.inSampleSize = Utils.calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageThumSize, mImageThumSize);
					break;
				case IMAGE_TYPE_SIZE_1234:
					mOptions.inSampleSize = Utils.calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageSize1234, mImageSize1234);
					break;
				case IMAGE_TYPE_SIZE_56789:
					mOptions.inSampleSize = Utils.calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageSize56789, mImageSize56789);
					break;
				case IMAGE_TYPE_FULL_SIZE:
				default:
					mOptions.inSampleSize = 1;
					break;
				}
				mOptions.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeStream(AssetsUtil.open(mAssetManager, key.getPath()), null, mOptions);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		/**
		 * Method to create bitmap from file
		 * 
		 * @param key
		 * @return
		 */
		private Bitmap createBitmapFromFile(ImageData key) {
			Bitmap bitmap = null;
			mOptions.inJustDecodeBounds = true;
			mOptions.inSampleSize = 1;
			BitmapFactory.decodeFile(key.getPath(), mOptions);
			switch (key.getImageType()) {
			case IMAGE_TYPE_THUMBNAIL_MICRO:
				bitmap = MediaStore.Images.Thumbnails.getThumbnail(mContentResolver, key.getImageId(), Thumbnails.MICRO_KIND, null);
				if (bitmap == null || bitmap.isRecycled()) {
					mOptions.inSampleSize = Utils.calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageThumSize, mImageThumSize);
				} else {
					return bitmap;
				}
				break;
			case IMAGE_TYPE_SIZE_1234:
				mOptions.inSampleSize = Utils.calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageSize1234, mImageSize1234);
				break;
			case IMAGE_TYPE_SIZE_56789:
				mOptions.inSampleSize = Utils.calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageSize56789, mImageSize56789);
				break;
			case IMAGE_TYPE_FULL_SIZE:
			default:
				mOptions.inSampleSize = 1;
			}
			Log.e("THE-MKR", "ImageCache.MKRLruCache.createBitmapFromFile()" + "3");
			mOptions.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(key.getPath(), mOptions);
			return bitmap;
		}
	}

}
