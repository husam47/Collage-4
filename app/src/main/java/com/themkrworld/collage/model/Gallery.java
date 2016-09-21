package com.themkrworld.collage.model;

import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by delhivery on 15/9/16.
 */
public class Gallery {
    private static final String TAG = AppConfig.BASE_TAG + ".Gallery";
    private HashMap<String, Album> mAlbumMap;

    /**
     * Constructor
     */
    public Gallery() {
        Tracer.debug(TAG, "Gallery()");
        mAlbumMap = new HashMap<>();
    }

    /**
     * Method to add the Image data of Pic in the Album
     *
     * @param albumName
     * @param imageData
     */
    public void addPicImageData(String albumName, ImageData imageData) {
        if (!mAlbumMap.containsKey(albumName)) {
            Tracer.debug(TAG, "addPicImageData()" + albumName);
            mAlbumMap.put(albumName, new Album(albumName));
        }
        mAlbumMap.get(albumName).addPicImageData(imageData);
    }

    /**
     * Method to get the Album with name associate with it
     *
     * @param albumName
     * @return
     */
    public Album getAlbum(String albumName) {
        Tracer.debug(TAG, "getAlbum()" + albumName);
        return mAlbumMap.get(albumName);
    }

    /**
     * Method to get the name list of all the Album is the Device
     *
     * @return
     */
    public Vector<String> getAlbumNameList() {
        Tracer.debug(TAG, "getAlbumNameList()");
        return new Vector<String>(mAlbumMap.keySet());
    }

}
