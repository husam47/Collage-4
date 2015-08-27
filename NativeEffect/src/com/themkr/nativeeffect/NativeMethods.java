package com.themkr.nativeeffect;

class NativeMethods {

	/**
	 * Method to get conventional bitmap, Bitmap Dimension must be same
	 * 
	 * @param bitmapOrignal
	 * @param effectedBitmap
	 * @param multiplier
	 * @param valueArray
	 */
	public native static void getConventionalEffectedBitmap(Object bitmapOrignal, Object effectedBitmap, float multiplier, float[] valueArray);

	/**
	 * Method to get coller effected bitmap, Bitmap Dimension must be same
	 * 
	 * @param bitmapOrignal
	 * @param effectedBitmap
	 * @param multiplier
	 * @param valueArray
	 */
	public native static void getColorEffectedBitmap(Object bitmapOrignal, Object effectedBitmap, float multiplier, float[] valueArray);

	/**
	 * Method to get inverse bitmap Bitmap, Dimension must be same
	 * 
	 * @param bitmapOrignal
	 * @param effectedBitmap
	 * @param multiplier
	 * @param offSet
	 */
	public native static void getInversedColorEffectedBitmap(Object bitmapOrignal, Object effectedBitmap, float multiplier, int offSet);

	/**
	 * Method to get overlayed bitmap, Bitmap Dimension must be same
	 * 
	 * @param bitmapOrignal
	 * @param bitmapOverlay
	 * @param bitmapEffected
	 */
	public native static void getOverLayBitmap(Object bitmapOrignal, Object bitmapOverlay, Object bitmapEffected);

}
