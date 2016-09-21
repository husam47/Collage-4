package com.themkrworld.collage.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.themkrworld.collage.R;
import com.themkrworld.collage.model.Album;
import com.themkrworld.collage.model.Gallery;
import com.themkrworld.collage.ui.custom.ThumbImageView;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

import java.util.Vector;

/**
 * Created by delhivery on 15/9/16.
 */
public class AdapterGallery extends BaseAdapter {
    private static final String TAG = AppConfig.BASE_TAG + ".AdapterGallery";
    private Vector<String> mAlbumNameList;
    private Gallery mGallery;
    private LayoutInflater mLayoutInflater;

    /**
     * Constructor
     *
     * @param context
     * @param gallery Object of the Gallery
     */
    public AdapterGallery(Context context, Gallery gallery) {
        mGallery = gallery;
        mAlbumNameList = gallery.getAlbumNameList();
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAlbumNameList.size();
    }

    @Override
    public Album getItem(int position) {
        return mGallery.getAlbum(mAlbumNameList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_collage_gallery, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mThumbImageView = (ThumbImageView) convertView.findViewById(R.id.adapter_collage_gallery_imageView_pic);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.adapter_collage_gallery_textView_album_name);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Album album = getItem(position);
        try {
            viewHolder.mTextView.setText(album.getAlbumName());
            viewHolder.mThumbImageView.setImageData(album.getPicImageDataVector().get(0));
        } catch (Exception e) {
            Tracer.error(TAG, "getView()" + e.getMessage());
            viewHolder.mTextView.setText(album.getAlbumName());
            viewHolder.mThumbImageView.setImageData(null);
        }
        return convertView;
    }

    /**
     * Class to hold the view refference
     */
    private class ViewHolder {
        ThumbImageView mThumbImageView;
        TextView mTextView;
    }
}
