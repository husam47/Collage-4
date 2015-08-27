package com.adroitstudio.photocollage.view;

import java.util.Hashtable;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.ExceptionHandler;
import com.adroitstudio.photocollage.R;
import com.adroitstudio.photocollage.Utils;
import com.adroitstudio.photocollage.controller.Bucket;
import com.adroitstudio.photocollage.controller.Controller;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.view.CollageFragment.OnCollageFragmentListener;
import com.adroitstudio.photocollage.view.GalleryFragment.OnGalleryFragmentListener;
import com.adroitstudio.photocollage.view.HomeFragment.OnHomeFragmentListener;
import com.adroitstudio.photocollage.view.ShapeThumbFragment.OnShapThumbFragmentListener;

public class MainActivity extends Activity implements OnHomeFragmentListener, OnGalleryFragmentListener, OnCollageFragmentListener, Constants, OnShapThumbFragmentListener {

	public static final int DIALOG_CLIP = 1001;
	private Controller mController;
	private Fragment mFragmentCurrent;
	private Hashtable<String, Bucket> mBucketData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		ExceptionHandler.attachActivity();
		mBucketData = new Hashtable<String, Bucket>();
		getActionBar().hide();
		mController = Controller.getInstance();
		try {
			new PicListInitilizer().execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		if (mFragmentCurrent != null && mFragmentCurrent instanceof OnBackPress && ((OnBackPress) mFragmentCurrent).onBackPressed()) {
			return;
		}
		finish();
	}

	// ======================================================================================
	// METHODS TO LISTEN HOME FRAGMENT EVENT
	// ======================================================================================

	@Override
	public void onHomeFragmentGridStyleSelected() {
		mController.setCollageType(COLLAGE_TYPE_FIXED);
		GalleryFragment galleryFragment = new GalleryFragment();
		galleryFragment.setGalleryData(mBucketData);
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = galleryFragment).commit();
	}

	@Override
	public void onHomeFragmentFreeStyleSelected() {
		mController.setCollageType(COLLAGE_TYPE_FREE);
		GalleryFragment galleryFragment = new GalleryFragment();
		galleryFragment.setGalleryData(mBucketData);
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = galleryFragment).commit();
	}

	@Override
	public void onHomeFragmentShapeStyleSelected() {
		mController.setCollageType(COLLAGE_TYPE_SHAPE);
		ShapeThumbFragment shapeThumbFragment = new ShapeThumbFragment();
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = shapeThumbFragment).commit();
	}

	// ======================================================================================
	// METHODS TO LISTEN GALLERY FRAGMENT EVENT
	// ======================================================================================

	@Override
	public void onGalleryFragmentBackAction() {
		getActionBar().hide();
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = new HomeFragment()).commit();
	}

	@Override
	public void onGalleryFragmentDoneAction() {
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = new CollageFragment()).commit();
	}

	// ======================================================================================
	// METHODS TO LISTEN COLLAGE FRAGMENT EVENT
	// ======================================================================================

	@Override
	public void onFragmentCollageAddNewPic() {
		mController.setCollageType(mController.getCollageType());
		GalleryFragment galleryFragment = new GalleryFragment();
		galleryFragment.setGalleryData(mBucketData);
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = galleryFragment).commit();
	}

	@Override
	public void onFragmentCollageAddNewShape() {
		mController.setCollageType(mController.getCollageType());
		ShapeThumbFragment shapeThumbFragment = new ShapeThumbFragment();
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = shapeThumbFragment).commit();
	}

	@Override
	public void onFragmentCollageOnShowHomeScreen() {
		getActionBar().hide();
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = new HomeFragment()).commit();
	}

	// ======================================================================================
	// METHODS TO LISTEN SHAPE FRAGMENT EVENT
	// ======================================================================================

	@Override
	public void onShapThumbFragmentShowHomeFragment() {
		getActionBar().hide();
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = new HomeFragment()).commit();
	}

	@Override
	public void onShappeThumbFragmentOpenGallery(int index) {
		Controller controller = Controller.getInstance();
		controller.setThumbShapeName(index);
		controller.setCollageType(COLLAGE_TYPE_SHAPE);
		GalleryFragment galleryFragment = new GalleryFragment();
		galleryFragment.setGalleryData(mBucketData);
		getFragmentManager().beginTransaction().replace(R.id.parent_container, mFragmentCurrent = galleryFragment).commit();
	}

	// ======================================================================================
	// METHODS TO NOTIFY BACKPRESSED EVENT TO FRAGMENT
	// ======================================================================================

	/**
	 * @author THE-MKR Callback to notify the fragment that the backpressed
	 *         event occur
	 */
	public interface OnBackPress {
		/**
		 * Method to notify the backpressed event
		 * 
		 * @return True if pause the backpressed event , False otherwise
		 */
		public boolean onBackPressed();
	}

	/**
	 * @author THE-MKR ASYNTASK TO INITILIZE THE PIC LIST
	 */
	private class PicListInitilizer extends AsyncTask<Void, Void, Void> {
		private ProgressDialog mProgressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			try {
				mProgressDialog = new ProgressDialog(MainActivity.this);
				mProgressDialog.setTitle("Please wait!!");
				mProgressDialog.setMessage("Initilizing Pic list");
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Utils.getDefaultThumbBitmap(MainActivity.this);
				// INITILIZE THE LIST OF ALL THE IMAGES
				initilizeImageList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			try {
				if (mProgressDialog != null) {
					mProgressDialog.dismiss();
				}
				getActionBar().hide();
				mFragmentCurrent = new HomeFragment();
				getFragmentManager().beginTransaction().add(R.id.parent_container, mFragmentCurrent).commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Method to generate the Table of all the images in the device as per
		 * there folder name This method should be called from Thread other then
		 * UI thread
		 */
		protected void initilizeImageList() {
			// EXTRACT EXTERNAL IMAGES
			Cursor cursor = MainActivity.this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
			if (cursor != null) {
				while (cursor.moveToNext()) {
					String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
					long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
					String bucketName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
					// INSERT PIC DATA AS PER THEIR RELATED BUCKET
					if (!mBucketData.containsKey(bucketName)) {
						mBucketData.put(bucketName, new Bucket(bucketName));
					}
					mBucketData.get(bucketName).addPicData(new ImageData(path, id, SOURCE_DEVICE, IMAGE_TYPE_THUMBNAIL_MICRO));
				}
			}
			cursor.close();
			// EXTRACT INTERNAL IMAGES
			Cursor cursorInternal = getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, null, null, null, null);
			if (cursorInternal != null) {
				while (cursorInternal.moveToNext()) {
					String path = cursorInternal.getString(cursorInternal.getColumnIndex(MediaStore.Images.Media.DATA));
					long id = cursorInternal.getLong(cursorInternal.getColumnIndex(MediaStore.Images.Media._ID));
					String bucketName = cursorInternal.getString(cursorInternal.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
					// INSERT PIC DATA AS PER THEIR RELATED BUCKET
					if (!mBucketData.containsKey(bucketName)) {
						mBucketData.put(bucketName, new Bucket(bucketName));
					}
					mBucketData.get(bucketName).addPicData(new ImageData(path, id, SOURCE_DEVICE, IMAGE_TYPE_THUMBNAIL_MICRO));
				}
			}
			cursorInternal.close();
		}

	}
}
