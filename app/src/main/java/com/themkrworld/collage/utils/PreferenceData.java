package com.themkrworld.collage.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceData {

    private static final String TAG = AppConfig.BASE_TAG + ".PreferenceData";
    private static final String STORE = "STORE_8";
    private static String APP_INITIALIZE = "APP_INITIALIZE";

    //==================================================================================================================
    //==================================================================================================================
    //==================================================================================================================


    /**
     * Method to know weather the app is initialized or not
     *
     * @param context
     * @return TRUE if initialized else FALSE
     */
    public static boolean isAppInitialized(Context context) {
        Tracer.debug(TAG, "isAppInitialized() ");
        return getShearedPreference(context).getBoolean(APP_INITIALIZE, false);
    }

    /**
     * Method to set that app is initialized
     *
     * @param context
     */
    public static void setAppInitialize(Context context) {
        Tracer.debug(TAG, "setAppInitialize() ");
        getShearedPreferenceEditor(context).putBoolean(APP_INITIALIZE, true).commit();
    }

    //==================================================================================================================
    //==================================================================================================================
    //==================================================================================================================

    /**
     * Method to clear the Data Store
     *
     * @param context
     */
    public static void clearStore(Context context) {
        Tracer.debug(TAG, "clearStore()");
        getShearedPreferenceEditor(context).clear().commit();
    }

    /**
     * Method to return the Data Store Prefference
     *
     * @param context
     * @return
     */
    private static SharedPreferences getShearedPreference(Context context) {
        return context.getSharedPreferences(STORE, Context.MODE_PRIVATE);
    }

    /**
     * caller to commit this editor
     *
     * @param context
     * @return Editor
     */
    private static Editor getShearedPreferenceEditor(Context context) {
        return getShearedPreference(context).edit();
    }
}
