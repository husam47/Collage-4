package com.themkr.nativeeffect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.Log;

public class NativeEffects {
	static {
		System.loadLibrary("NativeMethods");
	}

	public static enum MKR_Effects {

	}

	public static final int NO_EFFECT = 0;
	/*
	 * public static final int SHARPEN1 = 1; public static final int BLUR = 2;
	 * public static final int EDGE_ENHANCE = 3; public static final int EMBOSS
	 * = 5; public static final int LIGHTEN = 6; public static final int DARKEN
	 * = 7; public static final int MIKE_FAVORITE = 8; public static final int
	 * SOLID = 9; public static final int FUZZY_GLASS = 10; public static final
	 * int GOLDEN_EDGE = 12;
	 */
	// DONE
	public static final int EDGE_DETECT = 4;
	public static final int GRAYSCALE = 13;
	public static final int SKETCH = 15;
	public static final int SEPIA = 16;
	public static final int BLUE = 17;
	public static final int YELLOW = 18;
	public static final int GREEN = 19;
	public static final int PINK = 20;
	public static final int MAGENTA = 21;
	public static final int RED = 22;
	public static final int CYAN = 23;
	public static final int VOILET = 24;
	public static final int LINEAR_BAR_EFFECT_1 = 25;
	public static final int NEGATIVE_1 = 27;
	public static final int NEGATIVE_2 = 28;
	public static final int OCEAN = 29;
	public static final int OVER_LAY_A0 = 30;
	public static final int OVER_LAY_A1 = 31;

	/**
	 * Method to get the list of all effects
	 * 
	 * @return
	 */
	public static int[] getEffectsList() {
		int list[] = { NO_EFFECT, EDGE_DETECT, GRAYSCALE, SKETCH, SEPIA, BLUE, YELLOW, GREEN, PINK, MAGENTA, RED, CYAN, VOILET, LINEAR_BAR_EFFECT_1, NEGATIVE_1, NEGATIVE_2, OCEAN };
		return list;
	}

	/**
	 * Method to ge the Convolution Matrix
	 * 
	 * @param effects
	 *            Effect name
	 * @return
	 */
	/*
	 * private static int[] getEffectValue(int effects) { switch (effects) {
	 * case SHARPEN1: return new int[] { 0, -3, 0, -3, 15, -3, 0, -3, 0 }; case
	 * BLUR: return new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 }; case EDGE_ENHANCE:
	 * return new int[] { 0, 0, 0, -1, -1, 0, 0, 0, 0 }; case EDGE_DETECT: case
	 * EMBOSS: // return new float[] { -2, -1, 0, -1, 1, 1, 0, 1, 2 }; case
	 * LIGHTEN: return new int[] { 0, 0, 0, 0, 12 / 9, 0, 0, 0, 0 }; case
	 * DARKEN: return new int[] { 0, 0, 0, 0, 6 / 97, 0, 0, 0, 0 }; case
	 * MIKE_FAVORITE: return new int[] { 2 / 9, 22 / 9, 1 / 9, 22 / 9, 1 / 9,
	 * -22 / 9, 1 / 9, -22 / 9, -2 / 9 }; case SOLID: // return new float[] { 1,
	 * 0, -10, -2, 3, 1, 6, 1, -1 };/ case GOLDEN_EDGE: return new int[] { 2, 3,
	 * -3, 1, -1, 1, -2, 1, -2 }; case FUZZY_GLASS: return new int[] { 0, 20, 0,
	 * 20, -59, 20, 0, 13, 0 }; case GRAYSCALE: default: return new int[] { 1,
	 * 1, 1, 1, -8, 1, 1, 1, 1 }; } }
	 */

