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
    private CropImageData mCropImageData;

    /**
     * Constructor Complete
     *
     * @param key           Path / URL / ID / PrimaryKey
     * @param type          Type of Image we fetched
     * @param source        Source of the Image, Frome where this Image is fetched
     * @param orientation   Orientation of the image
     * @param effect        Effect Apply on the Image
     * @param cropImageData Crop Image Data
     */
    public ImageData(String key, ImageType type, ImageSource source, ImageOrientation orientation, MKREffect.Effect effect, CropImageData cropImageData) {
        mKey = key;
        mType = type;
        mSource = source;
        if ((mOrientation = orientation) == null) {
            mOrientation = ImageOrientation.NONE;
        }
        if ((mEffect = effect) == null) {
            mEffect = MKREffect.Effect.NONE;
        }
        mCropImageData = cropImageData;
    }

    /**
     * Constructor Complete
     *
     * @param key    Path / URL / ID / PrimaryKey
     * @param type   Type of Image we fetched
     * @param source Source of the Image, Frome where this Image is fetched
     */
    public ImageData(String key, ImageType type, ImageSource source) {
        this(key, type, source, null, null, null);
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
            // CHECK FOR IMAGE CROP DATA
            if (mCropImageData != null && imageData.mCropImageData != null) {
                if (!(mCropImageData.equals(imageData.mCropImageData))) {
                    return false;
                }
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
     * Method to get the Crop Image Data
     *
     * @return
     */
    public CropImageData getCropImageData() {
        return mCropImageData;
    }

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

    /**
     * Class to hold the data of a Cropart who shap is cropped from the Image
     */
    public class CropImageData {
        private ImageData mCropImageData;
        private float mCropImageDataLeft, mCropImageDataTop, mCropImageDataRight, mCropImageDataBottom;

        public CropImageData(ImageData cropImageData, float CropImageDataLeft, float CropImageDataTop, float CropImageDataRight, float CropImageDataBottom) {
            mCropImageData = cropImageData;
            mCropImageDataLeft = CropImageDataLeft;
            mCropImageDataRight = CropImageDataRight;
            mCropImageDataTop = CropImageDataTop;
            mCropImageDataBottom = CropImageDataBottom;
        }

        /**
         * Method to get the bottom limit of Crop image in Original Image
         *
         * @return
         */
        public float getCropImageDataBottom() {
            return mCropImageDataBottom;
        }

        /**
         * Method to get the left limit of Crop image in Original Image
         *
         * @return
         */
        public float getCropImageDataLeft() {
            return mCropImageDataLeft;
        }

        /**
         * Method to get the right limit of Crop image in Original Image
         *
         * @return
         */
        public float getCropImageDataRight() {
            return mCropImageDataRight;
        }

        /**
         * Method to get the top limit of Crop image in Original Image
         *
         * @return
         */
        public float getCropImageDataTop() {
            return mCropImageDataTop;
        }

        /**
         * Method to get the Image Data of the Crop Image
         *
         * @return
         */
        public ImageData getCropImageData() {
            return mCropImageData;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof CropImageData) {
                CropImageData cropImageData = (CropImageData) obj;
                // CHECK FOR CROP IMAGE DATA
                if (!mCropImageData.equals(cropImageData.mCropImageData)) {
                    return false;
                }
                // CHECK FOR CROP LEFT INDEX
                if (mCropImageDataLeft != cropImageData.mCropImageDataLeft) {
                    return false;
                }
                // CHECK FOR CROP TOP INDEX
                if (mCropImageDataTop != cropImageData.mCropImageDataTop) {
                    return false;
                }
                // CHECK FOR CROP RIGHT INDEX
                if (mCropImageDataRight != cropImageData.mCropImageDataRight) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }
}
