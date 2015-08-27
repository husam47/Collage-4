package com.adroitstudio.photocollage.view;

import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.R;
import com.adroitstudio.photocollage.Utils;
import com.adroitstudio.photocollage.controller.Bucket;
import com.adroitstudio.photocollage.controller.Controller;
import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.lib.TwoWayGridView;
import com.adroitstudio.photocollage.view.MainActivity.OnBackPress;
import com.adroitstudio.photocollage.view.custom_view.ThumbImageView;
import com.adroitstudio.photocollage.view.custom_view.ThumbImageView.OnImageCurropted;
import com.adroitstudio.photocollage.view.custom_view.collage.LayoutChildUtils;

@SuppressLint("NewApi")
public class GalleryFragment extends Fragment implements OnClickListener, Constants, OnItemClickListener, OnBackPress {

	private Hashtable<String, Bucket> mBucketData;
	private BitmapDrawable mBitmapDrawableActivityBackground, mBitmapAlbumFrame, mBitmapPicFrame;
	private AdapterAlbum mAdapterAlbum;
	private AdapterPic mAdapterPic;
	private AdapterSelectedPic mAdapterSelectedPic;
	private LinkedList<ImageData> mSelectedImageData;
	private int mThumbnailDimension;
	private Controller mController;

	public GalleryFragment() {
		mBucketData = new Hashtable<String, Bucket>();
		mController = Controller.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// INIT ACTION BAR
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.show();
		actionBar.setDisplayShowCustomEnabled(true);
		if (android.os.Build.VERSION.SDK_INT >= 14) {
			actionBar.setHomeButtonEnabled(false);
		}
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setCustomView(R.layout.headder_gallery);
		getActivity().findViewById(R.id.headder_gallery_imageView_back).setOnClickListener(this);
		getActivity().findViewById(R.id.headder_gallery_imageView_delete).setOnClickListener(this);
		getActivity().findViewById(R.id.headder_gallery_imageView_next).setOnClickListener(this);
		mThumbnailDimension = (int) ((float) getResources().getDisplayMetrics().widthPixels * 0.24F);
		// INIT FRAGMENT VIEW
		View parentView = inflater.inflate(R.layout.fragment_gallery, container, false);
		mSelectedImageData = new LinkedList<ImageData>(Controller.getInstance().getSelectedImage());
		mAdapterAlbum = new AdapterAlbum();
		mAdapterPic = new AdapterPic();
		mAdapterSelectedPic = new AdapterSelectedPic();
		// INITILIZE GRID VIEW
		GridView gridView = (GridView) parentView.findViewById(R.id.fragment_gallery_gridView_album);
		gridView.setOnItemClickListener(this);
		gridView.setVerticalSpacing((int) ((float) getResources().getDisplayMetrics().widthPixels * 0.02F));
		// INIT TWO WAY GRID VIEW
		TwoWayGridView twoWayGridView = (TwoWayGridView) parentView.findViewById(R.id.fragment_gallery_gridView_selecete_pic);
		twoWayGridView.setAdapter(mAdapterSelectedPic);
		twoWayGridView.getLayoutParams().height = mThumbnailDimension + (int) ((float) mThumbnailDimension * 0.1F);
		int padding = (int) ((float) mThumbnailDimension * 0.05F);
		twoWayGridView.setPadding(padding, padding, padding, padding);
		twoWayGridView.setHorizontalSpacing(3 * padding);
		setSelectedPicCount(parentView);
		// INITILIZE COLLECTION
		try {
			new Initilizer().execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parentView;
	}

	@Override
	public void onDestroyView() {
		getActivity().getActionBar().hide();
		if (mBitmapDrawableActivityBackground != null && mBitmapDrawableActivityBackground.getBitmap() != null) {
			mBitmapDrawableActivityBackground.getBitmap().recycle();
		}
		if (mBitmapAlbumFrame != null && mBitmapAlbumFrame.getBitmap() != null) {
			mBitmapAlbumFrame.getBitmap().recycle();
		}
		if (mBitmapPicFrame != null && mBitmapPicFrame.getBitmap() != null) {
			mBitmapPicFrame.getBitmap().recycle();
		}
		ImageCache.getInstance(getActivity()).clear();
		super.onDestroyView();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.headder_gallery_imageView_back:
			if (getView() != null && getActivity() instanceof OnGalleryFragmentListener) {
				((OnGalleryFragmentListener) getActivity()).onGalleryFragmentBackAction();
			}
			break;
		case R.id.headder_gallery_imageView_delete:
			mSelectedImageData.clear();
			mAdapterSelectedPic.notifyDataSetChanged();
			break;
		case R.id.headder_gallery_imageView_next:
		default:
			if (getView() != null && getActivity() instanceof OnGalleryFragmentListener && mSelectedImageData.size() > 0) {
				if (mController.getCollageType() == COLLAGE_TYPE_SHAPE) {
					if (mSelectedImageData.size() == LayoutChildUtils.getNumberOfChildInShape(mController.getThumbShapeIndex())) {
						mController.addSelectedPic(mSelectedImageData);
						((OnGalleryFragmentListener) getActivity()).onGalleryFragmentDoneAction();
					} else {
						Toast.makeText(getActivity(), "You should choose " + LayoutChildUtils.getNumberOfChildInShape(mController.getThumbShapeIndex()) + " pic", Toast.LENGTH_SHORT).show();
					}
				} else {
					if (mSelectedImageData.size() <= 9) {
						mController.addSelectedPic(mSelectedImageData);
						((OnGalleryFragmentListener) getActivity()).onGalleryFragmentDoneAction();
					} else {
						Toast.makeText(getActivity(), "You can choose 9 pic at most ", Toast.LENGTH_SHORT).show();
					}
				}
			}
			break;
		}
	}

