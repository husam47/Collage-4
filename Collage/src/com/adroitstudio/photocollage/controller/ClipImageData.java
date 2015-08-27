package com.adroitstudio.photocollage.controller;

import com.adroitstudio.photocollage.MKRPath;

public class ClipImageData extends ImageData {

	private float mLeft, mTop, mRight, mBottam;
	private MKRPath mClipPath;
	private ImageData mClipImage;

	/**
	 * 
	 * @param path
	 *            Path of Src Image
	 * @param imageId
	 *            ID of Src Image
	 * @param source
	 *            Src destination
	 * @param imageType
	 *            Type of image
	 * @param effect
	 *            Effect apply on image
	 * @param left
	 *            Left of Clip in Src image
	 * @param top
	 *            Top of Clip in Src image
	 * @param right
	 *            Right of Clip in Src image
	 * @param bottam
	 *            Bottom of Clip in Src image
	 * @param clipPath
	 *            Clip Path
	 * @param isHorizontafFlip
	 *            Is Src clip image is horizontally flliped
	 * @param isVerticalFlip
	 *            Is Src clip image is vertically flliped
	 */
	public ClipImageData(String path, long imageId, int source, int imageType, int effect, float left, float top, float right, float bottam, MKRPath clipPath, boolean isHorizontafFlip, boolean isVerticalFlip) {
		super(path, imageId, source, imageType, effect, isHorizontafFlip, isVerticalFlip);
		mLeft = left;
		mTop = top;
		mRight = right;
		mBottam = bottam;
		mClipPath = clipPath;
		mIsParent = false;
	}

	/**
	 * 
	 * @param path
	 *            Path of Src Image
	 * @param imageId
	 *            ID of Src Image
	 * @param source
	 *            Src destination
	 * @param imageType
	 *            Type of image
	 * @param effect
	 *            Effect apply on image
	 * @param left
	 *            Left of Clip in Src image
	 * @param top
	 *            Top of Clip in Src image
	 * @param right
	 *            Right of Clip in Src image
	 * @param bottam
	 *            Bottom of Clip in Src image
	 * @param clipImage
	 *            Clip Image or Shapped image
	 * @param isHorizontafFlip
	 *            Is Src clip image is horizontally flliped
	 * @param isVerticalFlip
	 *            Is Src clip image is vertically flliped
	 */
	public ClipImageData(String path, long imageId, int source, int imageType, int effect, float left, float top, float right, float bottam, ImageData clipImage, boolean isHorizontafFlip, boolean isVerticalFlip) {
		super(path, imageId, source, imageType, effect, isHorizontafFlip, isVerticalFlip);
		mLeft = left;
		mTop = top;
		mRight = right;
		mBottam = bottam;
		mClipImage = clipImage;
		mIsParent = false;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ClipImageData) {
			ClipImageData clipImageData = (ClipImageData) o;
			if (((mClipPath != null) && (mClipPath.equals(clipImageData.mClipPath))) || ((mClipImage != null) && (mClipImage.equals(clipImageData.mClipImage)))) {
				if ((mLeft == clipImageData.mLeft) && (mTop == clipImageData.mTop) && (mRight == clipImageData.mRight) && (mBottam == clipImageData.mBottam) && mPath.equals(clipImageData.mPath) && mSource == clipImageData.mSource && mImageType == clipImageData.mImageType && mEffect == clipImageData.mEffect && mIsHorizontalFlipped == clipImageData.mIsHorizontalFlipped && mIsVerticalFlipped == clipImageData.mIsVerticalFlipped) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return mPath.hashCode();
	}

	// =====================================================================================================
	// GETTERS
	// =====================================================================================================

	/**
	 * Method to get the left clip point
	 * 
	 * @return left clip point in percent, 0-1
	 */
	public float getmLeft() {
		return mLeft;
	}

	/**
	 * Method to get the top clip point
	 * 
	 * @return top clip point in percent, 0-1
	 */
	public float getmTop() {
		return mTop;
	}

	/**
	 * Method to get the right clip point
	 * 
	 * @return right clip point in percent, 0-1
	 */
	public float getmRight() {
		return mRight;
	}

	/**
	 * Method to get the bottom clip point
	 * 
	 * @return bottom clip point in percent, 0-1
	 */
	public float getmBottam() {
		return mBottam;
	}

	/**
	 * Method to get the clip path
	 * 
	 * @return clip path is depending on device
	 */
	public MKRPath getClipPath() {
		return mClipPath;
	}

	/**
	 * Method to get the clip image
	 * 
	 * @return
	 */
	public ImageData getClipImage() {
		return mClipImage;
	}
}
