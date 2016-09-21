package com.themkrworld.collage.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.themkrworld.collage.R;
import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.ui.custom.ThumbImageView;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

import java.util.Vector;

/**
 * Created by delhivery on 15/9/16.
 */
public class AdapterAlbum extends BaseAdapter {
    private static final String TAG = AppConfig.BASE_TAG+".AdapterAlbum";
    private Vector<ImageData> mImageDataList;
    private LayoutInflater mLayoutInflater;

    /**
     * Constructor
     *
     * @param context
     * @param imageDataList List of images in the Album
     */
    public AdapterAlbum(Context context, Vector<ImageData> imageDataList) {
        mImageDataList = new Vector<>();
        if (imageDataList != null) {
            mImageDataList.addAll(imageDataList);
        }
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            convertView = mLayoutInflater.inflate(R.layout.adapter_collage_album, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mThumbImageView = (ThumbImageView) convertView.findViewById(R.id.adapter_collage_album_imageView_pic);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        try {
            viewHolder.mThumbImageView.setImageData(getItem(position));
        } catch (Exception e) {
            Tracer.error(TAG, "getView()" + e.getMessage());
            viewHolder.mThumbImageView.setImageData(null);
        }
        return convertView;
    }

    /**
     * Class to hold the view refference
     */
    private class ViewHolder {
        ThumbImageView mThumbImageView;
    }
}