	/**
	 * Method to apply effect on a bitmap
	 * 
	 * @param context
	 * 
	 * @param src
	 *            Source bitmap
	 * @param dest
	 *            Dest bitmap
	 */
	public static void getEffectedBitmap(Context context, Bitmap src, Bitmap dest, int effect) {
		if (src != null && dest != null && !src.isRecycled() && !dest.isRecycled()) {
			switch (effect) {
			case NO_EFFECT:
				break;
			case EDGE_DETECT:
				getEdgeDetectBitmap(src, dest);
				break;
			case GRAYSCALE:
				getGrayScaleBitmap(src, dest);
				break;
			case SKETCH:
				getSketchBitmap(src, dest);
				break;
			case SEPIA:
				getSepiaBitmap(src, dest);
				break;
			case BLUE:
				getBlueBitmap(src, dest);
				break;
			case YELLOW:
				getYellowBitmap(src, dest);
				break;
			case GREEN:
				getGreenBitmap(src, dest);
				break;
			case PINK:
				getPinkBitmap(src, dest);
				break;
			case MAGENTA:
				getMagentaBitmap(src, dest);
				break;
			case RED:
				getRedBitmap(src, dest);
				break;
			case CYAN:
				getCyanBitmap(src, dest);
				break;
			case VOILET:
				getVoiletBitmap(src, dest);
				break;
			case LINEAR_BAR_EFFECT_1:
				getLinearBarEffect1(src, dest);
				break;
			case NEGATIVE_1:
				getNegative1Bitmap(src, dest);
				break;
			case NEGATIVE_2:
				getNegative2Bitmap(src, dest);
				break;
			case OCEAN:
				getOceanEffect(src, dest);
				break;
			case OVER_LAY_A0:
			case OVER_LAY_A1:
				getOverLayEffect(context, src, dest, effect);
				break;
			default:
				break;
			}

		}
	}

	/**
	 * Method to grayscaled the bitmap
	 * 
	 * @param src
	 * @param dest
	 * @param multiplyer
	 */
	private static void getGrayScaleBitmap(Bitmap src, Bitmap dest) {
		NativeMethods.getColorEffectedBitmap(src, dest,0.35F, new float[] { 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0 });
	}

	/**
	 * Method to edgeBitmapDark the bitmap
	 * 
	 * @param src
	 * @param dest
	 * @param multiplyer
	 */
	private static void getEdgeDetectBitmap(Bitmap src, Bitmap dest) {
		Bitmap bitmapGrayScale = Bitmap.createBitmap(dest.getWidth(), dest.getHeight(), dest.getConfig());
		getGrayScaleBitmap(src, bitmapGrayScale);
		NativeMethods.getConventionalEffectedBitmap(bitmapGrayScale, dest, 0.6F, new float[] { 0, 1, 0, 1, -4, 1, 0, 1, 0 });
		bitmapGrayScale.recycle();
	}

	/**
	 * Method to sketch Bitmap the bitmap
	 * 
	 * @param src
	 * @param dest
	 * @param multiplyer
	 */
	private static void getSketchBitmap(Bitmap src, Bitmap dest) {
		Bitmap bitmapEdged = Bitmap.createBitmap(dest.getWidth(), dest.getHeight(), dest.getConfig());
		getEdgeDetectBitmap(src, bitmapEdged);
		NativeMethods.getInversedColorEffectedBitmap(bitmapEdged, dest, 1, 0);
		bitmapEdged.recycle();
	}

	/**
	 * Method to get the solid bitmap
	 * 
	 * @param src
	 * @param dest
	 * @param multiplier
	 * @param offSet
	 */
	private static void getSolidBitmap(Bitmap src, Bitmap dest) {
		NativeMethods.getConventionalEffectedBitmap(src, dest, -1, new float[] { 1, 0, -10, -2, 3, 1, 6, 1, -1 });
	}

