package com.themkrworld.collage.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.themkrworld.collage.R;
import com.themkrworld.collage.controller.GalleryController;
import com.themkrworld.collage.interfaces.OnBackPressedListener;
import com.themkrworld.collage.model.CollageOptionInfo;
import com.themkrworld.collage.model.Gallery;
import com.themkrworld.collage.ui.dialog.DialogGallery;
import com.themkrworld.collage.ui.fragment.FragmentCollageFrameList;
import com.themkrworld.collage.ui.fragment.FragmentCollageGridList;
import com.themkrworld.collage.ui.fragment.FragmentCollageHome;
import com.themkrworld.collage.ui.fragment.FragmentCollageShapeList;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

public class MainActivity extends Activity implements GalleryController.OnGalleryControllerListener, FragmentCollageHome.OnFragmentCollageHomeListener, FragmentCollageFrameList.OnFragmentCollageFrameListListener, FragmentCollageGridList.OnFragmentCollageGridListListener, FragmentCollageShapeList.OnFragmentCollageShapeListListener {
    private static final String TAG = AppConfig.BASE_TAG + ".MainActivity";
    private Gallery mGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GalleryController(this, this).initialieGallery();
    }

    @Override
    public void onBackPressed() {
        try {
            Fragment fragment = getFragmentManager().findFragmentById(R.id.activity_collage_container);
            if ((fragment != null) && (fragment instanceof OnBackPressedListener) && ((OnBackPressedListener) fragment).onBackPressed()) {
                return;
            }
        } catch (Exception e) {
            Tracer.error(TAG, "onBackPressed()" + e.getMessage());
        }
        super.onBackPressed();
    }

    @Override
    public void onGalleryControllerGalleryInitialized(Gallery gallery) {
        Tracer.debug(TAG, "onGalleryControllerGalleryInitialized()");
        mGallery = gallery;
        showHomeFragment();
    }

    //============================================================================================================================
    //FRAGMENT COLLAGE HOME ======================================================================================================
    //============================================================================================================================

    @Override
    public void onFragmentCollageHomeShowGridFragment() {
        Tracer.debug(TAG, "onFragmentCollageHomeShowGridFragment()");
        showCollageGridListFragment();
    }

    @Override
    public void onFragmentCollageHomeShowFreeStyleFragment() {
        Tracer.debug(TAG, "onFragmentCollageHomeShowFreeStyleFragment()");
    }

    @Override
    public void onFragmentCollageHomeShowShapeFragment() {
        Tracer.debug(TAG, "onFragmentCollageHomeShowShapeFragment()");
        showCollageShapeListFragment();
    }

    @Override
    public void onFragmentCollageHomeShowFrameFragment() {
        Tracer.debug(TAG, "onFragmentCollageHomeShowFrameFragment()");
        showCollageFrameListFragment();
    }

    //============================================================================================================================
    //FRAGMENT COLLAGE FRAME LIST=================================================================================================
    //============================================================================================================================

    @Override
    public void onFragmentCollageFrameListShowHomeFragment() {
        Tracer.debug(TAG, "onFragmentCollageFrameListShowHomeFragment()");
        showHomeFragment();
    }

    @Override
    public void onFragmentCollageFrameListShowFrameCollageFragment(CollageOptionInfo collageOptionInfo) {
        Tracer.debug(TAG, "onFragmentCollageFrameListShowFrameCollageFragment()");
    }

    //============================================================================================================================
    //FRAGMENT COLLAGE GRID LIST==================================================================================================
    //============================================================================================================================

    @Override
    public void onFragmentCollageGridListShowHomeFragment() {
        Tracer.debug(TAG, "onFragmentCollageGridListShowHomeFragment()");
        showHomeFragment();
    }

    @Override
    public void onFragmentCollageGridListShowGridCollageFragment(CollageOptionInfo collageOptionInfo) {
        Tracer.debug(TAG, "onFragmentCollageGridListShowGridCollageFragment()");
    }

    //============================================================================================================================
    //FRAGMENT COLLAGE SHAPE LIST=================================================================================================
    //============================================================================================================================

    @Override
    public void onFragmentCollageShapeListShowHomeFragment() {
        Tracer.debug(TAG, "onFragmentCollageShapeListShowHomeFragment()");
        showHomeFragment();
    }

    @Override
    public void onFragmentCollageShapeListShowShapeCollageFragment(CollageOptionInfo collageOptionInfo) {
        Tracer.debug(TAG, "onFragmentCollageShapeListShowShapeCollageFragment()");
    }

    //============================================================================================================================
    //============================================================================================================================
    //============================================================================================================================
    //============================================================================================================================
    //============================================================================================================================
    //============================================================================================================================

    /**
     * Method to show the home fragment
     */
    private void showHomeFragment() {
        Tracer.debug(TAG, "showHomeFragment()");
        getFragmentManager().beginTransaction().replace(R.id.activity_collage_container, new FragmentCollageHome()).commit();
    }

    /**
     * Method to show the grid list fragment
     */
    private void showCollageGridListFragment() {
        Tracer.debug(TAG, "showCollageGridListFragment()");
        getFragmentManager().beginTransaction().replace(R.id.activity_collage_container, new FragmentCollageGridList()).commit();
    }

    /**
     * Method to show the grid list fragment
     */
    private void showCollageShapeListFragment() {
        Tracer.debug(TAG, "showCollageShapeListFragment()");
        getFragmentManager().beginTransaction().replace(R.id.activity_collage_container, new FragmentCollageShapeList()).commit();
    }

    /**
     * Method to show the frame list fragment
     */
    private void showCollageFrameListFragment() {
        Tracer.debug(TAG, "showCollageFrameListFragment()");
        getFragmentManager().beginTransaction().replace(R.id.activity_collage_container, new FragmentCollageFrameList()).commit();
    }

    /**
     * Method to show the Gallery Dialog
     *
     * @param onDialogGalleryListener Callback to listen the Event occur inside gallery view
     * @param numberOfPicSelected     Number of pic selected from gallery
     */
    private void showDialogGallery(DialogGallery.OnDialogGalleryListener onDialogGalleryListener, int numberOfPicSelected) {
        Tracer.debug(TAG, "showDialogGallery()");
        new DialogGallery(this, mGallery, numberOfPicSelected).show();
    }

    /**
     * Method to show the Gallery Dialog
     *
     * @param onDialogGalleryListener Callback to listen the Event occur inside gallery view
     */
    private void showDialogGallery(DialogGallery.OnDialogGalleryListener onDialogGalleryListener) {
        Tracer.debug(TAG, "showDialogGallery()");
        new DialogGallery(this, mGallery).show();
    }
}
