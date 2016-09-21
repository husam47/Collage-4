package com.themkrworld.collage.cache;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.LruCache;

import com.themkrworld.collage.effect.MKREffect;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

import java.util.Vector;

public class ImageCache {

    private static ImageCache imageCache;
    private MKRLruCache cache;
    private Vector<ImageData> mKeys;
    private int mImageThumSize, mImageCollageSize;
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
        mContext = context.getApplicationContext();
        cache = new MKRLruCache(getMaxSize());
        mKeys = new Vector<>();
        mOptions = new Options();
        mOptions.inPreferredConfig = Config.ARGB_8888;
        float width = mContext.getResources().getDisplayMetrics().widthPixels;
        mImageThumSize = (int) (width / 4F);
        mImageCollageSize = (int) (width / 3F);
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
     * bitmap
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
        private static final String TAG = AppConfig.BASE_TAG + ".MKRLruCache";

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
            Bitmap bitmap = (key.getSource().equals(ImageData.ImageSource.ASSETS)) ? createBitmapFromAssets(key) : createBitmapFromFile(key);
            if (key.getCropImageData() != null) {
                ImageData.CropImageData cropImageData = key.getCropImageData();
                int left = (int) ((float) bitmap.getWidth() * cropImageData.getCropImageDataLeft());
                int top = (int) ((float) bitmap.getHeight() * cropImageData.getCropImageDataTop());
                int width = (int) ((float) bitmap.getWidth() * cropImageData.getCropImageDataRight()) - left;
                int height = (int) ((float) bitmap.getHeight() * cropImageData.getCropImageDataBottom()) - top;
                // CREATE A SECTOR OF IMAGE
                Bitmap bitmapImage = Bitmap.createBitmap(bitmap, left, top, width, height);
                if (!bitmapImage.equals(bitmap)) {
                    bitmap.recycle();
                }
                // BITMAP SHAPE
                Bitmap bitmapShapeTemp = null;
                bitmapShapeTemp = getBitmap(cropImageData.getCropImageData());
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

            if (key.getEffect() != MKREffect.Effect.NONE) {
//                Bitmap bitmapNew = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
//                NativeEffects.getEffectedBitmap(mContext, bitmap, bitmapNew, key.getEffect());
//                bitmap.recycle();
//                bitmap = bitmapNew;
            }
//            if (key.isHorizontalFlip()) {
//                Bitmap flipBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
//                Canvas canvas = new Canvas(flipBitmap);
//                Matrix flipHorizontalMatrix = new Matrix();
//                flipHorizontalMatrix.setScale(-1, 1);
//                flipHorizontalMatrix.postTranslate(bitmap.getWidth(), 0);
//                canvas.drawBitmap(bitmap, flipHorizontalMatrix, new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
//                bitmap.recycle();
//                bitmap = flipBitmap;
//            }
//            if (key.isVerticalFlip()) {
//                Bitmap flipBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
//                Canvas canvas = new Canvas(flipBitmap);
//                Matrix flipHorizontalMatrix = new Matrix();
//                flipHorizontalMatrix.setScale(1, -1);
//                flipHorizontalMatrix.postTranslate(0, bitmap.getHeight());
//                canvas.drawBitmap(bitmap, flipHorizontalMatrix, new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
//                bitmap.recycle();
//                bitmap = flipBitmap;
//            }
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
                BitmapFactory.decodeStream(mAssetManager.open(key.getKey()), null, mOptions);
                switch (key.getType()) {
                    case THUMBNIAL:
                        mOptions.inSampleSize = calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageThumSize, mImageThumSize);
                        break;
                    case COLLAGE:
                        mOptions.inSampleSize = calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageCollageSize, mImageCollageSize);
                        break;
                    case ORIGINAL_SIZE:
                    default:
                        mOptions.inSampleSize = 1;
                        break;
                }
                mOptions.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(mAssetManager.open(key.getKey()), null, mOptions);
            } catch (Exception e) {
                e.printStackTrace();
                Tracer.error(TAG, "createBitmapFromAssets()" + e.getMessage());
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
            BitmapFactory.decodeFile(key.getKey(), mOptions);
            switch (key.getType()) {
                case THUMBNIAL:
                    mOptions.inSampleSize = calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageThumSize, mImageThumSize);
                    break;
                case COLLAGE:
                    mOptions.inSampleSize = calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, mImageCollageSize, mImageCollageSize);
                    break;
                case ORIGINAL_SIZE:
                default:
                    mOptions.inSampleSize = 1;
            }
            mOptions.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(key.getKey(), mOptions);
            return bitmap;
        }

        /**
         * Method to get the sample size of bitmap
         *
         * @param srcWidth      src bitmap width
         * @param srcHeight     src bitmap height
         * @param desiredWidth  dest bitmap width
         * @param desiredHeight dest bitmap height
         * @return the sample size
         */
        private int calculateInSampleSize(int srcWidth, int srcHeight, int desiredWidth, int desiredHeight) {
            int inSampleSize = 1;
            if (srcHeight > desiredHeight || srcWidth > desiredWidth) {
                int heightRatio = Math.round((float) srcHeight / (float) desiredHeight);
                int widthRatio = Math.round((float) srcWidth / (float) desiredWidth);
                inSampleSize = heightRatio <= widthRatio ? heightRatio : widthRatio;
            }
            return (inSampleSize % 2 == 0) ? inSampleSize : (inSampleSize - 1);
        }
    }

}
