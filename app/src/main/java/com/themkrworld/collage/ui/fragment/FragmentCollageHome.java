package com.themkrworld.collage.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.themkrworld.collage.R;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

/**
 * Created by delhivery on 13/9/16.
 */
public class FragmentCollageHome extends Fragment implements View.OnClickListener {
    private static final String TAG = AppConfig.BASE_TAG + ".FragmentCollageHome";
    private static final int ANIMATION_DURATION = 500;
    private Animation mAnimationAppPicScale, mAnimationOptionAlpha;

    /**
     * Constructor
     */
    public FragmentCollageHome() {
        mAnimationAppPicScale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        mAnimationAppPicScale.setDuration(ANIMATION_DURATION);
        mAnimationAppPicScale.setFillAfter(true);
        mAnimationOptionAlpha = new AlphaAnimation(0, 1);
        mAnimationOptionAlpha.setStartOffset(ANIMATION_DURATION);
        mAnimationOptionAlpha.setDuration(ANIMATION_DURATION);
        mAnimationOptionAlpha.setFillAfter(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Tracer.debug(TAG, "onCreateView()");
        return inflater.inflate(R.layout.fragment_collage_home, container, false);
    }

    @Override
    public void onViewCreated(View parentView, Bundle savedInstanceState) {
        super.onViewCreated(parentView, savedInstanceState);
        Tracer.debug(TAG, "onViewCreated()");
        // START ANIMATION
        startAnimation(parentView);
        parentView.findViewById(R.id.fragment_collage_home_layout_option_frame_style).setOnClickListener(this);
        parentView.findViewById(R.id.fragment_collage_home_layout_option_free_style).setOnClickListener(this);
        parentView.findViewById(R.id.fragment_collage_home_layout_option_grid_style).setOnClickListener(this);
        parentView.findViewById(R.id.fragment_collage_home_layout_option_shape_style).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Tracer.debug(TAG, "onClick()" + view);
        switch (view.getId()) {
            case R.id.fragment_collage_home_layout_option_frame_style:
                if (getActivity() instanceof OnFragmentCollageHomeListener) {
                    ((OnFragmentCollageHomeListener) getActivity()).onFragmentCollageHomeShowFrameFragment();
                }
                break;
            case R.id.fragment_collage_home_layout_option_free_style:
                if (getActivity() instanceof OnFragmentCollageHomeListener) {
                    ((OnFragmentCollageHomeListener) getActivity()).onFragmentCollageHomeShowFreeStyleFragment();
                }
                break;
            case R.id.fragment_collage_home_layout_option_grid_style:
                if (getActivity() instanceof OnFragmentCollageHomeListener) {
                    ((OnFragmentCollageHomeListener) getActivity()).onFragmentCollageHomeShowGridFragment();
                }
                break;
            case R.id.fragment_collage_home_layout_option_shape_style:
                if (getActivity() instanceof OnFragmentCollageHomeListener) {
                    ((OnFragmentCollageHomeListener) getActivity()).onFragmentCollageHomeShowShapeFragment();
                }
                break;
        }
    }

    /**
     * Method to start the Animation on this screen
     *
     * @param parentView
     */
    private void startAnimation(View parentView) {
        Tracer.debug(TAG, "startAnimation()");
        parentView.findViewById(R.id.fragment_collage_home_image_view_app_pic).startAnimation(mAnimationAppPicScale);
        parentView.findViewById(R.id.fragment_collage_home_layout_option_frame_style).startAnimation(mAnimationOptionAlpha);
        parentView.findViewById(R.id.fragment_collage_home_layout_option_free_style).startAnimation(mAnimationOptionAlpha);
        parentView.findViewById(R.id.fragment_collage_home_layout_option_grid_style).startAnimation(mAnimationOptionAlpha);
        parentView.findViewById(R.id.fragment_collage_home_layout_option_shape_style).startAnimation(mAnimationOptionAlpha);
    }

    /**
     * Callback to notify the event and selection occur by the user in this fragment
     */
    public interface OnFragmentCollageHomeListener {
        /**
         * Method to notify that user select the Grid-Collage option
         */
        public void onFragmentCollageHomeShowGridFragment();

        /**
         * Method to notify that user select the Free-Style-Collage option
         */
        public void onFragmentCollageHomeShowFreeStyleFragment();

        /**
         * Method to notify that user select the Shape-Collage option
         */
        public void onFragmentCollageHomeShowShapeFragment();

        /**
         * Method to notify that user select the Frame-Collage option
         */
        public void onFragmentCollageHomeShowFrameFragment();
    }
}
