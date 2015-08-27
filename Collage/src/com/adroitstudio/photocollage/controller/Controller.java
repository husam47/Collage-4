package com.adroitstudio.photocollage.controller;

import java.util.LinkedList;

import com.adroitstudio.photocollage.Constants;

public class Controller implements Constants {

	private int mCollageType;
	private static Controller mController;
	private LinkedList<ImageData> mSelectedImage;
	private int mShapeThumbIndex;

	/**
	 * Contructor
	 */
	private Controller() {
		mSelectedImage = new LinkedList<ImageData>();
	}

	/**
	 * Method to set the index of shape
	 * 
	 * @param shapeThumbIndex
	 *            Shape thumb index
	 */
	public void setThumbShapeName(int shapeThumbIndex) {
		mShapeThumbIndex = shapeThumbIndex;
	}

	/**
	 * Method to get the index of selected thumb
	 * 
	 * @return index of select thumb
	 */
	public int getThumbShapeIndex() {
		return mShapeThumbIndex;
	}

	/**
	 * Method to get the instance of Controller
	 * 
	 * @return
	 */
	public static Controller getInstance() {
		return (mController == null) ? (mController = new Controller()) : mController;
	}

	/**
	 * Method to add new pic
	 * 
	 * @param imageData
	 */
	public void addSelectedPic(LinkedList<ImageData> selectedImageData) {
		mSelectedImage.clear();
		mSelectedImage.addAll(selectedImageData);
	}

	/**
	 * Method to get the list of selected image
	 * 
	 * @return
	 */
	public LinkedList<ImageData> getSelectedImage() {
		return mSelectedImage;
	}

	/**
	 * Method to set the collage type user want to create
	 * 
	 * @param collageType
	 */
	public void setCollageType(int collageType) {
		this.mCollageType = collageType;
	}

	/**
	 * Method to get the collage type user want to create
	 * 
	 * @return
	 */
	public int getCollageType() {
		return mCollageType;
	}
}
