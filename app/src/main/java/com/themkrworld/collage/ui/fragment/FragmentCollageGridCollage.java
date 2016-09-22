package com.themkrworld.collage.ui.fragment;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.themkrworld.collage.R;
import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.model.Gallery;
import com.themkrworld.collage.ui.dialog.DialogGallery;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Constants;
import com.themkrworld.collage.utils.Tracer;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by delhivery on 21/9/16.
 */
public class FragmentCollageGridCollage extends Fragment implements DialogGallery.OnDialogGalleryListener {
    public static String EXTRA_THUMB_NAME = "EXTRA_THUMB_NAME";
    public static String EXTRA_NUMBER_OF_PIC = "EXTRA_NUMBER_OF_PIC";
    private static final String TAG = AppConfig.BASE_TAG + ".FragmentCollageGridCollage";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Tracer.debug(TAG, "onCreateView()");
        return inflater.inflate(R.layout.fragment_collage_grid_collage, container, false);
    }

    @Override
    public void onViewCreated(View parentView, Bundle savedInstanceState) {
        super.onViewCreated(parentView, savedInstanceState);
        Tracer.debug(TAG, "onViewCreated()");
        if (getArguments() != null) {
            if (getArguments().getInt(EXTRA_NUMBER_OF_PIC, -1) > 0 && getActivity() instanceof OnFragmentCollageGridCollage) {
                new DialogGallery(getActivity(), ((OnFragmentCollageGridCollage) getActivity()).onFragmentCollageGridCollageGetGallery(), getArguments().getInt(EXTRA_NUMBER_OF_PIC, -1), this).show();
            }
        }
    }

    @Override
    public void onDialogGalleryImageSelected(Vector<ImageData> imageDataList) {
        Tracer.debug(TAG, "onDialogGalleryImageSelected() " + imageDataList.size());
        if (getArguments() != null) {
//            Vector<ImageData> collageTemplateList = getCollageTemplateList(getArguments().getString(EXTRA_THUMB_NAME, "").trim(), getArguments().getInt(EXTRA_NUMBER_OF_PIC, -1));
        }
    }

    /**
     * Method to get the List of all the template for this collage
     *
     * @param collageThumbName
     * @param numberOfTemplate
     * @return
     */
    private Vector<ImageData> getCollageTemplateList(String collageThumbName, int numberOfTemplate) {
        Tracer.debug(TAG, "getCollageTemplateList()");
        Vector<ImageData> templateImageList = new Vector<>();
        if (!collageThumbName.trim().isEmpty() && numberOfTemplate > 0) {
            try {
                AssetManager assetManager = getActivity().getAssets();
                String baseDirPath = Constants.ASSETS_PATH.ASSETS_TEMPLATE_PATH + "/" + numberOfTemplate;
                String[] temlateFileNameList = assetManager.list(baseDirPath);
                String templatePre = collageThumbName.replace(Constants.ASSETS_FILE_PRE_TAG.ASSETS_THUMB_GRID, Constants.ASSETS_FILE_PRE_TAG.ASSETS_TEMPLATE_GRID);
                templatePre = templatePre.replace(".png", Constants.ASSETS_FILE_PRE_TAG.ASSETS_JOINER).trim();
                Tracer.debug(TAG, "getCollageTemplateList()" + templatePre);
                for (String templateFileName : temlateFileNameList) {
                    if (templateFileName.toLowerCase().startsWith(templatePre)) {
                        String imagePath = baseDirPath + "/" + templateFileName;
                        ImageData imageData = new ImageData(imagePath, ImageData.ImageType.COLLAGE, ImageData.ImageSource.ASSETS);
                        templateImageList.add(imageData);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return templateImageList;
    }

    /**
     * Callback to listen the event occur inside this Fragment
     */
    public interface OnFragmentCollageGridCollage {
        /**
         * Method to return the Gallery
         *
         * @return
         */
        public Gallery onFragmentCollageGridCollageGetGallery();
    }
}