	/**
	 * Method to get the sepia effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getSepiaBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		Bitmap tempBlue = Bitmap.createBitmap(tempGrayScale.getWidth(), tempGrayScale.getHeight(), tempGrayScale.getConfig());
		NativeMethods.getColorEffectedBitmap(tempGrayScale, tempBlue, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
		NativeMethods.getColorEffectedBitmap(tempBlue, dest, 1, new float[] { -0.58F, 1.36F, 0.22F, 0, 0, 0.43F, 0.44F, 0.11F, 0, 0, 0.35F, 1.49F, -0.84F, 0, 0, 0, 0, 0, 1, 0 });
		tempBlue.recycle();
	}

	/**
	 * Method to get the blue effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getBlueBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		NativeMethods.getColorEffectedBitmap(tempGrayScale, dest, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
	}

	/**
	 * Method to get the Yellow effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getYellowBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		Bitmap tempBlue = Bitmap.createBitmap(tempGrayScale.getWidth(), tempGrayScale.getHeight(), tempGrayScale.getConfig());
		NativeMethods.getColorEffectedBitmap(tempGrayScale, tempBlue, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
		NativeMethods.getColorEffectedBitmap(tempBlue, dest, 1, new float[] { -0.574F, 1.43F, 0.143F, 0, 0, 0.426F, 0.43F, 0.144F, 0, 0, 0.426F, 1.429F, -0.855F, 0, 0, 0, 0, 0, 1, 0 });
		tempBlue.recycle();
	}

	/**
	 * Method to get the Green effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getGreenBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		Bitmap tempBlue = Bitmap.createBitmap(tempGrayScale.getWidth(), tempGrayScale.getHeight(), tempGrayScale.getConfig());
		NativeMethods.getColorEffectedBitmap(tempGrayScale, tempBlue, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
		NativeMethods.getColorEffectedBitmap(tempBlue, dest, 1, new float[] { -0.063F, 1.71F, -0.646F, 0, 0, 0.2186F, 0.436F, 0.345F, 0, 0, 0.979F, 0.539F, -0.519F, 0, 0, 0, 0, 0, 1, 0 });
		tempBlue.recycle();
	}

	/**
	 * Method to get the Pink effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getPinkBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		Bitmap tempBlue = Bitmap.createBitmap(tempGrayScale.getWidth(), tempGrayScale.getHeight(), tempGrayScale.getConfig());
		NativeMethods.getColorEffectedBitmap(tempGrayScale, tempBlue, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
		NativeMethods.getColorEffectedBitmap(tempBlue, dest, 1, new float[] { 0.083F, -0.07F, 0.9876F, 0, 0, 0.332F, 0.8846F, -0.216F, 0, 0, -0.591F, 1.3519F, 0.24F, 0, 0, 0, 0, 0, 1, 0 });
		tempBlue.recycle();
	}

	/**
	 * Method to get the MAGENTA effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getMagentaBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		Bitmap tempBlue = Bitmap.createBitmap(tempGrayScale.getWidth(), tempGrayScale.getHeight(), tempGrayScale.getConfig());
		NativeMethods.getColorEffectedBitmap(tempGrayScale, tempBlue, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
		NativeMethods.getColorEffectedBitmap(tempBlue, dest, 1, new float[] { 0.895F, -0.185F, 0.290F, 0, 0, 0.054F, 1.029F, -0.083F, 0, 0, -0.232F, 0.255F, 0.976F, 0, 0, 0, 0, 0, 1, 0 });
		tempBlue.recycle();
	}

	/**
	 * Method to get the Voilet effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getVoiletBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		Bitmap tempBlue = Bitmap.createBitmap(tempGrayScale.getWidth(), tempGrayScale.getHeight(), tempGrayScale.getConfig());
		NativeMethods.getColorEffectedBitmap(tempGrayScale, tempBlue, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
		NativeMethods.getColorEffectedBitmap(tempBlue, dest, 1, new float[] { 0.618F, -0.296F, 0.677F, 0, 0, 0.163F, 1.015F, -0.179F, 0, 0, -0.494F, 0.715F, 0.779F, 0, 0, 0, 0, 0, 1, 0 });
		tempBlue.recycle();
	}

	/**
	 * Method to get the Cyan effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getCyanBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		Bitmap tempBlue = Bitmap.createBitmap(tempGrayScale.getWidth(), tempGrayScale.getHeight(), tempGrayScale.getConfig());
		NativeMethods.getColorEffectedBitmap(tempGrayScale, tempBlue, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
		NativeMethods.getColorEffectedBitmap(tempBlue, dest, 1, new float[] { 0.838F, 0.89F, -0.729F, 0, 0, -0.026F, 0.763F, 0.262F, 0, 0, 0.735F, -0.28F, 0.545F, 0, 0, 0, 0, 0, 1, 0 });
		tempBlue.recycle();
	}

	/**
	 * Method to get the Red effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getRedBitmap(Bitmap src, Bitmap dest) {
		Bitmap tempGrayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, tempGrayScale);
		src.recycle();
		Bitmap tempBlue = Bitmap.createBitmap(tempGrayScale.getWidth(), tempGrayScale.getHeight(), tempGrayScale.getConfig());
		NativeMethods.getColorEffectedBitmap(tempGrayScale, tempBlue, 1, new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 86, 0, 0, 0, 1, 0 });
		tempGrayScale.recycle();
		NativeMethods.getColorEffectedBitmap(tempBlue, dest, 1, new float[] { -0.244F, 0.271F, 0.972F, 0, 0, 0.417F, 0.754F, -0.172F, 0, 0, -0.461F, 1.623F, -0.162F, 0, 0, 0, 0, 0, 1, 0 });
		tempBlue.recycle();
	}

	/**
	 * Method to get Linear Bar Effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getLinearBarEffect1(Bitmap src, Bitmap dest) {
		Canvas canvas = new Canvas(dest);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		paint.reset();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		paint.setColorFilter(new ColorMatrixColorFilter(cm));
		canvas.drawBitmap(src, 0, 0, paint);
		paint.reset();
		cm = new ColorMatrix();
		float[] arr = cm.getArray();
		arr[4] = arr[9] = arr[14] = 100;
		paint.setColorFilter(new ColorMatrixColorFilter(cm));
		int[] colors = new int[] { Color.rgb(143, 0, 0), Color.rgb(75, 0, 130), 0xFF0000FF, 0xFF00FF00, 0xFFFFFF00, Color.rgb(255, 127, 0), 0xFFFF0000 };
		// int[] colors = new int[] { 0xFFFF0000, 0xFFFFFF00, 0xFF00FF00,
		// 0xFF00FFFF, 0xFF0000FF, 0xFFFF00FF, 0xFFFF0000 };
		Shader s = new LinearGradient(0, 0, 0, dest.getHeight(), colors, null, TileMode.CLAMP);
		paint.setShader(s);
		paint.setXfermode(new PorterDuffXfermode(Mode.MULTIPLY));
		canvas.drawRect(0, 0, dest.getWidth(), dest.getHeight(), paint);
	}

	/**
	 * Method to get Negative Effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getNegative1Bitmap(Bitmap src, Bitmap dest) {
		Bitmap grayScale = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		getGrayScaleBitmap(src, grayScale);
		NativeMethods.getColorEffectedBitmap(grayScale, dest, 1, new float[] { -1, 0, 0, 0, 255, 0, -1, 0, 0, 255, 0, 0, -1, 0, 255, 0, 0, 0, 1, 0 });
		grayScale.recycle();
	}

	/**
	 * Method to get Negative Effect Bitmap
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getNegative2Bitmap(Bitmap src, Bitmap dest) {
		NativeMethods.getColorEffectedBitmap(src, dest, 1, new float[] { -1, 0, 0, 0, 255, 0, -1, 0, 0, 255, 0, 0, -1, 0, 255, 0, 0, 0, 1, 0 });
	}

	/**
	 * Method to apply ocean effect
	 * 
	 * @param src
	 * @param dest
	 */
	private static void getOceanEffect(Bitmap src, Bitmap dest) {
		Bitmap layer = Bitmap.createBitmap(src.getWidth(),src.getHeight(),Config.ARGB_8888);
		Canvas canvas = new Canvas(layer);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		p.setColor(Color.parseColor("#1C6BA0"));
		canvas.drawPaint(p);
		NativeMethods.getOverLayBitmap(src, layer, dest);
	}

