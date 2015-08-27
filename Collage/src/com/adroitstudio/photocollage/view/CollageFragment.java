package com.adroitstudio.photocollage.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.R;
import com.adroitstudio.photocollage.Utils;
import com.adroitstudio.photocollage.controller.ClipImageData;
import com.adroitstudio.photocollage.controller.Controller;
import com.adroitstudio.photocollage.controller.ImageCache;
import com.adroitstudio.photocollage.controller.ImageData;
import com.adroitstudio.photocollage.lib.TwoWayAdapterView;
import com.adroitstudio.photocollage.lib.TwoWayAdapterView.OnItemClickListener;
import com.adroitstudio.photocollage.lib.TwoWayGridView;
import com.adroitstudio.photocollage.view.MainActivity.OnBackPress;
import com.adroitstudio.photocollage.view.custom_view.ClipViewGroup;
import com.adroitstudio.photocollage.view.custom_view.ThumbImageView;
import com.adroitstudio.photocollage.view.custom_view.collage.CollageBackgroundView;
import com.adroitstudio.photocollage.view.custom_view.collage.CollageViewClipSmilly;
import com.adroitstudio.photocollage.view.custom_view.collage.CollageViewGroup;
import com.adroitstudio.photocollage.view.custom_view.collage.CollageViewGroup.OnMKRCollageViewGroupCallbackListener;
import com.adroitstudio.photocollage.view.custom_view.collage.OnCollageViewGroup;
import com.themkr.nativeeffect.NativeEffects;

@SuppressLint("NewApi")
public class CollageFragment extends Fragment implements OnClickListener, OnBackPress, Constants, OnItemClickListener, OnMKRCollageViewGroupCallbackListener, OnSeekBarChangeListener {
	private static final float MAX_HEIGHT = 0.6F;
	private static final float MAX_WIDTH = 0.95F;

	private BitmapDrawable mBitmapDrawableActivityBackground;
	private int mOptionDimension;
	private LinkedList<ImageData> mImageData;
	private Controller mController;
	private int mClickedViewId;
	private OptionAdapter mOptionAdapter, mOptionAdapterClipShape;
	private OnCollageViewGroup mOnCollageViewGroup;
	private int mRatioX, mRatioY;
	private Bitmap bitmapLockView, bitmapUnlockView;
	private ClipViewGroup mClipViewGroup;

