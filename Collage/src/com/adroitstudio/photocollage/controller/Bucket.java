package com.adroitstudio.photocollage.controller;

import java.util.LinkedList;

public class Bucket {
	
	private LinkedList<ImageData> mImageDatas;
	private String mBucketName;

	public Bucket(String bucketName) {
		mImageDatas = new LinkedList<ImageData>();
		mBucketName = bucketName;
	}

	/**
	 * Method to get the name of bucket
	 * 
	 * @return
	 */
	public String getBucketName() {
		return mBucketName;
	}

	/**
	 * Method to add ImageData
	 * 
	 * @param imageData
	 */
	public void addPicData(ImageData imageData) {
		mImageDatas.add(imageData);
	}

	/**
	 * Method to remove ImageData
	 * 
	 * @param imageData
	 */
	public void removePicData(ImageData imageData) {
		mImageDatas.remove(imageData);
	}

	/**
	 * Method to get the data of all images belongs belongs to this folder
	 * 
	 * @return
	 */
	public LinkedList<ImageData> getImageDatas() {
		return mImageDatas;
	}
}
