package com.themkrworld.collage.ui.custom.collage;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.themkrworld.collage.cache.ImageCache;
import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.cache.ImageLoader;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

/**
 * Created by delhivery on 21/9/16.
 */
public abstract class CollageView extends View implements ImageLoader.OnImageLoaded {
    public static final int INVALID_POINTER = -9999;
    private static final String TAG = AppConfig.BASE_TAG + ".CollageView";
    private ImageLoader mImageLoader;
    private ImageCache mImageCache;
    private ImageData mImageDataUserPic;
    private ImageData mImageDataCropper;
    private Bitmap mBitmapUserPic, mBitmapCropper, mBitmapShown;
    private PorterDuffXfermode mPorterDuffXfermode;
    private Matrix mMatrixUserPic, mMatrixCropper;
    private Paint mPaint;
    private float mVisibleLeft, mVisibleTop, mVisibleRight, mVisibleBottom;
    private boolean mIsSingleTapped;


    public CollageView(Context context) {
        super(context);
        Tracer.debug(TAG, "CollageView()");
        init();
    }

    public CollageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Tracer.debug(TAG, "CollageView()");
        init();
    }

    public CollageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Tracer.debug(TAG, "CollageView()");
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CollageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Tracer.debug(TAG, "CollageView()");
        init();
    }

    /**
     * Method to init the collage view
     */
    public void init() {
        Tracer.debug(TAG, "init()");
        mMatrixUserPic = new Matrix();
        mMatrixCropper = new Matrix();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mImageLoader = ImageLoader.getInstance(getContext());
        mImageCache = ImageCache.getInstance(getContext());
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    /**
     * Method to set the ImageData
     *
     * @param imageDataUserPic
     * @param imageDataCropper
     */
    public void collageSetImageData(ImageData imageDataUserPic, ImageData imageDataCropper) {
        mImageDataUserPic = imageDataUserPic;
        mImageDataCropper = imageDataCropper;
    }

    /**
     * Method to set the User Pic ImageData
     *
     * @param imageDataUserPic
     */
    public void collageSetImageData(ImageData imageDataUserPic) {
        mImageDataUserPic = imageDataUserPic;
    }

    /**
     * Method to set the Cropper ImageData
     *
     * @param imageDataCropper
     */
    public void collageSetCropperImageData(ImageData imageDataCropper) {
        mImageDataCropper = imageDataCropper;
    }


    @Override
    public void onImageLoaded(Bitmap bitmap, ImageData imageData) {
        Tracer.debug(TAG, "onImageLoaded()");
        if (bitmap == null || bitmap.isRecycled()) {
            mImageLoader.execute(imageData, this);
        }
        if (imageData.equals(mImageDataUserPic)) {
            mBitmapUserPic = bitmap;
        } else if (imageData.equals(mImageDataCropper)) {
            mBitmapUserPic = bitmap;
            setMatrixBitmapCroppedCropped();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmapShown != null && !mBitmapShown.isRecycled()) {
            mPaint.reset();
            mPaint.setFilterBitmap(true);
            mPaint.setAntiAlias(true);
            canvas.drawBitmap(mBitmapShown, null, mPaint);
        }
    }

    /**
     * Method call when Motion event occur for related view
     *
     * @param event
     */
    public abstract void collageViewMotion(MotionEvent event);

    /**
     * Method call when scale the related view
     */
    public abstract void collageViewScale(float scaleFactor);

    /**
     * Method to flip selected image item vertically
     */
    public abstract void collageViewFlipVertically();

    /**
     * Method to flip selected image item Horizontally
     */
    public abstract void collageViewFlipHorizontally();

    /**
     * Method to notify that child is selected
     */
    public abstract void collageViewSingalTap();

    /**
     * Method to lock view at its position
     */
    public abstract void collageViewLockView();

    /**
     * Method to unlock view
     */
    public abstract void collageViewUnlockView();

    /**
     * Method to know weather the view is lock or not
     *
     * @return TRUE if view is lock at its position
     */
    public abstract boolean collageViewIsViewLocked();

    /**
     * Method to notify view that back press event occur
     *
     * @return TRUE to block backpress event , FALSE other wise
     */
    public abstract boolean collageViewUnSelected();

    /**
     * Method to set the background of frame
     *
     * @param imageData
     */
    public abstract void collageViewSetImageData(ImageData imageData);

    /**
     * Method to get the actual ImageData
     *
     * @return the ImageData object
     */
    public abstract ImageData collageViewGetImageData();

    /**
     * Method to get the final output of collage Item
     *
     * @param width  width of collage in pixl
     * @param height height of collage in pixl
     * @return
     */
    public abstract Bitmap collageViewGetCollageItemBitmap(int width, int height);

    /**
     * Method to get the matrix of view
     *
     * @return Matrix of view implement this, 'null' if not an instance of view
     */
    public abstract Matrix collageViewGetViewMatrix();

    /**
     * Method to get that view is tapped or not
     *
     * @param event event occur in view group
     * @return TRUE if view is tapped otherwise FALSE
     */
    public abstract boolean collageViewIsViewTapped(MotionEvent event);


    /**
     * Method to create display image
     */
    private void createDisplayImage() {
        if (mBitmapShown != null) {
            mBitmapShown.recycle();
        }
        if (!(getWidth() > 0 && getHeight() > 0)) {
            return;
        }
        if (!(mBitmapUserPic != null && !mBitmapUserPic.isRecycled())) {
            mImageLoader.execute(mImageDataUserPic, this);
            return;
        }
        if (!(mBitmapCropper != null && !mBitmapCropper.isRecycled())) {
            mImageLoader.execute(mImageDataCropper, this);
            return;
        }
        mBitmapShown = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(mBitmapShown);
        canvas.drawColor(Color.argb(0, 0, 0, 0));
        mPaint.reset();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(mBitmapUserPic, mMatrixUserPic, mPaint);
        mPaint.setXfermode(mPorterDuffXfermode);
        canvas.drawBitmap(mBitmapCropper, mMatrixCropper, mPaint);
        invalidate();
    }

    /**
     * Method to set the matrix of Cropped bitmap
     */
    private void setMatrixBitmapCroppedCropped() {
        Tracer.debug(TAG, "setMatrixBitmapCroppedCropped()");
        mMatrixCropper.reset();
        float scaleFactor = 1;
        scaleFactor = Math.min((float) getWidth() / (float) mBitmapCropper.getWidth(), (float) getHeight() / (float) mBitmapCropper.getHeight());
        mMatrixCropper.setScale(scaleFactor, scaleFactor);
        setVisibleBoundaryOfCropped(scaleFactor);
    }

    /**
     * Method to set the matrix of Cropped bitmap
     *
     * @param scaleFactor
     */
    private void setVisibleBoundaryOfCropped(float scaleFactor) {
        Tracer.debug(TAG, "setVisibleBoundaryOfCropped()");
        int width = mBitmapCropper.getWidth();
        int height = mBitmapCropper.getHeight();
        mVisibleLeft = width;
        mVisibleTop = height;
        mVisibleRight = 0;
        mVisibleBottom = 0;
        boolean isTopDone = false, isBottomDone = false;
        for (int i = 0; i < width; i++) {
            boolean isLeftDone = false, isRightDone = false;
            for (int j = 0; j < height; j++) {
                if (Color.alpha(mBitmapCropper.getPixel(i, j)) > 10) {
                    // CHECK FOR LEFT
                    if (!isLeftDone && i < mVisibleLeft) {
                        mVisibleLeft = i;
                        isLeftDone = true;
                    }

                    if (isLeftDone && !isRightDone && i > mVisibleRight) {
                        mVisibleRight = i;
                        isRightDone = true;
                    }
                    if (!isTopDone && j < mVisibleTop) {
                        mVisibleTop = j;
                        isTopDone = true;
                    }

                    if (isTopDone && !isBottomDone && j > mVisibleBottom) {
                        mVisibleBottom = j;
                        isBottomDone = true;
                    }
                }
            }
        }
        // SET VISIBLE RECT BASED ON THIS VIEW WIDTH
        mVisibleTop = mVisibleTop * scaleFactor;
        mVisibleBottom = mVisibleBottom * scaleFactor;
        mVisibleLeft = mVisibleLeft * scaleFactor;
        mVisibleRight = mVisibleRight * scaleFactor;
    }
}
