package com.adroitstudio.photocollage.view.custom_view.collage;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.controller.ImageData;

public interface OnCollageView extends Constants {

	int INVALID_POINTER = -9999;

	/**
	 * Method call when Motion event occur for related view
	 * 
	 * @param event
	 */
	public void onChildMotion(MotionEvent event);

	/**
	 * Method call when scale the related view
	 * 
	 * @param event
	 */
	public void onChildScale(float scaleFactor);

	/**
	 * Method to apply effect on collage image item
	 * 
	 * @param imageData
	 *            Image data of new effected bitmap
	 * 
	 */
	public void onApplyEffect(int effect);

	/**
	 * Method to flip selected image item vertically
	 * 
	 * @param imageData
	 *            Image data of new flipped bitmap
	 */
	public void onFlipVerticall();

	/**
	 * Method to flip selected image item horizontally
	 * 
	 * @param imageData
	 *            Image data of new flipped bitmap
	 */
	public void onFlipHorizontally();

	/**
	 * Method to notify that child is selected
	 */
	public void onSingalTap();

	/**
	 * Method to lock view at its position
	 */
	public void onLockView();

	/**
	 * Method to unlock view
	 */
	public void onUnlockView();

	/**
	 * Method to know weather the view is lock or not
	 * @return TRUE if view is lock at its position
	 */
	public boolean onIsViewLocked();

	/**
	 * Method to notify view that back press event occur
	 * 
	 * @return TRUE to block backpress event , FALSE other wise
	 */
	public boolean onUnselected();

	/**
	 * Method to set the background of frame
	 * 
	 * @param imageData
	 */
	public void onSetCollageItemFrame(ImageData imageData);

	/**
	 * Method to set the background of frame
	 * 
	 * @param imageData
	 */
	public void onSetImageData(ImageData imageData);

	/**
	 * Method to get the actual ImageData
	 * 
	 * @return the ImageData object
	 */
	public ImageData onGetImageData();

	/**
	 * Method to get the final output of collage Item
	 * clipSmillys.onGetViewMatrix().getScaleY()
	 * 
	 * @param width
	 *            width of collage in pixl
	 * @param height
	 *            height of collage in pixl
	 * @return
	 */
	public Bitmap onGetCollageItemBitmap(int width, int height);

	/**
	 * Method to get the matrix of view
	 * 
	 * @return Matrix of view implement this, 'null' if not an instance of view
	 */
	public Matrix onGetViewMatrix();

	/**
	 * Method to get that view is tapped or not
	 * 
	 * @param event
	 *            event occur in view group
	 * @return TRUE if view is tapped otherwise FALSE
	 */
	public boolean onIsViewTapped(MotionEvent event);

}