	@Override
	public boolean onBackPressed() {
		if (getView() != null) {
			if (((GridView) getView().findViewById(R.id.fragment_gallery_gridView_album)).getAdapter().equals(mAdapterPic)) {
				setAlbumAdapter();
			} else if (getActivity() instanceof OnGalleryFragmentListener) {
				((OnGalleryFragmentListener) getActivity()).onGalleryFragmentBackAction();
			}
		}
		return true;
	}

	/**
	 * Method to put the gallery data
	 * 
	 * @param bucketData
	 */
	public void setGalleryData(Hashtable<String, Bucket> bucketData) {
		mBucketData.clear();
		mBucketData.putAll(bucketData);
	}

	/**
	 * Method to initilize Bucket grid view
	 */
	private void setAlbumAdapter() {
		if (getView() != null) {
			GridView gridView = (GridView) getView().findViewById(R.id.fragment_gallery_gridView_album);
			mAdapterAlbum.setData(mBucketData.keySet());
			gridView.setAdapter(mAdapterAlbum);
			gridView.startLayoutAnimation();
			((TextView) getActivity().findViewById(R.id.headder_gallery_textView_albumName)).setText("Gallery");
		}
	}

	/**
	 * Method to initilize Bucket grid view
	 */
	private void setPicAdapter(String bucketName) {
		if (getView() != null) {
			GridView gridView = (GridView) getView().findViewById(R.id.fragment_gallery_gridView_album);
			mAdapterPic.setData(bucketName);
			gridView.setAdapter(mAdapterPic);
			gridView.startLayoutAnimation();
			((TextView) getActivity().findViewById(R.id.headder_gallery_textView_albumName)).setText(bucketName);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (parent.getAdapter().equals(mAdapterAlbum)) {
			setPicAdapter(mAdapterAlbum.getItem(position).getBucketName());
		} else if (parent.getAdapter().equals(mAdapterPic)) {
			ImageData item = mAdapterPic.getItem(position);
			if (!mSelectedImageData.contains(item)) {
				mSelectedImageData.addFirst(item);
				mAdapterSelectedPic.notifyDataSetChanged();
			}
			if (((mController.getCollageType() == COLLAGE_TYPE_SHAPE) && (mSelectedImageData.size() > LayoutChildUtils.getNumberOfChildInShape(mController.getThumbShapeIndex()))) || (mSelectedImageData.size() > 9)) {
				mSelectedImageData.removeLast();
			}
			setSelectedPicCount(getView());
		}
	}

	/**
	 * Method to set the pic counts
	 */
	private void setSelectedPicCount(View parent) {
		if (parent == null && getView() != null) {
			parent = getView();
		}
		if (mController.getCollageType() == COLLAGE_TYPE_SHAPE) {
			((TextView) parent.findViewById(R.id.fragment_gallery_textView_info)).setText("You should choose " + LayoutChildUtils.getNumberOfChildInShape(mController.getThumbShapeIndex()) + " pic  (" + mSelectedImageData.size() + ")");
		} else {
			((TextView) parent.findViewById(R.id.fragment_gallery_textView_info)).setText("You can choose 9 pic at most  (" + mSelectedImageData.size() + ")");
		}
	}

	// ======================================================================================
	// Asyntask to initilize app list
	// ======================================================================================

	/**
	 * @author THE-MKR Adapter to show pics
	 */
	public class AdapterAlbum extends BaseAdapter implements OnImageCurropted {

		private LinkedList<String> mBucketNames;

		public AdapterAlbum() {
			mBucketNames = new LinkedList<String>();
		}

		/**
		 * Method to add new image data
		 * 
		 * @param bucket
		 */
		public void setData(Collection<String> bucketName) {
			mBucketNames.clear();
			mBucketNames.addAll(bucketName);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mBucketNames.size();
		}

		@Override
		public Bucket getItem(int position) {
			return mBucketData.get(mBucketNames.get(position));
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.grid_view_thumb_album, null);
				LayoutParams layoutParams = convertView.findViewById(R.id.grid_view_thumb_album_pic).getLayoutParams();
				layoutParams.width = layoutParams.height = mThumbnailDimension;
				layoutParams = convertView.findViewById(R.id.grid_view_thumb_album_frame).getLayoutParams();
				layoutParams.width = layoutParams.height = mThumbnailDimension;
			}
			((ThumbImageView) convertView.findViewById(R.id.grid_view_thumb_album_pic)).setImageData(getItem(position).getImageDatas().get(0), this);
			convertView.findViewById(R.id.grid_view_thumb_album_frame).setBackgroundDrawable(mBitmapAlbumFrame);
			return convertView;
		}

