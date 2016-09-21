package com.themkrworld.collage.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.themkrworld.collage.R;

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
            mBitmapDefaultThumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }
        return mBitmapDefaultThumb;
    }

//	/**
//	 * Method to get the sample size of bitmap
//	 *
//	 * @param srcWidth
//	 *            src bitmap width
//	 * @param srcHeight
//	 *            src bitmap height
//	 * @param desiredWidth
//	 *            dest bitmap width
//	 * @param desiredHeight
//	 *            dest bitmap height
//	 * @return the sample size
//	 */
//	public static int calculateInSampleSize(int srcWidth, int srcHeight, int desiredWidth, int desiredHeight) {
//		int inSampleSize = 1;
//		if (srcHeight > desiredHeight || srcWidth > desiredWidth) {
//			int heightRatio = Math.round((float) srcHeight / (float) desiredHeight);
//			int widthRatio = Math.round((float) srcWidth / (float) desiredWidth);
//			inSampleSize = heightRatio <= widthRatio ? heightRatio : widthRatio;
//		}
//		return (inSampleSize % 2 == 0) ? inSampleSize : (inSampleSize - 1);
//	}

//	/**
//	 * Method to get the bitmap draw by path
//	 *
//	 * @param path
//	 *            MKRPath
//	 * @param width
//	 *            width of bitmap
//	 * @param height
//	 *            height of bitmap
//	 * @return Bitmap draw by path
//	 */
//	public static Bitmap getPathBitmap(MKRPath path, int width, int height) {
//		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
//		paint.setStyle(Style.FILL_AND_STROKE);
//		paint.setColor(Color.BLACK);
//		Bitmap bitmapTemp = Bitmap.createBitmap((int) (path.getmMaxX() - path.getmMinX()), (int) (path.getmMaxY() - path.getmMinY()), Config.ARGB_8888);
//		Canvas canvas = new Canvas(bitmapTemp);
//		canvas.drawColor(Color.argb(0, 0, 0, 0));
//		canvas.translate(-path.getmMinX(), -path.getmMinY());
//		canvas.drawPath(path, paint);
//		Bitmap bitmap = Bitmap.createScaledBitmap(bitmapTemp, width, height, true);
//		if (!bitmap.equals(bitmapTemp)) {
//			bitmapTemp.recycle();
//		}
//		return bitmap;
//	}

    /**
     * Method to get an unique interger value
     *
     * @param context
     * @return
     */
    public static int getImageCount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(COLLAGE_COUNT, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(COLLAGE_COUNT, 0);
        if (count >= Integer.MAX_VALUE) {
            count = 0;
        }
        Editor edit = sharedPreferences.edit();
        edit.putInt(COLLAGE_COUNT, ++count);
        edit.commit();
        return count;
    }

//    private class PicRename extends AsyncTask<Void, Void, Void> {
//        private String mFilePath;
//        private String mTemplateFilePath;
//
//        /**
//         * Constructor
//         *
//         * @param drivePath Folder path in assets
//         */
//        public PicRename(String drivePath, String templateFilePath) {
//            mFilePath = drivePath;
//            mTemplateFilePath = templateFilePath;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        /**
//         * Method to rename and save the template file
//         */
//        private void renameTemplateFile(String srcFileName, int count) {
////            shape_collage_48.png
////            grid_collage_0.png
//            Tracer.debug(TAG, "renameTemplateFile(" + mFilePath + ")");
//            srcFileName = srcFileName.replace(".png", "_");
//            int countGrid = 0, countShap = 0;
//            try {
//                AssetManager assets = getAssets();
//                String[] list = assets.list(mTemplateFilePath);
//                for (String fileName : list) {
//                    try {
//                        if (!fileName.contains(srcFileName)) {
//                            continue;
//                        }
//                        Tracer.debug(TAG, "renameTemplateFile(" + mFilePath + ")" + fileName);
//                        String newFileName = "";
//                        if (fileName.startsWith("grid")) {
//                            newFileName = "template_grid_" + count + "_" + (++countGrid);
//                        } else {
//                            newFileName = "template_shape_" + count + "_" + (++countShap);
//                        }
//
//                        File subDir = new File(AppConfig.BASE_ARCHIVE_PATH + "/template/" + mFilePath);
//                        if (!subDir.exists()) {
//                            subDir.mkdirs();
//                        }
//
//                        File file = new File(AppConfig.BASE_ARCHIVE_PATH + "/template/" + mFilePath, newFileName + ".png");
//                        if (!file.exists()) {
//                            file.createNewFile();
//                        }
//                        FileOutputStream fileOutputStream = new FileOutputStream(file);
//                        InputStream inputStream = assets.open(mTemplateFilePath + "/" + fileName);
//                        byte data[] = new byte[1024];
//                        int validDataSize = 0;
//                        while ((validDataSize = inputStream.read(data)) != -1) {
//                            fileOutputStream.write(data, 0, validDataSize);
//                        }
//                        fileOutputStream.flush();
//                        fileOutputStream.close();
//                        inputStream.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                Tracer.debug(TAG, "renameTemplateFile()" + e.getMessage());
//            }
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            AssetManager assets = getAssets();
//            try {
//                int countGrid = 0, countShape = 0, countTemplate = 0;
//                String[] list = assets.list(mFilePath);
//                for (String fileName : list) {
//                    Tracer.debug(TAG, "doInBackground(" + mFilePath + ")" + fileName);
//                    try {
//                        String newFileName = "";
//                        if (fileName.startsWith("grid")) {
//                            newFileName = "thumb_grid_" + (++countGrid);
//                            renameTemplateFile(fileName, countGrid);
//                        } else {
//                            newFileName = "thumb_shape_" + (++countShape);
//                            renameTemplateFile(fileName, countShape);
//                        }
//
//                        File subDir = new File(AppConfig.BASE_ARCHIVE_PATH, mFilePath);
//                        if (!subDir.exists()) {
//                            subDir.mkdirs();
//                        }
//                        File file = new File(AppConfig.BASE_ARCHIVE_PATH + "/" + mFilePath, newFileName + ".png");
//                        if (!file.exists()) {
//                            file.createNewFile();
//                        }
//                        FileOutputStream fileOutputStream = new FileOutputStream(file);
//                        InputStream inputStream = assets.open(mFilePath + "/" + fileName);
//                        byte data[] = new byte[1024];
//                        int validDataSize = 0;
//                        while ((validDataSize = inputStream.read(data)) != -1) {
//                            fileOutputStream.write(data, 0, validDataSize);
//                        }
//                        fileOutputStream.flush();
//                        fileOutputStream.close();
//                        inputStream.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//    }
}


