package com.adroitstudio.photocollage;

import android.graphics.Path;

public class MKRPath extends Path {

	private float mMinX, mMinY, mMaxX, mMaxY, initX, initY;

	public MKRPath() {
		mMinX = mMinY = mMaxX = mMaxY = initX = initY = -999;
	}

	@Override
	public void moveTo(float x, float y) {
		super.moveTo(x, y);
		initX = mMinX = mMaxX = x;
		initY = mMinY = mMaxY = y;

	}

	@Override
	public void lineTo(float x, float y) {
		super.lineTo(x, y);
		if (x < mMinX) {
			mMinX = x;
		}
		if (y < mMinY) {
			mMinY = y;
		}

		if (x > mMaxX) {
			mMaxX = x;
		}
		if (y > mMaxY) {
			mMaxY = y;
		}
	}

	/**
	 * Method to get the path points
	 * 
	 * @return
	 */

	/**
	 * Method to get the maximum X value
	 * 
	 * @return
	 */
	public float getmMaxX() {
		return mMaxX;
	}

	/**
	 * Method to get the maximum Y value
	 * 
	 * @return
	 */
	public float getmMaxY() {
		return mMaxY;
	}

	/**
	 * Method to get the minimum X value
	 * 
	 * @return
	 */
	public float getmMinX() {
		return mMinX;
	}

	/**
	 * Method to get the minimum Y value
	 * 
	 * @return
	 */
	public float getmMinY() {
		return mMinY;
	}

	/**
	 * Method to get initial X value
	 * 
	 * @return
	 */
	public float getInitX() {
		return initX;
	}

	/**
	 * Method to get initial Y value
	 * 
	 * @return
	 */
	public float getInitY() {
		return initY;
	}

	/**
	 * Method to know weather the path is valid or not
	 * 
	 * @return TRUE if path is vallid, FALSE other wise
	 */
	public boolean isValidPath() {
		if (mMinX == -999 || mMinY == -999 || mMaxX == -999 || mMaxY == -999 || mMinX == mMaxX || mMinY == mMaxY) {
			return false;
		}
		return true;
	}
}
