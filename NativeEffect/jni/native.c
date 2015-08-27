#include <android/bitmap.h>
#include <android/log.h>
#include <jni.h>
#include <stdint.h>

//#include "com_themkr_nativeeffect_NativeMethods.h"

#define  LOG_TAG    "THE-MKR NATIVE"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

typedef struct {
	uint8_t red;
	uint8_t green;
	uint8_t blue;
	uint8_t alpha;
} argb;

/**
 * Method to get the color value in 0-255 range
 */
int getColorValue(int value) {
	if (value > 255) {
		return 255;
	} else if (value < 0) {
		return 0;
	}
	return value;
}

JNIEXPORT void JNICALL Java_com_themkr_nativeeffect_NativeMethods_getConventionalEffectedBitmap(JNIEnv * jEnv, jclass jcls, jobject bitmapGrayscale, jobject bitmapEffecte, jfloat multiplier, jfloatArray effectArray) {
	AndroidBitmapInfo bitmapInfoOrignal;
	void* pixelsOrignal;
	AndroidBitmapInfo bitmapInfoEffected;
	void* pixelsEffected;
	int ret;
	int x, y, i, j;

	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapGrayscale, &bitmapInfoOrignal)) < 0) {
		LOGE("AndroidBitmap_getInfo() bitmapGrayscale failed ! error=%d", ret);
		return;
	}
	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapEffecte, &bitmapInfoEffected)) < 0) {
		LOGE("AndroidBitmap_getInfo()  bitmapEffecte failed ! error=%d", ret);
		return;
	}
	if (bitmapInfoOrignal.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapGRAY format is not 88888 !");
		return;
	}
	if (bitmapInfoEffected.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapEFFECTE format is not 88888 !");
		return;
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapGrayscale, &pixelsOrignal)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapEffecte, &pixelsEffected)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}

	jsize length = (*jEnv)->GetArrayLength(jEnv, effectArray);
	jfloat *array = (*jEnv)->GetFloatArrayElements(jEnv, effectArray, 0);
	float colorMatrix[3][3] = { { array[0], array[1], array[2] }, { array[3], array[4], array[5] }, { array[6], array[7], array[8] } };

	LOGE("CONVENTIONAL EFFECT");
	for (y = 0; y <= bitmapInfoOrignal.height - 1; y++) {
		argb* grayLine = (argb*) pixelsOrignal;
		argb* edgeLine = (argb*) pixelsEffected;
		for (x = 0; x < bitmapInfoOrignal.width - 1; x++) {
			int R = 0, G = 0, B = 0, A = 0;
			A = grayLine[x].alpha;
			if (!(y == 0 || y == bitmapInfoOrignal.height - 1 || x == 0 || x == bitmapInfoOrignal.width - 1)) {
				for (i = 0; i <= 2; ++i) {
					argb* grayLineTemp;
					if (i == 0) {
						grayLineTemp = (argb*) pixelsOrignal - bitmapInfoOrignal.stride;
					} else if (i == 1) {
						grayLineTemp = (argb*) pixelsOrignal;
					} else if (i == 2) {
						grayLineTemp = (argb*) pixelsOrignal + bitmapInfoOrignal.stride;
					}
					for (j = 0; j <= 2; ++j) {
						R = R + (int) ((float) grayLineTemp[x + j - 1].red * colorMatrix[i][j]);
						G = G + (int) ((float) grayLineTemp[x + j - 1].green * colorMatrix[i][j]);
						B = B + (int) ((float) grayLineTemp[x + j - 1].blue * colorMatrix[i][j]);
					}
				}
				R = (R * multiplier);
				G = (G * multiplier);
				B = (B * multiplier);
			}
			edgeLine[x].red = getColorValue(R);
			edgeLine[x].green = getColorValue(G);
			edgeLine[x].blue = getColorValue(B);
			edgeLine[x].alpha = getColorValue(A);
		}
		pixelsOrignal = (char*) pixelsOrignal + bitmapInfoOrignal.stride;
		pixelsEffected = (char*) pixelsEffected + bitmapInfoEffected.stride;
	}

	AndroidBitmap_unlockPixels(jEnv, bitmapGrayscale);
	AndroidBitmap_unlockPixels(jEnv, bitmapEffecte);
}

