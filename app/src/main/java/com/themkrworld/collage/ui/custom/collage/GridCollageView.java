package com.themkrworld.collage.ui.custom.collage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

/**
 * Created by delhivery on 22/9/16.
 */
public class GridCollageView extends CollageView {
    private static final String TAG = AppConfig.BASE_TAG + ".GridCollageView";

    public GridCollageView(Context context) {
        super(context);
        Tracer.debug(TAG, "GridCollageView()");
    }

    public GridCollageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Tracer.debug(TAG, "GridCollageView()");
    }

    public GridCollageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Tracer.debug(TAG, "GridCollageView()");
    }

    public GridCollageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Tracer.debug(TAG, "GridCollageView()");
    }

    @Override
    public void collageViewMotion(MotionEvent event) {

    }

    @Override
    public void collageViewScale(float scaleFactor) {

    }

    @Override
    public void collageViewFlipVertically() {

    }

    @Override
    public void collageViewFlipHorizontally() {

    }

    @Override
    public void collageViewSingalTap() {

    }

    @Override
    public void collageViewLockView() {

    }

    @Override
    public void collageViewUnlockView() {

    }

    @Override
    public boolean collageViewIsViewLocked() {
        return false;
    }

    @Override
    public boolean collageViewUnSelected() {
        return false;
    }

    @Override
    public void collageViewSetImageData(ImageData imageData) {

    }

    @Override
    public ImageData collageViewGetImageData() {
        return null;
    }

    @Override
    public Bitmap collageViewGetCollageItemBitmap(int width, int height) {
        return null;
    }

    @Override
    public Matrix collageViewGetViewMatrix() {
        return null;
    }

    @Override
    public boolean collageViewIsViewTapped(MotionEvent event) {
        return false;
    }
}
