package com.themkrworld.collage.cache;


import com.themkrworld.collage.effect.MKREffect;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Constants;

public class ImageData implements Constants {
    private static final String TAG = AppConfig.BASE_TAG + ".ImageData";
    private String mKey;
    private ImageSource mSource = ImageSource.DEVICE_STORAGE;
    private ImageOrientation mOrientation = ImageOrientation.NONE;
    private MKREffect.Effect mEffect = MKREffect.Effect.NONE;
    private ImageType mType = ImageType.ORIGINAL_SIZE;

    /**
     * Constructor Complete
     *
     * @param key           Path / URL / ID / PrimaryKey
     * @param type          Type of Image we fetched
     * @param source        Source of the Image, Frome where this Image is fetched
     * @param orientation   Orientation of the image
     * @param effect        Effect Apply on the Image
     */
    public ImageData(String key, ImageType type, ImageSource source, ImageOrientation orientation, MKREffect.Effect effect) {
        mKey = key;
        mType = type;
        mSource = source;
        if ((mOrientation = orientation) == null) {
            mOrientation = ImageOrientation.NONE;
        }
        if ((mEffect = effect) == null) {
            mEffect = MKREffect.Effect.NONE;
        }
    }

    /**
     * Constructor Complete
     *
     * @param key    Path / URL / ID / PrimaryKey
     * @param type   Type of Image we fetched
     * @param source Source of the Image, Frome where this Image is fetched
     */
    public ImageData(String key, ImageType type, ImageSource source) {
        this(key, type, source, null, null);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof ImageData) {
            ImageData imageData = (ImageData) o;
            // CHECK FOR IMAGE KEY
            if (!mKey.equals(imageData.mKey)) {
                return false;
            }
            // CHECK FOR IMAGE SOURCE
            if (!mSource.equals(imageData.mSource)) {
                return false;
            }
            // CHECK FOR IMAGE TYPE
            if (!mType.equals(imageData.mType)) {
                return false;
            }
            // CHECK FOR IMAGE ORIENTATION
            if (!mOrientation.equals(imageData.mOrientation)) {
                return false;
            }
            // CHECK FOR IMAGE EFFECT
            if (!mEffect.equals(imageData.mEffect)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return mKey.hashCode();
    }

    // ====================================================================================================
    // GETTERS
    // ====================================================================================================

    /**
     * Method to get the Effect Applied this image
     *
     * @return
     */
    public MKREffect.Effect getEffect() {
        return mEffect;
    }

    /**
     * Method to ge the Orientation Applied on this Image
     *
     * @return
     */
    public ImageOrientation getOrientation() {
        return mOrientation;
    }

    /**
     * Method to get the source of the Image
     *
     * @return
     */
    public ImageSource getSource() {
        return mSource;
    }

    /**
     * Method to get the Type of the Image
     *
     * @return
     */
    public ImageType getType() {
        return mType;
    }

    /**
     * Method ti get the Key of the Image
     *
     * @return
     */
    public String getKey() {
        return mKey;
    }

    //==========================================================================================================
    //==========================================================================================================
    //==========================================================================================================
    //==========================================================================================================

    /**
     * Enum to hold the Flip constant
     */
    public enum ImageOrientation {
        NONE, VERTICAL_FLIP, HORIZONTAL_FLIP, ALL_FLIP
    }

    /**
     * Enum to hold the source location of a Image
     */
    public enum ImageSource {
        ASSETS, DEVICE_STORAGE
    }

    /**
     * Enum to hold the Type of Image ve loaded in Cache
     */
    public enum ImageType {
        ORIGINAL_SIZE, THUMBNIAL, COLLAGE
    }
}
