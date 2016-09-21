package com.themkrworld.collage.asynctask;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.model.Gallery;
import com.themkrworld.collage.ui.dialog.ProgressDialogMKR;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

/**
 * Created by delhivery on 15/9/16.
 */
public class GalleryInitializer extends AsyncTask<Void, Void, Void> {
    private static final String TAG = AppConfig.BASE_TAG + ".GalleryInitializer";
    private Context mContext;
    private ProgressDialogMKR mProgressDialogMKR;
    private OnGalleryInitializerListener mOnGalleryInitializerListener;
    private Gallery mGallery;

    public GalleryInitializer(Context context, OnGalleryInitializerListener onGalleryInitializerListener) {
        mContext = context;
        mOnGalleryInitializerListener = onGalleryInitializerListener;
        mProgressDialogMKR = new ProgressDialogMKR(mContext);
        mGallery = new Gallery();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            mProgressDialogMKR.show();
        } catch (Exception e) {
            Tracer.error(TAG, "onPreExecute()" + e.getMessage());
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        initilizeImageList();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            mProgressDialogMKR.dismiss();
        } catch (Exception e) {
            Tracer.error(TAG, "onPostExecute()" + e.getMessage());
        }
        if (mOnGalleryInitializerListener != null) {
            mOnGalleryInitializerListener.onGalleryInitializerListenerSuccess(mGallery);
        }
    }

    /**
     * Method to generate the Table of all the images in the device as per
     * there folder name This method should be called from Thread other then
     * UI thread
     */
    protected void initilizeImageList() {
        // EXTRACT EXTERNAL IMAGES
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Images.Media.DATE_ADDED + " DESC");
        if (cursor != null) {
            int colPathIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int colAlbumNameIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                String path = cursor.getString(colPathIndex);
                String bucketName = cursor.getString(colAlbumNameIndex);
                ImageData imageData = new ImageData(path, ImageData.ImageType.THUMBNIAL, ImageData.ImageSource.DEVICE_STORAGE);
                mGallery.addPicImageData(bucketName, imageData);
            }
            cursor.close();
        }
        // EXTRACT INTERNAL IMAGES
        Cursor cursorInternal = mContext.getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, null, null, null, null);
        if (cursorInternal != null) {
            int colPathIndex = cursorInternal.getColumnIndex(MediaStore.Images.Media.DATA);
            int colAlbumNameIndex = cursorInternal.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursorInternal.moveToNext()) {
                String path = cursor.getString(colPathIndex);
                String bucketName = cursor.getString(colAlbumNameIndex);
                ImageData imageData = new ImageData(path, ImageData.ImageType.THUMBNIAL, ImageData.ImageSource.DEVICE_STORAGE);
                mGallery.addPicImageData(bucketName, imageData);
            }
            cursorInternal.close();
        }
    }

    /**
     * Callback to listen the Gallery Initialization Operation status
     */
    public interface OnGalleryInitializerListener {
        /**
         * Callback to notify that Gallery is successfully Initialized
         *
         * @param gallery
         */
        public void onGalleryInitializerListenerSuccess(Gallery gallery);
    }
}