		@Override
		public void onImageCurropted(ImageData imageData) {
			for (int i = 0; i < mBucketNames.size(); i++) {
				String bucketName = mBucketNames.get(i);
				Bucket bucket = mBucketData.get(bucketName);
				bucket.removePicData(imageData);
				if (bucket.getImageDatas().size() == 0) {
					mBucketData.remove(bucketName);
					setAlbumAdapter();
				}
			}
		}
	}

	/**
	 * @author THE-MKR Adapter to show pics
	 */
	public class AdapterPic extends BaseAdapter implements OnImageCurropted {

		private LinkedList<ImageData> mBucketImageDate;
		private String bucketName;

		public AdapterPic() {
			mBucketImageDate = new LinkedList<ImageData>();
		}

		/**
		 * Method to add new image data
		 * 
		 * @param bucket
		 */
		public void setData(String bucketName) {
			mBucketImageDate.clear();
			mBucketImageDate.addAll(mBucketData.get(bucketName).getImageDatas());
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mBucketImageDate.size();
		}

		@Override
		public ImageData getItem(int position) {
			return mBucketImageDate.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.grid_view_thumb_album, null);
				LayoutParams layoutParams = convertView.findViewById(R.id.grid_view_thumb_album_pic).getLayoutParams();
				layoutParams.width = layoutParams.height = mThumbnailDimension;
				layoutParams = convertView.findViewById(R.id.grid_view_thumb_album_frame).getLayoutParams();
				layoutParams.width = layoutParams.height = mThumbnailDimension;
			}
			((ThumbImageView) convertView.findViewById(R.id.grid_view_thumb_album_pic)).setImageData(getItem(position), this);
			convertView.findViewById(R.id.grid_view_thumb_album_frame).setBackgroundDrawable(mBitmapPicFrame);
			return convertView;
		}

		@Override
		public void onImageCurropted(ImageData imageData) {
			mBucketImageDate.remove(imageData);
			if (mBucketData.size() == 0) {
				mBucketData.remove(mBucketData.get(bucketName));
				setAlbumAdapter();
			}
		}
	}

	/**
	 * @author THE-MKR Adapter to show pics
	 */
	public class AdapterSelectedPic extends BaseAdapter {

		@Override
		public int getCount() {
			return mSelectedImageData.size();
		}

		@Override
		public ImageData getItem(int position) {
			return mSelectedImageData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.grid_view_thumb_selected_pic, null);
				LayoutParams layoutParamsImageView = convertView.findViewById(R.id.grid_view_thumb_selected_pic).getLayoutParams();
				layoutParamsImageView.width = layoutParamsImageView.height = mThumbnailDimension;
				View viewCross = convertView.findViewById(R.id.grid_view_thumb_selected_cross);
				LayoutParams layoutParamsCross = viewCross.getLayoutParams();
				layoutParamsCross.width = layoutParamsCross.height = (int) ((float) mThumbnailDimension * 0.4F);
				viewCross.setPadding((int) ((float) layoutParamsCross.width * 0.25F), 0, 0, (int) ((float) layoutParamsCross.width * 0.25F));
			}
			((ThumbImageView) convertView.findViewById(R.id.grid_view_thumb_selected_pic)).setImageData(mSelectedImageData.get(position));
			convertView.findViewById(R.id.grid_view_thumb_selected_cross).setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mSelectedImageData.remove(getItem(position));
					setSelectedPicCount(null);
					notifyDataSetChanged();
				}
			});
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
			try {
				mProgressDialog = new ProgressDialog(getActivity());
				mProgressDialog.setTitle("Please wait!!");
				mProgressDialog.setMessage("Initilizing Image");
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
				if (mBitmapAlbumFrame == null || mBitmapAlbumFrame.getBitmap() == null || mBitmapAlbumFrame.getBitmap().isRecycled()) {
					BitmapFactory.decodeResource(getResources(), R.drawable.album_bg, options);
					options.inJustDecodeBounds = false;
					options.inSampleSize = Utils.calculateInSampleSize(options.outWidth, options.outHeight, mThumbnailDimension, mThumbnailDimension);
					mBitmapAlbumFrame = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.album_bg, options));
				}
				if (mBitmapPicFrame == null || mBitmapPicFrame.getBitmap() == null || mBitmapPicFrame.getBitmap().isRecycled()) {
					BitmapFactory.decodeResource(getResources(), R.drawable.pic_bg, options);
					options.inJustDecodeBounds = false;
					options.inSampleSize = Utils.calculateInSampleSize(options.outWidth, options.outHeight, mThumbnailDimension, mThumbnailDimension);
					mBitmapPicFrame = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.pic_bg, options));
				}
				// INITILIZE BG BITMAP
				if (mBitmapDrawableActivityBackground == null || mBitmapDrawableActivityBackground.getBitmap() == null || mBitmapDrawableActivityBackground.getBitmap().isRecycled()) {
					options.inJustDecodeBounds = true;
					BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
					DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
					options.inSampleSize = Utils.calculateInSampleSize(options.outWidth, options.outHeight, displayMetrics.widthPixels, displayMetrics.heightPixels);
					options.inJustDecodeBounds = false;
					mBitmapDrawableActivityBackground = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.background, options));
				}
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
					getView().findViewById(R.id.fragment_gallery_parent).setBackgroundDrawable(mBitmapDrawableActivityBackground);
				}
				setAlbumAdapter();
			} catch (Exception e) {
				e.printStackTrace();
			}
			setAlbumAdapter();
		}
	}

	/**
	 * @author THE-MKR parent activity must implement this interface to listen
	 *         the user action in Gallery fragment
	 */
	public interface OnGalleryFragmentListener {
		/**
		 * Method to notify that user press the back button in action bar
		 */
		public void onGalleryFragmentBackAction();

		/**
		 * Method to notify that user select pic for collage and want to move
		 * further
		 */
		public void onGalleryFragmentDoneAction();
	}

}
