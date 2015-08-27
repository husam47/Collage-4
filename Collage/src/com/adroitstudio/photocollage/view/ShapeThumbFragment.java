package com.adroitstudio.photocollage.view;

import java.io.IOException;
import java.util.LinkedList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.R;
import com.adroitstudio.photocollage.Utils;
import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.view.MainActivity.OnBackPress;
import com.adroitstudio.photocollage.view.custom_view.ThumbImageView;

public class ShapeThumbFragment extends Fragment implements Constants, OnItemClickListener, OnBackPress {

	private BitmapDrawable mBitmapDrawableActivityBackground;
	private AdapterAlbum mAdapterAlbum;
	private int mThumbnailDimension;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mThumbnailDimension = (int) ((float) getResources().getDisplayMetrics().widthPixels * 0.2F);
		getActivity().getActionBar().hide();
		// INIT FRAGMENT VIEW
		View parentView = inflater.inflate(R.layout.fragment_shape_thumb, container, false);
		LinkedList<ImageData> mThumbImageData = new LinkedList<ImageData>();
		// INITILIZE SHAPE THUMBNAIL LIST
		try {
			String[] list = getActivity().getAssets().list(ASSETS_SHAPE_THUMBNAIL);
			for (int i = 0; i < list.length; i++) {
				Log.e("THE-MKR", "ShapeThumbFragment.onCreateView()" + "  " + ASSETS_SHAPE_THUMBNAIL + "/" + list[i]);
				mThumbImageData.add(new ImageData(ASSETS_SHAPE_THUMBNAIL + "/" + list[i], -1, SOURCE_APPLICATION, IMAGE_TYPE_THUMBNAIL_MICRO));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		mAdapterAlbum = new AdapterAlbum(mThumbImageData);
		// INITILIZE GRID VIEW
		GridView gridView = (GridView) parentView.findViewById(R.id.fragment_shapes_thumb_gridView_album);
		gridView.setOnItemClickListener(this);
		gridView.setAdapter(mAdapterAlbum);
		try {
			new Initilizer().execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parentView;
	}

	@Override
	public void onDestroyView() {
		if (mBitmapDrawableActivityBackground != null && mBitmapDrawableActivityBackground.getBitmap() != null) {
			mBitmapDrawableActivityBackground.getBitmap().recycle();
		}
		ImageCache.getInstance(getActivity()).clear();
		super.onDestroyView();
	}

	@Override
	public boolean onBackPressed() {
		if (getActivity() instanceof OnShapThumbFragmentListener) {
			((OnShapThumbFragmentListener) getActivity()).onShapThumbFragmentShowHomeFragment();
		}
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (getActivity() != null && getActivity() instanceof OnShapThumbFragmentListener) {
			((OnShapThumbFragmentListener) getActivity()).onShappeThumbFragmentOpenGallery(position);
		}
	}

	/**
	 * @author THE-MKR Adapter to show pics
	 */
	public class AdapterAlbum extends BaseAdapter {

		private LinkedList<ImageData> mImageData;

		public AdapterAlbum(LinkedList<ImageData> thumbImageData) {
			mImageData = thumbImageData;
		}

		@Override
		public int getCount() {
			return mImageData.size();
		}

		@Override
		public ImageData getItem(int position) {
			return mImageData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.grid_view_thumb_pic, null);
				LayoutParams layoutParams = convertView.findViewById(R.id.grid_view_thumb_pic).getLayoutParams();
				layoutParams.width = layoutParams.height = mThumbnailDimension;
			}
			((ThumbImageView) convertView.findViewById(R.id.grid_view_thumb_pic)).setImageData(getItem(position));
			return convertView;
		}
	}

	/**
	 * @author THE-MKR ASYNTASK TO INITILIZE THE PIC LIST
	 */
	private class Initilizer extends AsyncTask<Void, Void, Void> {
		private ProgressDialog mProgressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (mBitmapDrawableActivityBackground != null && mBitmapDrawableActivityBackground.getBitmap() != null) {
				mBitmapDrawableActivityBackground.getBitmap().recycle();
			}
			try {
				mProgressDialog = new ProgressDialog(getActivity());
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
				// INITILIZE FRAME BITMAP
				Options options = new Options();
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
				DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
				options.inSampleSize = Utils.calculateInSampleSize(options.outWidth, options.outHeight, displayMetrics.widthPixels, displayMetrics.heightPixels);
				options.inJustDecodeBounds = false;
				mBitmapDrawableActivityBackground = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.background, options));
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
				if (getView() != null) {
					getView().findViewById(R.id.fragment_shapes_thumb_parent).setBackgroundDrawable(mBitmapDrawableActivityBackground);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author THE-MKR Listener to notify activity about the event occur in shae
	 *         fragment
	 */
	public interface OnShapThumbFragmentListener {

		/**
		 * Method to notify that the back pressed event is occurred and main
		 * home page is showing to user
		 */
		public void onShapThumbFragmentShowHomeFragment();

		/**
		 * Method to notify that user select the shape and now he is going to
		 * open the gallery
		 * 
		 * @param index
		 *            Index of the shape in list
		 */
		public void onShappeThumbFragmentOpenGallery(int index);
	}

}
