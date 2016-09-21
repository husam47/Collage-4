package com.themkrworld.collage.utils;

import android.os.Environment;

/**
 * Created by delhivery on 21/3/16.
 */
public interface AppConfig {
    /**
     * Set this Boolean to show/hide logs
     */
    boolean SHOW_LOG = true;

    /**
     * The basic tag for the application log
     */
    String BASE_TAG = "TheMkrWorld.Collage";

    String BASE_ARCHIVE_PATH = Environment.getExternalStorageDirectory().toString() + "/Collage";
}
