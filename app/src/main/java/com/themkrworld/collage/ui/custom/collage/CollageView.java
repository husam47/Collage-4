package com.themkrworld.collage.ui.custom.collage;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;

import com.themkrworld.collage.cache.ImageData;

/**
 * Created by delhivery on 21/9/16.
 */
public abstract class CollageView {

    protected static final int INVALID_POINTER = -9999;

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

}
