package com.themkrworld.collage.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.themkrworld.collage.R;
import com.themkrworld.collage.model.CollageOptionInfo;
import com.themkrworld.collage.ui.custom.ThumbImageView;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

import java.util.Vector;

/**
 * Created by delhivery on 15/9/16.
 */
public class AdapterCollageOption extends BaseAdapter {
    private static final String TAG = AppConfig.BASE_TAG + ".AdapterCollageOption";
    private Vector<CollageOptionInfo> mCollageOptionInfoList;
    private LayoutInflater mLayoutInflater;

    /**
     * Constructor
     *
     * @param context
     * @param collageOptionInfoList List of images in the Album
     */
    public AdapterCollageOption(Context context, Vector<CollageOptionInfo> collageOptionInfoList) {
        Tracer.debug(TAG, "AdapterCollageOption()");
        mCollageOptionInfoList = new Vector<>();
        if (collageOptionInfoList != null) {
            mCollageOptionInfoList.addAll(collageOptionInfoList);
        }
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mCollageOptionInfoList.size();
    }

    @Override
    public CollageOptionInfo getItem(int position) {
        return mCollageOptionInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_collage_option, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mThumbImageView = (ThumbImageView) convertView.findViewById(R.id.adapter_collage_option_imageView_pic);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        try {
            viewHolder.mThumbImageView.setImageData(getItem(position).getImageData(), true);
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
