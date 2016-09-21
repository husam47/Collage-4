package com.themkrworld.collage.ui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.themkrworld.collage.cache.ImageCache;
import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.cache.ImageLoader;
import com.themkrworld.collage.utils.Utils;


public class ThumbImageView extends View implements ImageLoader.OnImageLoaded {
    private ImageData mImageData;
    private ImageCache mImageCache;
    private ImageLoader mImageLoader;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private Paint mPaint;
    private boolean mIsShowFullImage;

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

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mImageLoader.clear(mImageData);
        mBitmap = null;
    }

    /**
     * Method to init the View
     */
    private void init() {
        mImageCache = ImageCache.getInstance(getContext());
        mImageLoader = ImageLoader.getInstance(getContext());
        mMatrix = new Matrix();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
    }

    /**
     * Method to set the image data
     *
     * @param imageData
     */
    public void setImageData(ImageData imageData) {
        setImageData(imageData, false);
    }

    /**
     * Method to set the image data
     *
     * @param imageData
     * @param isShowFullImage
     */
    public void setImageData(ImageData imageData, boolean isShowFullImage) {
        mIsShowFullImage = isShowFullImage;
        if (imageData != null) {
            if (mImageLoader != null) {
                mImageLoader.clear(mImageData);
            }
            mImageData = imageData;
            if (mImageCache.isContain(mImageData)) {
                setBitmap(mImageCache.getBitmap(mImageData));
            } else {
                setBitmap(Utils.getDefaultThumbBitmap(getContext()));
                mImageLoader.execute(mImageData, this);
            }
        } else {
            setBitmap(Utils.getDefaultThumbBitmap(getContext()));
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap == null || mBitmap.isRecycled()) {
            setBitmap(Utils.getDefaultThumbBitmap(getContext()));
            mImageLoader.execute(mImageData, this);
        }
        if (mImageCache.isContain(mImageData)) {
            setBitmap(mImageCache.getBitmap(mImageData));
        } else {
            setBitmap(Utils.getDefaultThumbBitmap(getContext()));
            mImageLoader.execute(mImageData, this);
        }
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
    }

    @Override
    public void onImageLoaded(Bitmap bitmap, ImageData imageData) {
        if (bitmap == null || bitmap.isRecycled() || !mImageData.equals(imageData)) {
            setBitmap(Utils.getDefaultThumbBitmap(getContext()));
            mImageLoader.execute(mImageData, this);
        } else {
            setBitmap(bitmap);
        }
        invalidate();
    }

    /**
     * Method to set the Bitmap
     *
     * @param bitmap
     */
    private void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
        mMatrix.reset();
        float[] matrixValues = new float[9];
        mMatrix.getValues(matrixValues);
        float scaleFactor = 1;
        if (mIsShowFullImage) {
            scaleFactor = Math.min((float) getWidth() / (float) bitmap.getWidth(), (float) getHeight() / (float) bitmap.getHeight());
        } else {
            scaleFactor = Math.max((float) getWidth() / (float) bitmap.getWidth(), (float) getHeight() / (float) bitmap.getHeight());
        }
        matrixValues[Matrix.MSCALE_X] = scaleFactor;
        matrixValues[Matrix.MSCALE_Y] = scaleFactor;
        matrixValues[Matrix.MTRANS_X] = ((float) getWidth() - ((float) bitmap.getWidth() * scaleFactor)) / 2F;
        matrixValues[Matrix.MTRANS_Y] = ((float) getHeight() - ((float) bitmap.getHeight() * scaleFactor)) / 2F;
        mMatrix.setValues(matrixValues);
    }
}
