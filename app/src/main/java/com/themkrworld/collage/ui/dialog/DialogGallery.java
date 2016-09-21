package com.themkrworld.collage.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.themkrworld.collage.R;
import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.model.Gallery;
import com.themkrworld.collage.ui.adapter.AdapterAlbum;
import com.themkrworld.collage.ui.adapter.AdapterGallery;
import com.themkrworld.collage.ui.adapter.AdapterGallerySelectedPic;
import com.themkrworld.collage.ui.custom.MKRHorizontalListView;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

import java.util.Vector;

/**
 * Created by delhivery on 13/9/16.
 */
public class DialogGallery extends AlertDialog implements AdapterView.OnItemClickListener {
    private static final String TAG = AppConfig.BASE_TAG + ".DialogGallery";
    private static final int MAX_PIC_COUNT = 8;
    private Gallery mGallery;
    private int mSelecetedPicCount;
    private AdapterGallery mAdapterGallery;
    private AdapterAlbum mAdapterAlbum;
    private AdapterGallerySelectedPic mAdapterGallerySelectedPic;

    /**
     * Constructor
     *
     * @param context
     * @param gallery
     * @param selecetedPicCount
     */
    public DialogGallery(Context context, Gallery gallery, int selecetedPicCount) {
        super(context);
        mGallery = gallery;
        mSelecetedPicCount = selecetedPicCount;
    }

    /**
     * Constructor
     *
     * @param context
     * @param gallery
     */
    public DialogGallery(Context context, Gallery gallery) {
        this(context, gallery, MAX_PIC_COUNT);
    }

    @Override
    public void onAttachedToWindow() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = getContext().getResources().getDisplayMetrics().widthPixels;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
        super.onAttachedToWindow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_collage_gallery);
        // INIT ADAPTER
        mAdapterGallery = new AdapterGallery(getContext(), mGallery);
        // INIT LIST/GRID VIEW
        GridView gridView = (GridView) findViewById(R.id.dialog_collage_gallery_gridView);
        gridView.setOnItemClickListener(this);
        MKRHorizontalListView listView = (MKRHorizontalListView) findViewById(R.id.dialog_collage_gallery_listView);
        listView.setAdapter(mAdapterGallerySelectedPic = new AdapterGallerySelectedPic(getContext()));
        showGalleryView();
        setSelcetedImageData(null);
    }

    @Override
    public void dismiss() {
        GridView gridView = (GridView) findViewById(R.id.dialog_collage_gallery_gridView);
        ListAdapter adapter = gridView.getAdapter();
        if (adapter != null && adapter.equals(mAdapterAlbum)) {
            showGalleryView();
            return;
        }
        super.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tracer.debug(TAG, "onItemClick() " + parent);
        if (parent.getAdapter() != null) {
            if (parent.getAdapter().equals(mAdapterGallery)) {
                mAdapterAlbum = new AdapterAlbum(getContext(), mAdapterGallery.getItem(position).getPicImageDataVector());
                showAlbumView();
            } else if (parent.getAdapter().equals(mAdapterAlbum)) {
                Tracer.debug(TAG, "onItemClick()" + mAdapterAlbum.getItem(position).getKey());
                setSelcetedImageData(mAdapterAlbum.getItem(position));
            }
        }
    }

    /**
     * Method to show the Gallery View
     */
    private void showGalleryView() {
        if (mGallery != null && mAdapterGallery != null) {
            GridView gridView = (GridView) findViewById(R.id.dialog_collage_gallery_gridView);
            gridView.setAdapter(mAdapterGallery);
        }
    }

    private void showAlbumView() {
        if (mGallery != null && mAdapterAlbum != null) {
            GridView gridView = (GridView) findViewById(R.id.dialog_collage_gallery_gridView);
            gridView.setAdapter(mAdapterAlbum);
        }
    }

    /**
     * Method to add the selected Image Data
     *
     * @param imageData
     */
    private void setSelcetedImageData(ImageData imageData) {
        Tracer.debug(TAG, "setSelcetedImageData()");
        switch (mSelecetedPicCount) {
            case 1:
                ((TextView) findViewById(R.id.dialog_collage_gallery_textView_num_of_pic)).setText("Select single image from gallery");
                break;
            default:
                addSelcetedImageData(imageData, mSelecetedPicCount);
        }
    }

    /**
     * Method to add the selected Image Data
     *
     * @param imageData
     */
    private void addSelcetedImageData(ImageData imageData, int maxPicCount) {
        Tracer.debug(TAG, "addSelcetedImageData()");
        if (mAdapterGallerySelectedPic.getCount() >= maxPicCount) {
            ((TextView) findViewById(R.id.dialog_collage_gallery_textView_num_of_pic)).setText(mAdapterGallerySelectedPic.getCount() + "/" + maxPicCount + " MAX LIMIT REACHED");
        } else {
            if (imageData != null) {
                mAdapterGallerySelectedPic.addImageData(imageData);
            }
            ((TextView) findViewById(R.id.dialog_collage_gallery_textView_num_of_pic)).setText(mAdapterGallerySelectedPic.getCount() + "/" + maxPicCount);
        }
    }

    /**
     * Callback to return the
     */
    public interface OnDialogGalleryListener {
        /**
         * Callback to notify that user select the Pic
         *
         * @param imageData
         */
        public void onDialogGalleryImageSelected(ImageData imageData);

        /**
         * Callback to notify that user select the Pic
         *
         * @param imageDataList
         */
        public void onDialogGalleryImageSelected(Vector<ImageData> imageDataList);
    }
}
