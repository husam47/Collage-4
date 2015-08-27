package com.adroitstudio.photocollage.controller;

import java.util.Hashtable;
import java.util.LinkedList;

import com.adroitstudio.photocollage.view.custom_view.collage.CollageViewFixed;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

public class ImageLoader {
	private static ImageLoader mImageLoader;
	private LinkedList<ImageData> mQuery;
	private Hashtable<ImageData, OnImageLoaded> mListenerList;
	private CustomHandler mCustomHandler;
	private Worker mWorker;
	private ImageCache mImageCache;
	private static final Object SYN_OBJECT = new Object();

	private ImageLoader(Context context) {
		mImageCache = ImageCache.getInstance(context);
		mCustomHandler = new CustomHandler();
		mQuery = new LinkedList<ImageData>();
		mListenerList = new Hashtable<ImageData, OnImageLoaded>();
		mWorker = new Worker();
	}

	/**
	 * Method to get the instance on Loader
	 * 
	 * @param context
	 * @return
	 */
	public static ImageLoader getInstance(Context context) {
		return (mImageLoader == null) ? (mImageLoader = new ImageLoader(context)) : mImageLoader;
	}

	/**
	 * Method to load the image in image cache
	 * 
	 * @param imageData
	 *            image path
	 * @param imageLoaded
	 */
	public void execute(ImageData imageData, OnImageLoaded imageLoaded) {
		if (imageData != null) {
			if (!mQuery.contains(imageData)) {
				mQuery.add(imageData);
			}
			mListenerList.put(imageData, imageLoaded);
			if (!mWorker.isAlive()) {
				mWorker = new Worker();
				mWorker.start();
			}
		}
	}

	/**
	 * Method to clear specific query
	 * 
	 * @param imageData
	 *            image path
	 */
	public void clear(ImageData imageData) {
		try {
			synchronized (SYN_OBJECT) {
				mQuery.remove(imageData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to clear all query
	 * 
	 */
	public void clear() {
		try {
			synchronized (SYN_OBJECT) {
				mQuery.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Class to send data through message in handler
	 * 
	 * @author THE-MKR
	 * 
	 */
	private class Data {
		private Bitmap mBitmap;
		private OnImageLoaded mImageLoadingComplete;
		private ImageData mImageData;

		public Data(ImageData imageData, Bitmap bitmap, OnImageLoaded imageLoadingComplete) {
			mBitmap = bitmap;
			mImageLoadingComplete = imageLoadingComplete;
			mImageData = imageData;
		}

		/**
		 * Get bitmap
		 * 
		 * @return
		 */
		public Bitmap getBitmap() {
			return mBitmap;
		}

		/**
		 * get Listener
		 * 
		 * @return
		 */
		public OnImageLoaded getImageLoadingComplete() {
			return mImageLoadingComplete;
		}

		/**
		 * Get image data
		 * 
		 * @return
		 */
		public ImageData getImageData() {
			return mImageData;
		}

	}

	/**
	 * Custom Handler to link b/w BitmapLoader to UI
	 * 
	 * @author THE-MKR
	 * 
	 */
	private static class CustomHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			try {
				if (msg != null && msg.obj != null && msg.obj instanceof Data) {
					Data data = (Data) msg.obj;
					data.getImageLoadingComplete().onImageLoaded(data.getBitmap(), data.getImageData());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Thread to load bitmap
	 * 
	 * @author THE-MKR
	 */
	private class Worker extends Thread {
		@Override
		public void run() {
			while (mQuery.size() > 0) {
				synchronized (SYN_OBJECT) {
					ImageData key = null;
					try {
						key = mQuery.getFirst();
						final Bitmap bitmap = mImageCache.getBitmap(key);
						Message message = new Message();
						message.obj = new Data(key, bitmap, mListenerList.get(key));
						mCustomHandler.sendMessage(message);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						mQuery.remove(key);
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Interface to notify that the image is loaded in the cachay
	 * 
	 * @author THE-MKR
	 */
	public interface OnImageLoaded {
		public void onImageLoaded(Bitmap bitmap, ImageData imageData);
	}
}
