package com.adroitstudio.photocollage.view.custom_view.collage;

import java.util.LinkedList;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;

import com.adroitstudio.photocollage.controller.ImageData;

public interface OnCollageViewGroup {

	/**
	 * Method to apply effect on collage image item
	 * 
	 * @param effect
	 *            Effect apply on item
	 * @param isOnSingalImage
	 *            TRUE if apply on single image item, FALSE if apply effect on
	 *            all image item
	 */
	public void onApplyEffect(int effect, boolean isOnSingalImage);

	/**
	 * Method to flip selected image item vertically
	 */
	public void onFlipVerticall();

	/**
	 * Method to flip selected image item horizontally
	 */
	public void onFlipHorizontally();

	/**
	 * Method to delete the item from collage
	 */
	public void onDeleteItem();

	/**
	 * Method to notify that user want to swap the image items
	 */
	public void onSwapImageItem();

	/**
	 * Method to get the final output of collage, Get the final bitmap of
	 * collage
	 * 
	 * @param width
	 *            width of collage in pixl
	 * @param height
	 *            height of collage in pixl
	 * @return
	 */
	public Bitmap onGetCollageBitmap(int width, int height);

	/**
	 * Method to change the layout collage
	 * 
	 * @param layoutType
	 *            Type of layout
	 */
	public void onChangeCollageRatio();

	/**
	 * Method to change the layout collage
	 * 
	 * @param layoutType
	 *            Type of layout
	 */
	public void onChangeCollageLayout(int layoutType);

	/**
	 * Method to change the collage type
	 * 
	 * @param collageType
	 *            Type of collage user want to create
	 */
	public void onChangeCollageType(int collageType);

	/**
	 * Method to add the list of collage image items
	 * 
	 * @param imageItems
	 */
	public void onAddCollageImageItem(LinkedList<ImageData> imageItems);

	/**
	 * Method to add clip image item
	 * 
	 * @param imageItem
	 */
	public void onAddCollageClipItem(ImageData imageItem);

	/**
	 * Method to add clip Art
	 * 
	 * @param imageItem
	 */
	public void onAddCollageClipArt(ImageData imageItem);

	/**
	 * Method to set the background of frame
	 * 
	 * @param imageData
	 */
	public void onSetCollageFrameBackground(ImageData imageData);

	/**
	 * Method to get the collage view item
	 * 
	 * @param event
	 *            Motion event occur on view group
	 * @return
	 */
	public OnCollageView onGetCollageViewItem(MotionEvent event);

	/**
	 * Method to notify view group that backpressed event is occurred
	 * 
	 * @return TRUE to block the event , otherwise FALSE
	 */
	public boolean onBackPressed();

	/**
	 * Method to get the image data of the Selected Image
	 * 
	 * @return
	 */
	public ImageData onGetSelectedChildData();

	/**
	 * Method to set the collage item border width
	 * 
	 * @param border
	 */
	public void onSetCollageItemBorder(float border);

	/**
	 * Method to set the collage border width
	 * 
	 * @param border
	 */
	public void onSetCollageBorder(float border);

	/**
	 * Method to get collage item border
	 * 
	 * @return
	 */
	public float onGetCollageItemBorder();

	/**
	 * Method to get collage border
	 * 
	 * @return
	 */
	public float onGetCollageBorder();

	/**
	 * Method to lock the view at its position
	 * 
	 * @return
	 */
	public void onLockView();

	/**
	 * Method to unlock the view
	 * 
	 * @return
	 */
	public void onUnlockView();

	/**
	 * Method to know weather the view is lock or not
	 * 
	 * @return TRUE if view is lock at its position
	 */
	public boolean onIsViewLocked();

	/**
	 * Method to get the selected view
	 * 
	 * @return
	 */
	public View onGetSelectedView();

}
