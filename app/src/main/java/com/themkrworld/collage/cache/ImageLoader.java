package com.themkrworld.collage.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

import java.util.Hashtable;
import java.util.Vector;

public class ImageLoader {
    private static final String TAG = AppConfig.BASE_TAG + ".ImageLoader";
    private static ImageLoader mImageLoader;
    private Vector<ImageData> mQuery;
    private Hashtable<ImageData, Vector<OnImageLoaded>> mListenerList;
    private CustomHandler mCustomHandler;
    private Worker mWorker;
    private ImageCache mImageCache;

    private ImageLoader(Context context) {
        mImageCache = ImageCache.getInstance(context);
        mCustomHandler = new CustomHandler();
        mQuery = new Vector<ImageData>();
        mListenerList = new Hashtable<ImageData, Vector<OnImageLoaded>>();
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
     * @param imageData   image path
     * @param imageLoaded
     */
    public void execute(ImageData imageData, OnImageLoaded imageLoaded) {
        if (imageData != null) {
            if (!mQuery.contains(imageData)) {
                mQuery.add(imageData);
            }
            if (!mListenerList.containsKey(imageData)) {
                mListenerList.put(imageData, new Vector<OnImageLoaded>());
            }
            mListenerList.get(imageData).add(imageLoaded);
            if (!mWorker.isAlive()) {
                mWorker = new Worker();
                mWorker.start();
            }
        }
    }

    /**
     * Method to clear specific query
     *
     * @param imageData image path
     */
    public void clear(ImageData imageData) {
        try {
            mQuery.remove(imageData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to clear all query
     */
    public void clear() {
        try {
            mQuery.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Class to send data through message in handler
     *
     * @author THE-MKR
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
                ImageData key = null;
                try {
                    key = mQuery.get(0);
                    final Bitmap bitmap = mImageCache.getBitmap(key);
                    for (OnImageLoaded onImageLoaded : mListenerList.get(key)) {
                        try {
                            Message message = new Message();
                            message.obj = new Data(key, bitmap, onImageLoaded);
                            mCustomHandler.sendMessage(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Tracer.error(TAG, "run()" + e.getMessage());
                } finally {
                    mQuery.remove(key);
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
