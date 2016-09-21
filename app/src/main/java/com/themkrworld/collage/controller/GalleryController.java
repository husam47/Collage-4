package com.themkrworld.collage.controller;

import android.content.Context;
import android.os.AsyncTask;

import com.themkrworld.collage.asynctask.GalleryInitializer;
import com.themkrworld.collage.model.Gallery;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

/**
 * Class to creat the list of all the images
 */
public class GalleryController implements GalleryInitializer.OnGalleryInitializerListener {
    private static final String TAG = AppConfig.BASE_TAG + ".GalleryController";
    private Context mContext;
    private OnGalleryControllerListener mOnGalleryControllerListener;

    /**
     * Constructor
     *
     * @param context
     * @param onGalleryControllerListener
     */
    public GalleryController(Context context, OnGalleryControllerListener onGalleryControllerListener) {
        Tracer.debug(TAG, "GalleryController()");
        mContext = context;
        mOnGalleryControllerListener = onGalleryControllerListener;
    }

    /**
     * Method to initialize the Gallery
     */
    public void initialieGallery() {
        Tracer.debug(TAG, "initialieGallery()");
        new GalleryInitializer(mContext, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onGalleryInitializerListenerSuccess(Gallery gallery) {
        if (mOnGalleryControllerListener != null) {
            mOnGalleryControllerListener.onGalleryControllerGalleryInitialized(gallery);
        }
    }

    /**
     * Callback to listen the Gallery Initialization operation
     */
    public interface OnGalleryControllerListener {
        /**
         * Callback to notify that Gallery is initialize and we can show it to the User
         */
        public void onGalleryControllerGalleryInitialized(Gallery gallery);
    }
}
