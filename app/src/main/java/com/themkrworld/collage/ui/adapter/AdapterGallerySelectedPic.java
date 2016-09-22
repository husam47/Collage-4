package com.themkrworld.collage.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.themkrworld.collage.R;
import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.ui.custom.ThumbImageView;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

import java.util.Vector;

/**
 * Created by delhivery on 15/9/16.
 */
public class AdapterGallerySelectedPic extends BaseAdapter {
    private static final String TAG = AppConfig.BASE_TAG + ".AdapterGallerySelectedPic";
    private Vector<ImageData> mImageDataList;
    private LayoutInflater mLayoutInflater;

    /**
     * Constructor
     *
     * @param context
     */
    public AdapterGallerySelectedPic(Context context) {
        mImageDataList = new Vector<>();
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Method to add the image data of a new selected pic
     *
     * @param imageData
     */
    public void addImageData(ImageData imageData) {
        Tracer.debug(TAG, "addImageData() " + imageData.getKey());
        mImageDataList.add(imageData);
        notifyDataSetChanged();
    }

    /**
     * Method to get the list of selected pic
     *
     * @return
     */
    public Vector<ImageData> getImageDataList() {
        return mImageDataList;
    }

    @Override
    public int getCount() {
        return mImageDataList.size();
    }

    @Override
    public ImageData getItem(int position) {
        return mImageDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_collage_gallery_selected_pic, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mThumbImageView = (ThumbImageView) convertView.findViewById(R.id.adapter_collage_gallery_selected_pic_imageView_pic);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mThumbImageView.setImageData(getItem(position));
        return convertView;
    }

    /**
     * Class to hold the view refference
     */
    private class ViewHolder {
        ThumbImageView mThumbImageView;
        ImageView mImageViewCross;
    }
}
