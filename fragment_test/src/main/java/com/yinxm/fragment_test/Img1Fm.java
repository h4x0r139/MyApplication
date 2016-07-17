package com.yinxm.fragment_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by FirstQu on 2016/1/14.
 */
public class Img1Fm extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        ImageView imgView = new ImageView(getActivity());
        imgView.setImageResource(R.drawable.img1);
        return imgView;
    }
}
