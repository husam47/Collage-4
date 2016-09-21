package com.themkrworld.collage.ui.fragment;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.Vector;

/**
 * Created by delhivery on 13/9/16.
 */
public class FragmentCollageFrameList extends Fragment implements OnBackPressedListener {
    private static final String TAG = AppConfig.BASE_TAG + ".FragmentCollageFrameList";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Tracer.debug(TAG, "onCreateView()");
        return inflater.inflate(R.layout.fragment_collage_list, container, false);
    }

    @Override
    public void onViewCreated(View parentView, Bundle savedInstanceState) {
        super.onViewCreated(parentView, savedInstanceState);
        Tracer.debug(TAG, "onViewCreated()");
        ((TextView) parentView.findViewById(R.id.fragment_collage_list_textView)).setText("Select a Frame to create collage");
        Vector<CollageOptionInfo> gridCollageThumbInfo = getGridCollageThumbInfo();
        ((GridView) parentView.findViewById(R.id.fragment_collage_list_gridView)).setAdapter(new AdapterCollageOption(getActivity(), gridCollageThumbInfo));
    }

    @Override
    public boolean onBackPressed() {
        Tracer.debug(TAG, "onBackPressed()");
        if (getActivity() instanceof OnFragmentCollageFrameListListener) {
            ((OnFragmentCollageFrameListListener) getActivity()).onFragmentCollageFrameListShowHomeFragment();
            return true;
        }
        return false;
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
            int picCount = 1;
            // FETCH RECT FRAME PATH
            String dirPath = Constants.ASSETS_PATH.ASSETS_RECT_FRAME_PATH;
            String[] frameRectList = assets.list(dirPath);
            for (String fileName : frameRectList) {
                Tracer.debug(TAG, "getGridCollageThumbInfo() " + fileName);
                String picPath = dirPath + "/" + fileName;
                ImageData imageData = new ImageData(picPath, ImageData.ImageType.ORIGINAL_SIZE, ImageData.ImageSource.ASSETS);
                collageOptionInfoVector.add(new CollageOptionInfo(fileName, picCount, imageData));
            }
            // FETCH SQUARE FRAME PATH
            dirPath = Constants.ASSETS_PATH.ASSETS_SQUARE_FRAME_PATH;
            String[] frameSquareList = assets.list(dirPath);
            for (String fileName : frameSquareList) {
                Tracer.debug(TAG, "getGridCollageThumbInfo() " + fileName);
                String picPath = dirPath + "/" + fileName;
                ImageData imageData = new ImageData(picPath, ImageData.ImageType.ORIGINAL_SIZE, ImageData.ImageSource.ASSETS);
                collageOptionInfoVector.add(new CollageOptionInfo(fileName, picCount, imageData));
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
    public interface OnFragmentCollageFrameListListener {
        /**
         * Method to notify that user press the back button
         */
        public void onFragmentCollageFrameListShowHomeFragment();

        /**
         * Method to notify that user select the Shape for collage
         *
         * @param collageOptionInfo Collage selected by the user
         */
        public void onFragmentCollageFrameListShowFrameCollageFragment(CollageOptionInfo collageOptionInfo);
    }
}
