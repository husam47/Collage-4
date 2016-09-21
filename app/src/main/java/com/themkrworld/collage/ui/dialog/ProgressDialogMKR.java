package com.themkrworld.collage.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.themkrworld.collage.R;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;


/**
 * Created by delhivery on 14/6/16.
 */
public class ProgressDialogMKR extends Dialog {
    private static final String TAG = AppConfig.BASE_TAG + ".ProgressDialogMKR";

    /**
     * Constractor
     *
     * @param context
     */
    public ProgressDialogMKR(Context context) {
        super(context);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Tracer.debug(TAG, "onCreate() ");
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        try {
            int divierId = getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider = findViewById(divierId);
            divider.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {
            e.printStackTrace();
            Tracer.error(TAG, "onCreate() " + e.getMessage());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
    }
}
