package com.themkrworld.collage.interfaces;

/**
 * Callback to listen the back pressed event inside the fragment
 */
public interface OnBackPressedListener {
    /**
     * Method to notify user press the back button
     *
     * @return TRUE if want to block this event, Else FALSE
     */
    public boolean onBackPressed();
}