	private static void getOverLayEffect(Context context, Bitmap src, Bitmap dest, int effect) {
		Options mOptions = new Options();
		mOptions.inPreferredConfig = Config.ARGB_8888;
		mOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), R.drawable.a0, mOptions);
		mOptions.inSampleSize = calculateInSampleSize(mOptions.outWidth, mOptions.outHeight, dest.getWidth(), dest.getHeight());
		mOptions.inJustDecodeBounds = false;
		Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.a0, mOptions);
		Bitmap overLay = Bitmap.createScaledBitmap(decodeResource, dest.getWidth(), dest.getHeight(), true);
		if (!overLay.equals(decodeResource)) {
			decodeResource.recycle();
		}
		NativeMethods.getOverLayBitmap(src, overLay, dest);
		src.recycle();
		overLay.recycle();
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
	private static int calculateInSampleSize(int srcWidth, int srcHeight, int desiredWidth, int desiredHeight) {
		int inSampleSize = 1;
		if (srcHeight > desiredHeight || srcWidth > desiredWidth) {
			int heightRatio = Math.round((float) srcHeight / (float) desiredHeight);
			int widthRatio = Math.round((float) srcWidth / (float) desiredWidth);
			inSampleSize = heightRatio <= widthRatio ? heightRatio : widthRatio;
		}
		return (inSampleSize % 2 == 0) ? inSampleSize : (inSampleSize + 1);
	}

}