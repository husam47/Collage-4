package com.themkrworld.collage.model;

import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

/**
 * Created by delhivery on 21/9/16.
 */
public class CollageOptionInfo {
    private static final String TAG = AppConfig.BASE_TAG + ".CollageOptionInfo";
    private String mImageName;
    private int mPicCount;
    private ImageData mImageData;

    /**
     * Constructor
     *
     * @param imageName Name of the Pic
     * @param picCount  Number of image required for this collage
     * @param imageData Image data of the collage
     */
    public CollageOptionInfo(String imageName, int picCount, ImageData imageData) {
        Tracer.debug(TAG, "CollageOptionInfo()" + imageData.getKey() + "  " + picCount);
        mImageName = imageName;
        mPicCount = picCount;
        mImageData = imageData;
    }

    /**
     * Method to get the Pic count for to create this collage
     *
     * @return
     */
    public int getPicCount() {
        return mPicCount;
    }

    /**
     * Method to get the name of this thumb image
     *
     * @return
     */
    public String getImageName() {
        return mImageName;
    }

    /**
     * Method to get the ImageData of thumb
     *
     * @return
     */
    public ImageData getImageData() {
        return mImageData;
    }
}