JNIEXPORT void JNICALL Java_com_themkr_nativeeffect_NativeMethods_getColorEffectedBitmap(JNIEnv * jEnv, jclass jcls, jobject bitmapGrayscale, jobject bitmapEffecte, jfloat multiplier, jfloatArray effectArray) {
	AndroidBitmapInfo bitmapInfoOrignal;
	void* pixelsOrignal;
	AndroidBitmapInfo bitmapInfoEffected;
	void* pixelsEffected;
	int ret;
	int x, y, i, j, k = 0;

	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapGrayscale, &bitmapInfoOrignal)) < 0) {
		LOGE("AndroidBitmap_getInfo() bitmapGrayscale failed ! error=%d", ret);
		return;
	}
	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapEffecte, &bitmapInfoEffected)) < 0) {
		LOGE("AndroidBitmap_getInfo()  bitmapEffecte failed ! error=%d", ret);
		return;
	}
	if (bitmapInfoOrignal.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapGRAY format is not 88888 !");
		return;
	}
	if (bitmapInfoEffected.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapEFFECTE format is not 88888 !");
		return;
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapGrayscale, &pixelsOrignal)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapEffecte, &pixelsEffected)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}

	jsize length = (*jEnv)->GetArrayLength(jEnv, effectArray);
	jfloat *array = (*jEnv)->GetFloatArrayElements(jEnv, effectArray, 0);
	float colorMatrix[5][5];
	for (i = 0; i < 5; ++i) {
		for (j = 0; j < 5; ++j) {
			colorMatrix[i][j] = array[k];
			k++;
		}
	}

	LOGE("COLLOR EFFECT");
	for (y = 0; y < bitmapInfoOrignal.height; ++y) {
		argb* orignalLine = (argb*) pixelsOrignal;
		argb* effectedLine = (argb*) pixelsEffected;
		for (x = 0; x < bitmapInfoOrignal.width; ++x) {
			int red = orignalLine[x].red;
			int green = orignalLine[x].green;
			int blue = orignalLine[x].blue;
			int alpha = orignalLine[x].alpha;

			int newRed = (int) ((float) ((colorMatrix[0][0] * red) + (colorMatrix[0][1] * green) + (colorMatrix[0][2] * blue)) * multiplier);
			int newGreen = (int) ((float) ((colorMatrix[1][0] * red) + (colorMatrix[1][1] * green) + (colorMatrix[1][2] * blue)) * multiplier);
			int newBlue = (int) ((float) ((colorMatrix[2][0] * red) + (colorMatrix[2][1] * green) + (colorMatrix[2][2] * blue)) * multiplier);

			effectedLine[x].red = getColorValue(newRed + colorMatrix[0][4]);
			effectedLine[x].green = getColorValue(newGreen + colorMatrix[1][4]);
			effectedLine[x].blue = getColorValue(newBlue + colorMatrix[2][4]);
			effectedLine[x].alpha = getColorValue(alpha);
		}
		pixelsOrignal = (char*) pixelsOrignal + bitmapInfoOrignal.stride;
		pixelsEffected = (char*) pixelsEffected + bitmapInfoEffected.stride;
	}
	LOGE("5");

	AndroidBitmap_unlockPixels(jEnv, bitmapGrayscale);
	AndroidBitmap_unlockPixels(jEnv, bitmapEffecte);
}

JNIEXPORT void JNICALL Java_com_themkr_nativeeffect_NativeMethods_getInversedColorEffectedBitmap(JNIEnv * jEnv, jclass jcls, jobject bitmapGrayscale, jobject bitmapEffecte, jfloat multiplyer, jint offSet) {
	AndroidBitmapInfo bitmapInfoOrignal;
	void* pixelsOrignal;
	AndroidBitmapInfo bitmapInfoEffected;
	void* pixelsEffected;
	int ret;
	int x, y, i, j;

	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapGrayscale, &bitmapInfoOrignal)) < 0) {
		LOGE("AndroidBitmap_getInfo() bitmapGrayscale failed ! error=%d", ret);
		return;
	}
	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapEffecte, &bitmapInfoEffected)) < 0) {
		LOGE("AndroidBitmap_getInfo()  bitmapEffecte failed ! error=%d", ret);
		return;
	}
	if (bitmapInfoOrignal.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapGRAY format is not 88888 !");
		return;
	}
	if (bitmapInfoEffected.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapEFFECTE format is not 88888 !");
		return;
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapGrayscale, &pixelsOrignal)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapEffecte, &pixelsEffected)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}

	LOGE("REV EFFECT");
	for (y = 0; y < bitmapInfoOrignal.height; ++y) {
		argb* orignalLine = (argb*) pixelsOrignal;
		argb* effectedLine = (argb*) pixelsEffected;
		for (x = 0; x < bitmapInfoOrignal.width; ++x) {
			int valRed = getColorValue((int) ((float) orignalLine[x].red * multiplyer) + offSet);
			int valGreen = getColorValue((int) ((float) orignalLine[x].green * multiplyer) + offSet);
			int valBlue = getColorValue((int) ((float) orignalLine[x].blue * multiplyer) + offSet);

			int red = 255 - valRed;
			int green = 255 - valGreen;
			int blue = 255 - valBlue;

			effectedLine[x].red = red;
			effectedLine[x].green = green;
			effectedLine[x].blue = blue;
			effectedLine[x].alpha = orignalLine[x].alpha;
		}
		pixelsOrignal = (char*) pixelsOrignal + bitmapInfoOrignal.stride;
		pixelsEffected = (char*) pixelsEffected + bitmapInfoEffected.stride;
	}

	AndroidBitmap_unlockPixels(jEnv, bitmapGrayscale);
	AndroidBitmap_unlockPixels(jEnv, bitmapEffecte);
}

