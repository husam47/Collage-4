package com.adroitstudio.photocollage.view;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

import com.adroitstudio.photocollage.AppPromotionMKR;
import com.adroitstudio.photocollage.R;
import com.adroitstudio.photocollage.Utils;

public class HomeFragment extends Fragment implements OnClickListener, AnimationListener {

	private BitmapDrawable mBitmapDrawableActivityBackground;
	private ScaleAnimation mScaleAnimation;
	private AnimationSet mAnimationSet;

	public HomeFragment() {
		mScaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
		mScaleAnimation.setDuration(250);
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setAnimationListener(this);
		mAnimationSet = new AnimationSet(false);
		mAnimationSet.addAnimation(new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F));
		mAnimationSet.addAnimation(new AlphaAnimation(0, 1));
		mAnimationSet.setFillAfter(true);
		mAnimationSet.setDuration(250);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().getActionBar().hide();
		View parentView = inflater.inflate(R.layout.fragment_home, container, false);
		// SET CLICK LISTENER
		parentView.findViewById(R.id.fragment_home_layout_grid).setOnClickListener(this);
		parentView.findViewById(R.id.fragment_home_layout_free).setOnClickListener(this);
		parentView.findViewById(R.id.fragment_home_layout_shape).setOnClickListener(this);
		parentView.findViewById(R.id.fragment_home_textView_more_apps).setOnClickListener(this);
		parentView.findViewById(R.id.fragment_home_textView_rate_us).setOnClickListener(this);
		parentView.findViewById(R.id.fragment_home_textView_start).setOnClickListener(this);
		// INVISIBLE STYLE VIEW
		parentView.findViewById(R.id.fragment_home_imageView_free).setVisibility(View.INVISIBLE);
		parentView.findViewById(R.id.fragment_home_imageView_grid).setVisibility(View.INVISIBLE);
		parentView.findViewById(R.id.fragment_home_imageView_shape).setVisibility(View.INVISIBLE);
		parentView.findViewById(R.id.fragment_home_textView_free).setVisibility(View.INVISIBLE);
		parentView.findViewById(R.id.fragment_home_textView_grid).setVisibility(View.INVISIBLE);
		parentView.findViewById(R.id.fragment_home_textView_shape).setVisibility(View.INVISIBLE);
		// SET BACKGROUND
		if (mBitmapDrawableActivityBackground != null && mBitmapDrawableActivityBackground.getBitmap() != null) {
			mBitmapDrawableActivityBackground.getBitmap().recycle();
		}
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), R.drawable.background_home, options);
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		options.inSampleSize = Utils.calculateInSampleSize(options.outWidth, options.outHeight, displayMetrics.widthPixels, displayMetrics.heightPixels);
		options.inJustDecodeBounds = false;
		mBitmapDrawableActivityBackground = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.background_home, options));
		parentView.findViewById(R.id.fragment_home_parent).setBackgroundDrawable(mBitmapDrawableActivityBackground);
		return parentView;
	}

	@Override
	public void onDestroy() {
		if (mBitmapDrawableActivityBackground != null && mBitmapDrawableActivityBackground.getBitmap() != null) {
			mBitmapDrawableActivityBackground.getBitmap().recycle();
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.fragment_home_layout_grid:
			if (getActivity() instanceof OnHomeFragmentListener && getView() != null) {
				((OnHomeFragmentListener) getActivity()).onHomeFragmentGridStyleSelected();
			}
			break;
		case R.id.fragment_home_layout_free:
			if (getActivity() instanceof OnHomeFragmentListener && getView() != null) {
				((OnHomeFragmentListener) getActivity()).onHomeFragmentFreeStyleSelected();
			}
			break;
		case R.id.fragment_home_layout_shape:
			if (getActivity() instanceof OnHomeFragmentListener && getView() != null) {
				((OnHomeFragmentListener) getActivity()).onHomeFragmentShapeStyleSelected();
			}
			break;
		case R.id.fragment_home_textView_more_apps:
			AppPromotionMKR.getMoreApps(getActivity(), "Adroit Studio");
			break;
		case R.id.fragment_home_textView_rate_us:
			AppPromotionMKR.sendReview(getActivity());
			break;
		case R.id.fragment_home_textView_start:
			if (getView() != null && getView().findViewById(R.id.fragment_home_imageView_grid).getVisibility() != View.VISIBLE) {
				View gridImageView = getView().findViewById(R.id.fragment_home_imageView_grid);
				gridImageView.setVisibility(View.VISIBLE);
				gridImageView.startAnimation(mScaleAnimation);
			}
			break;
		}
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (getView() != null) {
			if (getView().findViewById(R.id.fragment_home_imageView_shape).getVisibility() == View.VISIBLE) {
				getView().findViewById(R.id.fragment_home_imageView_shape).clearAnimation();
				getView().findViewById(R.id.fragment_home_textView_free).clearAnimation();
				View freeTextView = getView().findViewById(R.id.fragment_home_textView_shape);
				freeTextView.setVisibility(View.VISIBLE);
				freeTextView.startAnimation(mAnimationSet);
			} else if (getView().findViewById(R.id.fragment_home_imageView_free).getVisibility() == View.VISIBLE) {
				getView().findViewById(R.id.fragment_home_imageView_free).clearAnimation();
				getView().findViewById(R.id.fragment_home_textView_grid).clearAnimation();

				View freeTextView = getView().findViewById(R.id.fragment_home_textView_free);
				freeTextView.setVisibility(View.VISIBLE);
				freeTextView.startAnimation(mAnimationSet);

				View shapeImageView = getView().findViewById(R.id.fragment_home_imageView_shape);
				shapeImageView.setVisibility(View.VISIBLE);
				shapeImageView.startAnimation(mScaleAnimation);

			} else if (getView().findViewById(R.id.fragment_home_imageView_grid).getVisibility() == View.VISIBLE) {
				getView().findViewById(R.id.fragment_home_imageView_grid).clearAnimation();

				View gridTextView = getView().findViewById(R.id.fragment_home_textView_grid);
				gridTextView.setVisibility(View.VISIBLE);
				gridTextView.startAnimation(mAnimationSet);

				View freeImageView = getView().findViewById(R.id.fragment_home_imageView_free);
				freeImageView.setVisibility(View.VISIBLE);
				freeImageView.startAnimation(mScaleAnimation);

			} else {
				getView().findViewById(R.id.fragment_home_textView_shape).clearAnimation();
			}
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	/**
	 * @author THE-MKR Parent activity must implement this interface to listen
	 *         the events occur in the HomeFragment
	 */
	public interface OnHomeFragmentListener {

		/**
		 * Method to notify that user want to create Grid Style Collage
		 */
		public void onHomeFragmentGridStyleSelected();

		/**
		 * Method to notify that user want to create Free Style Collage
		 */
		public void onHomeFragmentFreeStyleSelected();

		/**
		 * Method to notify that user want to create Shape Style Collage
		 */
		public void onHomeFragmentShapeStyleSelected();
	}

}
