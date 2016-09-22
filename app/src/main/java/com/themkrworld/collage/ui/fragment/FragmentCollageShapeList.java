package com.themkrworld.collage.ui.fragment;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.themkrworld.collage.R;
import com.themkrworld.collage.cache.ImageData;
import com.themkrworld.collage.interfaces.OnBackPressedListener;
import com.themkrworld.collage.model.CollageOptionInfo;
import com.themkrworld.collage.ui.adapter.AdapterCollageOption;
import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Constants;
import com.themkrworld.collage.utils.Tracer;

import java.util.Locale;
import java.util.Vector;

/**
 * Created by delhivery on 13/9/16.
 */
public class FragmentCollageShapeList extends Fragment implements OnBackPressedListener, AdapterView.OnItemClickListener {
    private static final String TAG = AppConfig.BASE_TAG + ".FragmentCollageShapeList";
    private AdapterCollageOption mAdapterCollageOption;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Tracer.debug(TAG, "onCreateView()");
        return inflater.inflate(R.layout.fragment_collage_list, container, false);
    }

    @Override
    public void onViewCreated(View parentView, Bundle savedInstanceState) {
        super.onViewCreated(parentView, savedInstanceState);
        Tracer.debug(TAG, "onViewCreated()");
        ((TextView) parentView.findViewById(R.id.fragment_collage_list_textView)).setText("Select a Shape to create collage");
        Vector<CollageOptionInfo> gridCollageThumbInfo = getGridCollageThumbInfo();
        GridView gridView = (GridView) parentView.findViewById(R.id.fragment_collage_list_gridView);
        gridView.setAdapter(mAdapterCollageOption = new AdapterCollageOption(getActivity(), gridCollageThumbInfo));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public boolean onBackPressed() {
        Tracer.debug(TAG, "onBackPressed()");
        if (getActivity() instanceof OnFragmentCollageShapeListListener) {
            ((OnFragmentCollageShapeListListener) getActivity()).onFragmentCollageShapeListShowHomeFragment();
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tracer.debug(TAG, "onItemClick()");
        if (getActivity() instanceof OnFragmentCollageShapeListListener) {
            ((OnFragmentCollageShapeListListener) getActivity()).onFragmentCollageShapeListShowShapeCollageFragment(mAdapterCollageOption.getItem(position));
        }
    }

    /**
     * Method to get the list of all the grid collage
     *
     * @return
     */
    private Vector<CollageOptionInfo> getGridCollageThumbInfo() {
        Vector<CollageOptionInfo> collageOptionInfoVector = new Vector<>();
        AssetManager assets = getActivity().getAssets();
        try {
            Locale locale = Locale.getDefault();
            String preTag = Constants.ASSETS_FILE_PRE_TAG.ASSETS_THUMB_SHAPE.toUpperCase(locale);
            String[] list = assets.list(Constants.ASSETS_PATH.ASSETS_THUMB_PATH);
            for (String folderName : list) {
                Tracer.debug(TAG, "getGridCollageThumbInfo() " + folderName);
                try {
                    int picCount = Integer.parseInt(folderName);
                    String subFolderPath = Constants.ASSETS_PATH.ASSETS_THUMB_PATH + "/" + folderName;
                    String[] subList = assets.list(subFolderPath);
                    for (String fileName : subList) {
                        if (fileName.toUpperCase(locale).startsWith(preTag)) {
                            String picPath = subFolderPath + "/" + fileName;
                            ImageData imageData = new ImageData(picPath, ImageData.ImageType.ORIGINAL_SIZE, ImageData.ImageSource.ASSETS);
                            collageOptionInfoVector.add(new CollageOptionInfo(fileName, picCount, imageData));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Tracer.error(TAG, "getGridCollageThumbInfo()" + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Tracer.error(TAG, "getGridCollageThumbInfo()");
        }


        return collageOptionInfoVector;
    }

    /**
     * Callback to notify the event and selection occur by the user in this fragment
     */
    public interface OnFragmentCollageShapeListListener {
        /**
         * Method to notify that user press the back button
         */
        public void onFragmentCollageShapeListShowHomeFragment();

        /**
         * Method to notify that user select the Shape for collage
         *
         * @param collageOptionInfo Collage selected by the user
         */
        public void onFragmentCollageShapeListShowShapeCollageFragment(CollageOptionInfo collageOptionInfo);
    }
}