JNIEXPORT void JNICALL Java_com_themkr_nativeeffect_NativeMethods_getOverLayBitmap(JNIEnv * jEnv, jclass jcls, jobject bitmapOrignal, jobject bitmapOverlay, jobject bitmapEffecte) {
	AndroidBitmapInfo bitmapInfoOrignal;
	void* pixelsOrignal;
	AndroidBitmapInfo bitmapInfoOverlay;
	void* pixelsOverlay;
	AndroidBitmapInfo bitmapInfoEffected;
	void* pixelsEffected;
	int ret;
	int x, y, i, j;

	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapOrignal, &bitmapInfoOrignal)) < 0) {
		LOGE("AndroidBitmap_getInfo() bitmapOrignal failed ! error=%d", ret);
		return;
	}
	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapOverlay, &bitmapInfoOverlay)) < 0) {
		LOGE("AndroidBitmap_getInfo() bitmapOverlay failed ! error=%d", ret);
		return;
	}
	if ((ret = AndroidBitmap_getInfo(jEnv, bitmapEffecte, &bitmapInfoEffected)) < 0) {
		LOGE("AndroidBitmap_getInfo()  bitmapEffecte failed ! error=%d", ret);
		return;
	}
	if (bitmapInfoOrignal.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapOrignal format is not 88888 !");
		return;
	}
	if (bitmapInfoOverlay.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapOverlay format is not 88888 !");
		return;
	}
	if (bitmapInfoEffected.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
		LOGE("BitmapEFFECTE format is not 88888 !");
		return;
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapOrignal, &pixelsOrignal)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapOverlay, &pixelsOverlay)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}
	if ((ret = AndroidBitmap_lockPixels(jEnv, bitmapEffecte, &pixelsEffected)) < 0) {
		LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
	}

	LOGE("Overlay EFFECT");
	for (y = 0; y < bitmapInfoOrignal.height; ++y) {
		argb* orignalLine = (argb*) pixelsOrignal;
		argb* ovelayLine = (argb*) pixelsOverlay;
		argb* effectedLine = (argb*) pixelsEffected;
		for (x = 0; x < bitmapInfoOrignal.width; ++x) {
			int color = (int) ((float) (orignalLine[x].red + orignalLine[x].green + orignalLine[x].blue) * 0.4);
			int alphaOrignal = orignalLine[x].alpha;

			int redOverlay = ovelayLine[x].red;
			int greenOverlay = ovelayLine[x].green;
			int blueOverlay = ovelayLine[x].blue;
			int alphaOverlay = ovelayLine[x].alpha;

			effectedLine[x].red = (int) ((float) (color * redOverlay) / (float) 256);
			effectedLine[x].green = (int) ((float) (color * greenOverlay) / (float) 256);
			effectedLine[x].blue = (int) ((float) (color * blueOverlay) / (float) 256);
			effectedLine[x].alpha = alphaOrignal;
		}
		pixelsOrignal = (char*) pixelsOrignal + bitmapInfoOrignal.stride;
		pixelsOverlay = (char*) pixelsOverlay + bitmapInfoOverlay.stride;
		pixelsEffected = (char*) pixelsEffected + bitmapInfoEffected.stride;
	}
	LOGE("5");

	AndroidBitmap_unlockPixels(jEnv, bitmapOrignal);
	AndroidBitmap_unlockPixels(jEnv, bitmapEffecte);
}

