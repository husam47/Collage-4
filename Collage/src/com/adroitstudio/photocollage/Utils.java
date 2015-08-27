package com.adroitstudio.photocollage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class Utils {

	private static Bitmap mBitmapDefaultThumb;
	private static String COLLAGE_COUNT = "com.adroitstudio.photocollage";

	/**
	 * Method to get the default thumbnail bitmap
	 * 
	 * @param context
	 * @return
	 */
	public static Bitmap getDefaultThumbBitmap(Context context) {
		if (mBitmapDefaultThumb == null || mBitmapDefaultThumb.isRecycled()) {
			Options options = new Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(context.getResources(), R.drawable.default_thumb, options);
			int dimension = (int) ((float) context.getResources().getDisplayMetrics().widthPixels * 0.3F);
			options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, dimension, dimension);
			options.inJustDecodeBounds = false;
			mBitmapDefaultThumb = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_thumb, options);
		}
		return mBitmapDefaultThumb;
	}

	/**
	 * Method to get the sample size of bitmap
	 * 
	 * @param srcWidth
	 *            src bitmap width
	 * @param srcHeight
	 *            src bitmap height
	 * @param desiredWidth
	 *            dest bitmap width
	 * @param desiredHeight
	 *            dest bitmap height
	 * @return the sample size
	 */
	public static int calculateInSampleSize(int srcWidth, int srcHeight, int desiredWidth, int desiredHeight) {
		int inSampleSize = 1;
		if (srcHeight > desiredHeight || srcWidth > desiredWidth) {
			int heightRatio = Math.round((float) srcHeight / (float) desiredHeight);
			int widthRatio = Math.round((float) srcWidth / (float) desiredWidth);
			inSampleSize = heightRatio <= widthRatio ? heightRatio : widthRatio;
		}
		return (inSampleSize % 2 == 0) ? inSampleSize : (inSampleSize - 1);
	}

	/**
	 * Method to get the bitmap draw by path
	 * 
	 * @param path
	 *            MKRPath
	 * @param width
	 *            width of bitmap
	 * @param height
	 *            height of bitmap
	 * @return Bitmap draw by path
	 */
	public static Bitmap getPathBitmap(MKRPath path, int width, int height) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.BLACK);
		Bitmap bitmapTemp = Bitmap.createBitmap((int) (path.getmMaxX() - path.getmMinX()), (int) (path.getmMaxY() - path.getmMinY()), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapTemp);
		canvas.drawColor(Color.argb(0, 0, 0, 0));
		canvas.translate(-path.getmMinX(), -path.getmMinY());
		canvas.drawPath(path, paint);
		Bitmap bitmap = Bitmap.createScaledBitmap(bitmapTemp, width, height, true);
		if (!bitmap.equals(bitmapTemp)) {
			bitmapTemp.recycle();
		}
		return bitmap;
	}

	/**
	 * Method to get an unique interger value
	 * 
	 * @param context
	 * @return
	 */
	public static int getImageCount(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(COLLAGE_COUNT, Context.MODE_PRIVATE);
		int count = sharedPreferences.getInt(COLLAGE_COUNT, 0);
		if (count > 99999) {
			count = 0;
		}
		Editor edit = sharedPreferences.edit();
		edit.putInt(COLLAGE_COUNT, ++count);
		edit.commit();
		return count;
	}
}
