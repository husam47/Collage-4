package com.adroitstudio.photocollage.controller;

import com.adroitstudio.photocollage.Constants;
import com.themkr.nativeeffect.NativeEffects;

public class ImageData implements Constants {

	protected String mPath;
	protected int mEffect = NativeEffects.NO_EFFECT;
	protected int mSource;
	protected int mImageType = IMAGE_TYPE_THUMBNAIL_MICRO;
	protected boolean mIsVerticalFlipped;
	protected boolean mIsHorizontalFlipped;
	protected long mImageId;
	protected boolean mIsParent;

	/**
	 * Construct the key for imageCache for specifying the image loaded in cache
	 * 
	 * @param path
	 *            Image path
	 * @param imageId
	 *            Image Id, IF image from application then -1
	 * @param source
	 *            Image source ASSETS or SD_CARD
	 * @param imageType
	 *            specifythe size of bitmap
	 */
	public ImageData(String path, long imageId, int source, int imageType) {
		mPath = path;
		mImageId = imageId;
		mSource = source;
		mImageType = imageType;
		mIsParent = true;
	}

	/**
	 * Construct the key for imageCache for specifying the image loaded in cache
	 * 
	 * @param path
	 *            Image path
	 * @param imageId
	 *            Image Id, IF image from application then -1
	 * @param source
	 *            Image source ASSETS or SD_CARD
	 * @param imageType
	 *            specifythe size of bitmap
	 * @param effect
	 *            specify the effect apply on image
	 */
	public ImageData(String path, long imageId, int source, int imageType, int effect) {
		this(path, imageId, source, imageType);
		mEffect = effect;
	}

	/**
	 * Construct the key for imageCache for specifying the image loaded in cache
	 * 
	 * @param path
	 *            Image path
	 * @param imageId
	 *            Image Id, IF image from application then -1
	 * @param source
	 *            Image source ASSETS or SD_CARD
	 * @param imageType
	 *            specifythe size of bitmap
	 * @param effect
	 *            specify the effect apply on image
	 * @param isHorizontalFlip
	 *            set true if pic is horizontal flipped
	 * @param isVerticalFlip
	 *            set true if pic is vertically flipped
	 */
	public ImageData(String path, long imageId, int source, int imageType, int effect, boolean isHorizontalFlip, boolean isVerticalFlip) {
		this(path, imageId, source, imageType, effect);
		mEffect = effect;
		mIsHorizontalFlipped = isHorizontalFlip;
		mIsVerticalFlipped = isVerticalFlip;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ImageData) {
			ImageData key = (ImageData) o;
			if ((mIsParent == key.mIsParent) && (mPath.equals(key.mPath)) && (mSource == key.mSource) && (mImageType == key.mImageType) && (mEffect == key.mEffect) && (mIsHorizontalFlipped == key.mIsHorizontalFlipped) && (mIsVerticalFlipped == key.mIsVerticalFlipped)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return mPath.hashCode();
	}

	// ====================================================================================================
	// SETTERS
	// ====================================================================================================

	/**
	 * Method to set the effect apply on image
	 * 
	 * @param effect
	 *            Effect apply on image
	 */
	public void setEffect(int effect) {
		mEffect = effect;
	}

	/**
	 * Method to flip image vertically
	 */
	public void setVerticalFlip() {
		mIsVerticalFlipped = !mIsVerticalFlipped;
	}

	/**
	 * Method to flip image Horizontally
	 */
	public void setHorizontalFlip() {
		mIsHorizontalFlipped = !mIsHorizontalFlipped;
	}

	/**
	 * Method to flip image Horizontally
	 */
	public void setImageType(int imageType) {
		mImageType = imageType;
	}

	// ====================================================================================================
	// GETTERS
	// ====================================================================================================

	/**
	 * Method to get the image ID
	 * 
	 * @return Image Id ,IF image from Application then image id is -1
	 */
	public long getImageId() {
		return mImageId;
	}

	/**
	 * Method to get the path of image
	 * 
	 * @return Image path
	 */
	public String getPath() {
		return mPath;
	}

	/**
	 * Method to get the source of image
	 * 
	 * @return source of image
	 */
	public int getSource() {
		return mSource;
	}

	/**
	 * Method to get the effect apply on image
	 * 
	 * @return Effect apply on image
	 */
	public int getEffect() {
		return mEffect;
	}

	/**
	 * Method to get the image type depend on size
	 * 
	 * @return image type
	 */
	public int getImageType() {
		return mImageType;
	}

	/**
	 * Method to know weather the image is vertical flipped or not
	 * 
	 * @return TRUE if vertical flipped otherwise FALSE
	 */
	public boolean isVerticalFlip() {
		return mIsVerticalFlipped;
	}

	/**
	 * Method to know weather the image is horizontal flipped or not
	 * 
	 * @return TRUE if horizontal flipped otherwise FALSE
	 */
	public boolean isHorizontalFlip() {
		return mIsHorizontalFlipped;
	}
}