	public CollageFragment() {
		mController = Controller.getInstance();
		mOptionAdapter = new OptionAdapter();
		mOptionAdapterClipShape = new OptionAdapter();
		mImageData = new LinkedList<ImageData>();
		mRatioX = mRatioY = 1;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// APPLY ACTION BAR
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.show();
		actionBar.setDisplayShowCustomEnabled(true);
		if (android.os.Build.VERSION.SDK_INT >= 14) {
			actionBar.setHomeButtonEnabled(false);
		}
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setCustomView(R.layout.headder_collage);
		getActivity().findViewById(R.id.headder_collage_imageView_save).setOnClickListener(this);
		getActivity().findViewById(R.id.headder_collage_imageView_share).setOnClickListener(this);
		mOptionDimension = actionBar.getHeight();
		// INITILIZE LOCK UNLOCK BITMAP
		bitmapLockView = BitmapFactory.decodeResource(getResources(), R.drawable.mkr_option_lock);
		bitmapUnlockView = BitmapFactory.decodeResource(getResources(), R.drawable.mkr_option_unlock);
		// INITILIZE VIEW
		View parentView = inflater.inflate(R.layout.fragment_collage, container, false);
		CollageViewGroup collageViewGroup = (CollageViewGroup) parentView.findViewById(R.id.fragment_collage_collage_view_group);
		mOnCollageViewGroup = (OnCollageViewGroup) collageViewGroup;
		collageViewGroup.setCollageViewGroupCallbackListener(this);
		// SET CHILD VIEW RATIO
		setDimension(parentView);
		// SET COLLAGE DIMENSION RATIO
		setCollageDimensionRatio(parentView, mRatioX, mRatioY);
		// SET ON CLICK LISTENER
		ViewGroup viewGroup = (ViewGroup) parentView.findViewById(R.id.fragment_collage_layout_option_all);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			viewGroup.getChildAt(i).setOnClickListener(this);
		}
		viewGroup = (ViewGroup) parentView.findViewById(R.id.fragment_collage_layout_option_singal);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			viewGroup.getChildAt(i).setOnClickListener(this);
		}
		parentView.findViewById(R.id.fragment_collage_dialog_clip_done).setOnClickListener(this);
		((SeekBar) parentView.findViewById(R.id.fragment_collage_seekBar_1)).setOnSeekBarChangeListener(this);
		((SeekBar) parentView.findViewById(R.id.fragment_collage_seekBar_2)).setOnSeekBarChangeListener(this);
		parentView.findViewById(R.id.fragment_collage_layout_option_seek).setVisibility(View.INVISIBLE);
		parentView.findViewById(R.id.fragment_collage_layout_option_singal).setVisibility(View.INVISIBLE);
		parentView.findViewById(R.id.fragment_collage_dialog_parent).setVisibility(View.INVISIBLE);
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
		ImageCache.getInstance(getActivity()).clear();
		super.onDestroyView();
	}

	@Override
	public void onResume() {
		super.onResume();
		mImageData.clear();
		LinkedList<ImageData> selectedImage = mController.getSelectedImage();
		for (ImageData imageData : selectedImage) {
			mImageData.add(new ImageData(imageData.getPath(), imageData.getImageId(), imageData.getSource(), (selectedImage.size() < 4) ? IMAGE_TYPE_SIZE_1234 : IMAGE_TYPE_SIZE_56789));
		}
		mOnCollageViewGroup.onChangeCollageType(mController.getCollageType());
		mOnCollageViewGroup.onChangeCollageLayout((mController.getCollageType() == COLLAGE_TYPE_SHAPE) ? mController.getThumbShapeIndex() : LAYOUT_A1);
		mOnCollageViewGroup.onAddCollageImageItem(mImageData);
		showOptionListForAllItems();
	}

	@Override
	public void OnMKRCollageViewGroupSwapCompleted() {
		showOptionListForAllItems();
	}

	@Override
	public void OnMKRCollageViewGroupOnItemSelected() {
		showOptionListForSingalItems();
	}

	@Override
	public void onMKRCollageViewGroupOnItemUnselected() {
		showOptionListForAllItems();
	}

	@Override
	public boolean onBackPressed() {
		if (getView() != null) {
			if (getView().findViewById(R.id.fragment_collage_dialog_parent).getVisibility() == View.VISIBLE) {
				getView().findViewById(R.id.fragment_collage_dialog_parent).setVisibility(View.INVISIBLE);
				mOptionAdapter.setData(null);
			} else if (getView().findViewById(R.id.fragment_collage_layout_option_seek).getVisibility() == View.VISIBLE) {
				getView().findViewById(R.id.fragment_collage_layout_option_seek).setVisibility(View.INVISIBLE);
				mOptionAdapter.setData(null);
			} else if (mOptionAdapter.getCount() > 0) {
				mOptionAdapter.setData(null);
			} else if (!mOnCollageViewGroup.onBackPressed()) {
				if (getActivity() instanceof OnCollageFragmentListener) {
					((OnCollageFragmentListener) getActivity()).onFragmentCollageOnShowHomeScreen();
				}
			}
			return true;
		}
		if (getActivity() instanceof OnCollageFragmentListener) {
			((OnCollageFragmentListener) getActivity()).onFragmentCollageOnShowHomeScreen();
		}
		return true;
	}

	@Override
	public void onClick(View view) {
		if (getView() != null) {
			getView().findViewById(R.id.fragment_collage_layout_option_seek).setVisibility(View.INVISIBLE);
			switch (mClickedViewId = view.getId()) {
			case R.id.fragment_collage_dialog_clip_done:
				if (mClipViewGroup != null && mOnCollageViewGroup != null) {
					mOnCollageViewGroup.onAddCollageClipItem(mClipViewGroup.onGetFinalImageData());
				} else {
					Toast.makeText(getActivity(), "Illegal operation....", Toast.LENGTH_SHORT).show();
				}
				getView().findViewById(R.id.fragment_collage_dialog_parent).setVisibility(View.INVISIBLE);
				break;
			case R.id.headder_collage_imageView_save:
				if (getView().findViewById(R.id.fragment_collage_dialog_parent).getVisibility() == View.INVISIBLE) {
					new AsyntaskSaveCollageBitmap().execute(false);
				}
				break;
			case R.id.headder_collage_imageView_share:
				if (getView().findViewById(R.id.fragment_collage_dialog_parent).getVisibility() == View.INVISIBLE) {
					new AsyntaskSaveCollageBitmap().execute(true);
				}
				break;
			case R.id.fragment_collage_imageView_all_background:
				if (mOnCollageViewGroup != null) {
					setListForBackground();
				}
				break;
			case R.id.fragment_collage_imageView_all_filter:
				if (mOnCollageViewGroup != null) {
					setListForApplyEffectOnAllBitmap();
				}
				break;
			case R.id.fragment_collage_imageView_all_frame_add_new:
				if (getActivity() instanceof OnCollageFragmentListener) {
					if (mController.getCollageType() == COLLAGE_TYPE_SHAPE) {
						((OnCollageFragmentListener) getActivity()).onFragmentCollageAddNewShape();
					} else {
						((OnCollageFragmentListener) getActivity()).onFragmentCollageAddNewPic();
					}
				}
				break;
			case R.id.fragment_collage_imageView_all_frame_background:
				if (mOnCollageViewGroup != null) {
					setListForCollageFrame();
				}
				break;
			case R.id.fragment_collage_imageView_all_border:
				if (mOnCollageViewGroup != null) {
					getView().findViewById(R.id.fragment_collage_layout_option_seek).setVisibility(View.VISIBLE);
					SeekBar seekBar1 = (SeekBar) getView().findViewById(R.id.fragment_collage_seekBar_1);
					SeekBar seekBar2 = (SeekBar) getView().findViewById(R.id.fragment_collage_seekBar_2);
					seekBar1.setMax(100);
					seekBar1.setProgress((int) (mOnCollageViewGroup.onGetCollageBorder() * 100.00F));
					((TextView) getView().findViewById(R.id.fragment_collage_seekBar_1_textView)).setText("Collage Border" + mRatioX);
					seekBar2.setMax(100);
					seekBar2.setProgress((int) (mOnCollageViewGroup.onGetCollageItemBorder() * 100.00F));
					((TextView) getView().findViewById(R.id.fragment_collage_seekBar_2_textView)).setText("Frame Border");
				}
				break;
			case R.id.fragment_collage_imageView_all_layout:
				if (mOnCollageViewGroup != null) {
					setListForLayout();
				}
				break;
			case R.id.fragment_collage_imageView_all_layout_ratio:
				if (mOnCollageViewGroup != null) {
					setListForCollageRatio();
				}
				break;
			case R.id.fragment_collage_imageView_all_sticker:
				if (mOnCollageViewGroup != null) {
					setListForClipArts();
				}
				break;
			case R.id.fragment_collage_imageView_single_back:
				if (mOnCollageViewGroup != null) {
					mOnCollageViewGroup.onBackPressed();
					showOptionListForAllItems();
				}
				break;
			case R.id.fragment_collage_imageView_single_clip:
				if (mOnCollageViewGroup != null && getActivity() instanceof OnCollageFragmentListener) {
					getView().findViewById(R.id.fragment_collage_dialog_parent).setVisibility(View.VISIBLE);
					setClipImageList();
					if (mClipViewGroup != null) {
						mClipViewGroup.onSetImageData(mOnCollageViewGroup.onGetSelectedChildData());
						mClipViewGroup.onSetClipPath();
					}
				}
				break;
			case R.id.fragment_collage_imageView_single_delete:
				if (mOnCollageViewGroup != null) {
					mOnCollageViewGroup.onDeleteItem();
				}
				break;
			case R.id.fragment_collage_imageView_single_filter:
				if (mOnCollageViewGroup != null && mOnCollageViewGroup.onGetSelectedView() != null && !(mOnCollageViewGroup.onGetSelectedView() instanceof CollageViewClipSmilly)) {
					setListForApplyEffectOnSingleBitmap();
				} else {
					Toast.makeText(getActivity(), "Invalid operation, Filters is not apply on clip art", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.fragment_collage_imageView_single_flip_horizontal:
				if (mOnCollageViewGroup != null) {
					mOnCollageViewGroup.onFlipHorizontally();
				}
				break;
			case R.id.fragment_collage_imageView_single_flip_vertical:
				if (mOnCollageViewGroup != null) {
					mOnCollageViewGroup.onFlipVerticall();
				}
				break;
			case R.id.fragment_collage_imageView_single_swap:
				if (mOnCollageViewGroup != null) {
					mOnCollageViewGroup.onSwapImageItem();
				}
				break;
			case R.id.fragment_collage_imageView_single_lockView:
				if (getView() != null) {
					ImageView imageView = (ImageView) getView().findViewById(R.id.fragment_collage_imageView_single_lockView);
					if (mOnCollageViewGroup != null && mOnCollageViewGroup.onIsViewLocked()) {
						mOnCollageViewGroup.onUnlockView();
						imageView.setImageBitmap(bitmapUnlockView);
					} else {
						mOnCollageViewGroup.onLockView();
						imageView.setImageBitmap(bitmapLockView);
					}
				}
				break;
			}
		}
	}

	@Override
	public void onItemClick(TwoWayAdapterView<?> parent, View view, int position, long id) {
		if (getView() != null) {
			switch (mClickedViewId) {
			case R.id.fragment_collage_imageView_single_clip:
				if (mClipViewGroup != null) {
					if (position == 0) {
						mClipViewGroup.onSetClipPath();
					} else {
						mClipViewGroup.onSetClipImageData(mOptionAdapterClipShape.getItem(position));
					}
				}
				break;
			case R.id.fragment_collage_imageView_all_background:
				((CollageBackgroundView) getView().findViewById(R.id.fragment_collage_collage_view_group_background)).onSetCollageBackground(mOptionAdapter.getItem(position));
				break;
			case R.id.fragment_collage_imageView_all_frame_background:
				if (mOnCollageViewGroup != null) {
					// mOnCollageViewGroup.onSetCollageFrameBackground(mOptionAdapter.getItem(position));
				}
				break;
			case R.id.fragment_collage_imageView_all_filter:
				if (mOnCollageViewGroup != null) {
					new AsyntaskApplyEffect(NativeEffects.getEffectsList()[position]).execute(false);
				}
				break;
			case R.id.fragment_collage_imageView_all_layout:
				if (mOnCollageViewGroup != null) {
					mOnCollageViewGroup.onChangeCollageLayout(position);
				}
				break;
			case R.id.fragment_collage_imageView_all_layout_ratio:
				setRatioCollageRatio(position);
				break;
			case R.id.fragment_collage_imageView_all_sticker:
				if (mOnCollageViewGroup != null) {
					ImageData item = mOptionAdapter.getItem(position);
					mOnCollageViewGroup.onAddCollageClipArt(new ImageData(item.getPath(), item.getImageId(), item.getSource(), IMAGE_TYPE_THUMBNAIL_MICRO));
				}
				break;
			case R.id.fragment_collage_imageView_single_filter:
				if (mOnCollageViewGroup != null) {
					new AsyntaskApplyEffect(NativeEffects.getEffectsList()[position]).execute(true);
				}
				break;
			}
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (getView() != null) {
			switch (mClickedViewId) {
			case R.id.fragment_collage_imageView_all_border:
				if (getView().findViewById(R.id.fragment_collage_seekBar_1).equals(seekBar)) {
					setCollageBorder(progress);
				} else {
					setFrameBorder(progress);
				}
				break;
			case R.id.fragment_collage_imageView_all_layout_ratio:
				int ratioX = mRatioX;
				int ratioY = mRatioY;
				if (getView().findViewById(R.id.fragment_collage_seekBar_1).equals(seekBar)) {
					ratioX = progress != 0 ? progress : 1;
				} else {
					ratioY = progress != 0 ? progress : 1;
				}
				setCollageDimensionRatio(getView(), ratioX, ratioY);
				break;
			default:
				break;
			}

		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	// =======================================================================================
	// PRIVATE METHODS
	// =======================================================================================

	/**
	 * Method to set the border of collage frame
	 * 
	 * @param progress
	 */
	private void setFrameBorder(int progress) {
		if (mOnCollageViewGroup != null) {
			mOnCollageViewGroup.onSetCollageItemBorder((float) progress / 100.00F);
		}
	}

	/**
	 * Method to set the border of collage
	 * 
	 * @param progress
	 */
	private void setCollageBorder(int progress) {
		if (mOnCollageViewGroup != null) {
			if (mOnCollageViewGroup != null) {
				mOnCollageViewGroup.onSetCollageBorder((float) progress / 100.00F);
			}
		}
	}

	/**
	 * Method to set the ratio of collage dimension
	 * 
	 * @param position
	 */
	private void setRatioCollageRatio(int position) {
		switch (position) {
		case 0:
			getView().findViewById(R.id.fragment_collage_layout_option_seek).setVisibility(View.VISIBLE);
			SeekBar seekBar1 = (SeekBar) getView().findViewById(R.id.fragment_collage_seekBar_1);
			SeekBar seekBar2 = (SeekBar) getView().findViewById(R.id.fragment_collage_seekBar_2);
			seekBar1.setMax(10);
			seekBar1.setProgress(mRatioX);
			seekBar2.setMax(10);
			seekBar2.setProgress(mRatioY);
			((TextView) getView().findViewById(R.id.fragment_collage_seekBar_1_textView)).setText("X : " + mRatioX);
			((TextView) getView().findViewById(R.id.fragment_collage_seekBar_2_textView)).setText("Y : " + mRatioY);
			break;
		case 1:
			setCollageDimensionRatio(getView(), 1, 1);
			break;
		case 2:
			setCollageDimensionRatio(getView(), 2, 3);
			break;
		case 3:
			setCollageDimensionRatio(getView(), 3, 2);
			break;
		case 4:
			setCollageDimensionRatio(getView(), 3, 4);
			break;
		case 5:
			setCollageDimensionRatio(getView(), 4, 3);
			break;
		case 6:
			setCollageDimensionRatio(getView(), 3, 5);
			break;
		case 7:
			setCollageDimensionRatio(getView(), 5, 3);
			break;
		case 8:
			setCollageDimensionRatio(getView(), 5, 7);
			break;
		case 9:
			setCollageDimensionRatio(getView(), 7, 5);
			break;
		case 10:
			setCollageDimensionRatio(getView(), 9, 16);
			break;
		case 11:
			setCollageDimensionRatio(getView(), 16, 9);
			break;
		default:
			setCollageDimensionRatio(getView(), 1, 1);
		}
	}

	/**
	 * Method to change the dimension of collage
	 * 
	 * @param parent
	 *            parent viewGroup
	 * @param ratioX
	 *            width ratio
	 * @param ratioY
	 *            width ratio
	 */
	private void setCollageDimensionRatio(View parent, int ratioX, int ratioY) {
		mRatioX = ratioX;
		mRatioY = ratioY;
		if (parent == null && getView() != null) {
			parent = getView();
		}
		((TextView) parent.findViewById(R.id.fragment_collage_seekBar_1_textView)).setText("X : " + mRatioX);
		((TextView) parent.findViewById(R.id.fragment_collage_seekBar_2_textView)).setText("Y : " + mRatioY);
		if (parent != null) {
			int height = (int) ((float) getActivity().getResources().getDisplayMetrics().heightPixels * MAX_HEIGHT);
			int width = (int) ((float) getActivity().getResources().getDisplayMetrics().widthPixels * MAX_WIDTH);
			LayoutParams layoutParams = parent.findViewById(R.id.fragment_collage_collage_view_group).getLayoutParams();
			LayoutParams layoutParamsBackground = parent.findViewById(R.id.fragment_collage_collage_view_group_background).getLayoutParams();
			if (mController.getCollageType() != COLLAGE_TYPE_SHAPE) {
				int tempWidth = width;
				int tempHeight = (int) ((float) width / (float) ratioX) * ratioY;
				if (tempHeight > height) {
					tempHeight = height;
					tempWidth = (int) ((float) height / (float) ratioY) * ratioX;
				}
				layoutParams.height = tempHeight;
				layoutParams.width = tempWidth;
				layoutParamsBackground.height = tempHeight;
				layoutParamsBackground.width = tempWidth;
			} else {
				layoutParams.height = layoutParams.width = layoutParamsBackground.height = layoutParamsBackground.width = (width < height) ? width : height;
			}
		}
		if (mOnCollageViewGroup != null) {
			mOnCollageViewGroup.onChangeCollageRatio();
		}
	}

	/**
	 * Method to set the adapter list for Effect apply on all image
	 */
	private void setListForApplyEffectOnAllBitmap() {
		LinkedList<ImageData> imageData = new LinkedList<ImageData>();
		int[] effectsList = NativeEffects.getEffectsList();
		for (int i = 0; i < effectsList.length; i++) {
			imageData.add(new ImageData("x.png", -1, SOURCE_APPLICATION, IMAGE_TYPE_THUMBNAIL_MICRO, effectsList[i]));
		}
		mOptionAdapter.setData(imageData);
	}

	/**
	 * Method to set the adapter list for Effect apply on single image
	 */
	private void setListForApplyEffectOnSingleBitmap() {
		LinkedList<ImageData> imageData = new LinkedList<ImageData>();
		ImageData selectedChildImageData = mOnCollageViewGroup.onGetSelectedChildData();
		int[] effectsList = NativeEffects.getEffectsList();
		if (selectedChildImageData instanceof ClipImageData) {
			ClipImageData clipImageData = (ClipImageData) selectedChildImageData;
			if (clipImageData.getClipPath() != null) {
				for (int i = 0; i < effectsList.length; i++) {
					imageData.add(new ClipImageData(clipImageData.getPath(), clipImageData.getImageId(), clipImageData.getSource(), IMAGE_TYPE_THUMBNAIL_MICRO, effectsList[i], clipImageData.getmLeft(), clipImageData.getmTop(), clipImageData.getmRight(), clipImageData.getmBottam(), clipImageData.getClipPath(), clipImageData.isHorizontalFlip(), clipImageData.isVerticalFlip()));
				}
			} else {
				for (int i = 0; i < effectsList.length; i++) {
					imageData.add(new ClipImageData(clipImageData.getPath(), clipImageData.getImageId(), clipImageData.getSource(), IMAGE_TYPE_THUMBNAIL_MICRO, effectsList[i], clipImageData.getmLeft(), clipImageData.getmTop(), clipImageData.getmRight(), clipImageData.getmBottam(), clipImageData.getClipImage(), clipImageData.isHorizontalFlip(), clipImageData.isVerticalFlip()));
				}
			}
		} else {
			for (int i = 0; i < effectsList.length; i++) {
				imageData.add(new ImageData(selectedChildImageData.getPath(), selectedChildImageData.getImageId(), selectedChildImageData.getSource(), IMAGE_TYPE_THUMBNAIL_MICRO, effectsList[i], selectedChildImageData.isHorizontalFlip(), selectedChildImageData.isVerticalFlip()));
			}
		}
		mOptionAdapter.setData(imageData);
	}

	/**
	 * Method to set the adapter list for layout
	 */
	private void setListForLayout() {
		try {
			String gridDir = ASSETS_GRID[mImageData.size() - 1];
			String[] list = getActivity().getAssets().list(gridDir);
			if (list != null && list.length > 0) {
				LinkedList<ImageData> imageData = new LinkedList<ImageData>();
				for (int i = 0; i < list.length; i++) {
					imageData.add(new ImageData(gridDir + "/" + list[i], -1, SOURCE_APPLICATION, IMAGE_TYPE_THUMBNAIL_MICRO));
				}
				mOptionAdapter.setData(imageData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the adapter list for Collage Background
	 */
	private void setListForBackground() {
		try {
			String[] list = getActivity().getAssets().list(ASSETS_BACKGROUNDS);
			if (list != null && list.length > 0) {
				LinkedList<ImageData> imageData = new LinkedList<ImageData>();
				for (int i = 0; i < list.length; i++) {
					imageData.add(new ImageData(ASSETS_BACKGROUNDS + "/" + list[i], -1, SOURCE_APPLICATION, IMAGE_TYPE_FULL_SIZE));
				}
				mOptionAdapter.setData(imageData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the adapter list for Collage FRAME
	 */
	private void setListForCollageFrame() {
		try {
			String gridDir = ASSETS_GRID[mImageData.size() - 1];
			String[] list = getActivity().getAssets().list(gridDir);
			if (list != null && list.length > 0) {
				LinkedList<ImageData> imageData = new LinkedList<ImageData>();
				for (int i = 0; i < list.length; i++) {
					imageData.add(new ImageData(gridDir + "/" + list[i], -1, SOURCE_APPLICATION, IMAGE_TYPE_THUMBNAIL_MICRO));
				}
				mOptionAdapter.setData(imageData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the adapter list for Collage RATIO
	 */
	private void setListForCollageRatio() {
		try {
			String[] list = getActivity().getAssets().list(ASSETS_COLLAGE_RATIO);
			if (list != null && list.length > 0) {
				LinkedList<ImageData> imageData = new LinkedList<ImageData>();
				for (int i = 0; i < list.length; i++) {
					imageData.add(new ImageData(ASSETS_COLLAGE_RATIO + "/" + list[i], -1, SOURCE_APPLICATION, IMAGE_TYPE_THUMBNAIL_MICRO));
				}
				mOptionAdapter.setData(imageData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the adapter list for Collage sticker
	 */
	private void setListForClipArts() {
		try {
			String[] list = getActivity().getAssets().list(ASSETS_CLIP_ARTS);
			if (list != null && list.length > 0) {
				LinkedList<ImageData> imageData = new LinkedList<ImageData>();
				for (int i = 0; i < list.length; i++) {
					imageData.add(new ImageData(ASSETS_CLIP_ARTS + "/" + list[i], Utils.getImageCount(getActivity()), SOURCE_APPLICATION, IMAGE_TYPE_THUMBNAIL_MICRO));
				}
				mOptionAdapter.setData(imageData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method show the option apply to all items of collage
	 */
	private void showOptionListForAllItems() {
		if (getView() != null) {
			((HorizontalScrollView) getView().findViewById(R.id.fragment_collage_horizontal_scroll)).scrollTo(0, 0);
			mOptionAdapter.setData(null);
			getView().findViewById(R.id.fragment_collage_layout_option_seek).setVisibility(View.INVISIBLE);
			getView().findViewById(R.id.fragment_collage_layout_option_singal).setVisibility(View.GONE);
			getView().findViewById(R.id.fragment_collage_layout_option_all).setVisibility(View.VISIBLE);
			// HIDE OPTION IN CASE OF SHAPE COLLAGE
			if (getView() != null) {
				if (mController.getCollageType() == COLLAGE_TYPE_SHAPE) {
					getView().findViewById(R.id.fragment_collage_imageView_all_layout).setVisibility(View.GONE);
					getView().findViewById(R.id.fragment_collage_imageView_all_layout_ratio).setVisibility(View.GONE);
					getView().findViewById(R.id.fragment_collage_imageView_all_frame_background).setVisibility(View.GONE);
					((TextView) getActivity().findViewById(R.id.headder_collage_textView_collageType)).setText(R.string.fragment_home_shape);
				} else if (mController.getCollageType() == COLLAGE_TYPE_FREE) {
					getView().findViewById(R.id.fragment_collage_imageView_all_layout).setVisibility(View.GONE);
					getView().findViewById(R.id.fragment_collage_imageView_all_layout_ratio).setVisibility(View.GONE);
					getView().findViewById(R.id.fragment_collage_imageView_all_border).setVisibility(View.GONE);
					((TextView) getActivity().findViewById(R.id.headder_collage_textView_collageType)).setText(R.string.fragment_home_free_style);
				} else {
					((TextView) getActivity().findViewById(R.id.headder_collage_textView_collageType)).setText(R.string.fragment_home_grid_style);
				}
			}
		}
	}

	/**
	 * Method show the option apply to singal items of collage
	 */
	private void showOptionListForSingalItems() {
		if (getView() != null) {
			((HorizontalScrollView) getView().findViewById(R.id.fragment_collage_horizontal_scroll)).scrollTo(0, 0);
			getView().findViewById(R.id.fragment_collage_layout_option_seek).setVisibility(View.INVISIBLE);
			getView().findViewById(R.id.fragment_collage_layout_option_all).setVisibility(View.GONE);
			getView().findViewById(R.id.fragment_collage_layout_option_singal).setVisibility(View.VISIBLE);
			mOptionAdapter.setData(null);
			ImageView imageViewLock = (ImageView) getView().findViewById(R.id.fragment_collage_imageView_single_lockView);
			if (mController.getCollageType() == COLLAGE_TYPE_SHAPE) {
				getView().findViewById(R.id.fragment_collage_imageView_single_delete).setVisibility(View.GONE);
				imageViewLock.setVisibility(View.GONE);
			}
			if (mController.getCollageType() == COLLAGE_TYPE_FREE) {
				imageViewLock.setVisibility(View.VISIBLE);
				if (mOnCollageViewGroup != null && mOnCollageViewGroup.onIsViewLocked()) {
					imageViewLock.setImageBitmap(bitmapLockView);
				} else {
					imageViewLock.setImageBitmap(bitmapUnlockView);
				}
			} else {
				imageViewLock.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * Method to set the list for clip image
	 */
	private void setClipImageList() {
		try {
			String[] list = getActivity().getAssets().list(ASSETS_SHAPE);
			if (list != null && list.length > 0) {
				LinkedList<ImageData> imageData = new LinkedList<ImageData>();
				for (int i = 0; i < list.length; i++) {
					imageData.add(new ImageData(ASSETS_SHAPE + "/" + list[i], -1, SOURCE_APPLICATION, IMAGE_TYPE_THUMBNAIL_MICRO));
				}
				mOptionAdapterClipShape.setData(imageData);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to set the dimension of views
	 * 
	 * @param parentView
	 *            ViewGroup
	 */
	private void setDimension(View parentView) {
		// SET THE DIMENSION OF HORIZONTAL GRID
		int padding = (int) ((float) mOptionDimension * 0.1F);
		TwoWayGridView twoWayGridView = (TwoWayGridView) parentView.findViewById(R.id.fragment_collage_gridView_selecete_pic);
		twoWayGridView.getLayoutParams().height = mOptionDimension;
		twoWayGridView.setPadding(padding, padding, padding, padding);
		twoWayGridView.setHorizontalSpacing(3 * padding);
		twoWayGridView.setAdapter(mOptionAdapter);
		twoWayGridView.setOnItemClickListener(this);
		parentView.findViewById(R.id.fragment_collage_layout_option_seek).getLayoutParams().height = mOptionDimension;
		// SET HORIZONTAL GRID VIEW
		twoWayGridView = (TwoWayGridView) parentView.findViewById(R.id.fragment_collage_dialog_clip_twoWayGridView);
		twoWayGridView.getLayoutParams().height = mOptionDimension;
		twoWayGridView.setPadding(0, padding, 0, padding);
		twoWayGridView.setHorizontalSpacing(3 * padding);
		twoWayGridView.setAdapter(mOptionAdapterClipShape);
		twoWayGridView.setOnItemClickListener(this);
		// SET THE DIMENSION OF COLLAGE VIEW GROUP & OPTIONS
		padding = (int) ((float) mOptionDimension * 0.2F);
		parentView.findViewById(R.id.fragment_collage_frameLayout_option).getLayoutParams().height = mOptionDimension;
		LayoutParams layoutParams = null;
		ViewGroup viewGroup = (ViewGroup) parentView.findViewById(R.id.fragment_collage_layout_option_all);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			View childAt = viewGroup.getChildAt(i);
			layoutParams = childAt.getLayoutParams();
			layoutParams.height = mOptionDimension;
			layoutParams.width = (int) (1.5F * (float) mOptionDimension);
			childAt.setPadding(padding, padding, padding, padding);
		}
		viewGroup = (ViewGroup) parentView.findViewById(R.id.fragment_collage_layout_option_singal);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			View childAt = viewGroup.getChildAt(i);
			layoutParams = childAt.getLayoutParams();
			layoutParams.height = mOptionDimension;
			layoutParams.width = (int) (1.5F * (float) mOptionDimension);
			childAt.setPadding(padding, padding, padding, padding);
		}
		// INITILIZE CROP VIEW GROUP DIMENSION
		View cropViewGroup = parentView.findViewById(R.id.fragment_collage_dialog_clip_clipViewGroup);
		if (cropViewGroup instanceof ClipViewGroup) {
			mClipViewGroup = (ClipViewGroup) cropViewGroup;
		}
		cropViewGroup.getLayoutParams().width = (int) ((float) getResources().getDisplayMetrics().widthPixels * 0.8F);
		// INITILIZE DONE BUTTON DIMENSION
		View viewDone = parentView.findViewById(R.id.fragment_collage_dialog_clip_done);
		layoutParams = viewDone.getLayoutParams();
		layoutParams.width = layoutParams.height = mOptionDimension;
		viewDone.setPadding(padding, padding, padding, padding);
	}

	/**
	 * @author THE-MKR Adapter for showing the sub option
	 */
	private class OptionAdapter extends BaseAdapter {
		private LinkedList<ImageData> mImageData;

		public OptionAdapter() {
			mImageData = new LinkedList<ImageData>();
		}

		/**
		 * Method to set new data
		 * 
		 * @param imageData
		 */
		public void setData(LinkedList<ImageData> imageData) {
			mImageData.clear();
			if (imageData != null) {
				mImageData.addAll(imageData);
			}
			notifyDataSetChanged();
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
				layoutParams.width = layoutParams.height = (int) ((float) mOptionDimension * 0.8F);
			}
			((ThumbImageView) convertView.findViewById(R.id.grid_view_thumb_pic)).setImageData(getItem(position));
			return convertView;
		}
	}

	/**
	 * Assyntask to apply effect on Collage Image Item
	 * 
	 * @author THE-MKR
	 */
	private class AsyntaskApplyEffect extends AsyncTask<Boolean, Void, Void> {
		private ProgressDialog mProgressDialog;
		private int mEffect;

		public AsyntaskApplyEffect(int effect) {
			mEffect = effect;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			try {
				mProgressDialog = new ProgressDialog(getActivity());
				mProgressDialog.setCancelable(false);
				mProgressDialog.setTitle("Plz wait...");
				mProgressDialog.setMessage("Apply effect on item");
				mProgressDialog.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected Void doInBackground(Boolean... params) {
			if (mOnCollageViewGroup != null) {
				mOnCollageViewGroup.onApplyEffect(mEffect, params[0]);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @author THE-MKR Asyntask to save bitmap in Sd-Card
	 * 
	 */
	private class AsyntaskSaveCollageBitmap extends AsyncTask<Boolean, Void, Void> {
		private ProgressDialog mProgressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			try {
				mProgressDialog = new ProgressDialog(getActivity());
				mProgressDialog.setCancelable(false);
				mProgressDialog.setTitle("Plz wait...");
				mProgressDialog.setMessage("Creating collage... ");
				mProgressDialog.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected Void doInBackground(Boolean... params) {
			try {
				if (mOnCollageViewGroup != null && getView() != null) {
					int maxDimension = 1000;
					int height = maxDimension, width = maxDimension;
					ViewGroup collageView = (ViewGroup) mOnCollageViewGroup;
					if (collageView.getWidth() >= collageView.getHeight()) {
						height = (int) ((float) (maxDimension * collageView.getHeight()) / (float) collageView.getWidth());
					} else {
						width = (int) ((float) (maxDimension * collageView.getWidth()) / (float) collageView.getHeight());
					}
					Bitmap bitmapCollage = Bitmap.createBitmap(width, height, Config.ARGB_8888);
					Canvas canvas = new Canvas(bitmapCollage);
					((CollageBackgroundView) getView().findViewById(R.id.fragment_collage_collage_view_group_background)).onDrawBackground(canvas, width, height);
					Bitmap onGetCollageBitmap = mOnCollageViewGroup.onGetCollageBitmap(width, height);
					canvas.drawBitmap(onGetCollageBitmap, ((View) mOnCollageViewGroup).getMatrix(), new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
					onGetCollageBitmap.recycle();
					saveImageInSdcard(bitmapCollage, params[0]);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Method to save image into sd-card
		 * 
		 * @param collageBitmap
		 *            Bitmap to be share or save
		 * @param isShareImage
		 *            if TRUE share bitmap , FALSE save the image
		 * 
		 */
		private void saveImageInSdcard(Bitmap collageBitmap, boolean isShareImage) {
			try {
				if (collageBitmap != null && !collageBitmap.isRecycled() && getActivity() != null) {
					File externalDirectory = new File(EXTERNAL_DRIVE_PATH);
					externalDirectory.delete();
					if (!externalDirectory.exists()) {
						externalDirectory.mkdir();
					}
					File file = new File(EXTERNAL_DRIVE_PATH, "Collage " + Utils.getImageCount(getActivity()) + ".jpeg");
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
					collageBitmap.compress(CompressFormat.JPEG, 100, bos);
					bos.flush();
					bos.close();
					try {
						Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
						Uri contentUri = Uri.fromFile(file);
						mediaScanIntent.setData(contentUri);
						getActivity().sendBroadcast(mediaScanIntent);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (isShareImage) {
						Intent share = new Intent(Intent.ACTION_SEND);
						share.setType("image/*");
						share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
						Intent sendIntent = Intent.createChooser(share, "Share File");
						sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(sendIntent);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (collageBitmap != null) {
					collageBitmap.recycle();
				}
			}
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
				if (mBitmapDrawableActivityBackground == null || mBitmapDrawableActivityBackground.getBitmap() == null || mBitmapDrawableActivityBackground.getBitmap().isRecycled()) {
					Options options = new Options();
					options.inJustDecodeBounds = true;
					options.inPreferredConfig = Config.ARGB_8888;
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
					getView().findViewById(R.id.fragment_collage_parent).setBackgroundDrawable(mBitmapDrawableActivityBackground);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author THE-MKR Listener to notify events to parent activity
	 */
	public interface OnCollageFragmentListener {

		/**
		 * Method to notify that user want to add new pic in collage
		 */
		public void onFragmentCollageAddNewPic();

		/**
		 * Method to notify that user want to add add new shape
		 */
		public void onFragmentCollageAddNewShape();

		/**
		 * Method to notify that user want to add add new shape
		 */
		public void onFragmentCollageOnShowHomeScreen();

	}

}
