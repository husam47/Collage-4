package com.themkrworld.collage.model;

import com.themkrworld.collage.cache.ImageData;

import java.util.Vector;

/**
 * Created by delhivery on 15/9/16.
 */
public class Album {
    private String mAlbumName;
    private Vector<ImageData> mPicImageDataVector;

    /**
     * Constructor
     *
     * @param albumName
     */
    public Album(String albumName) {
        mAlbumName = albumName;
        mPicImageDataVector = new Vector<>();
    }

    /**
     * Method to add the Data of the Image
     *
     * @param imageData
     */
    public void addPicImageData(ImageData imageData) {
        mPicImageDataVector.add(imageData);
    }

    /**
     * Method to get the Name of the Dir
     *
     * @return
     */
    public String getAlbumName() {
        return mAlbumName;
    }

    /**
     * Method to get the List of the Images in this Album
     *
     * @return
     */
    public Vector<ImageData> getPicImageDataVector() {
        return mPicImageDataVector;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Album) {
            if (mAlbumName.equals(((Album) o).mAlbumName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return mAlbumName.hashCode();
    }
}
