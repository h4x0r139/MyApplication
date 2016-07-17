package com.yinxm.fragment_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FirstQu on 2016/1/12.
 */
public class AnotherFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("AnotherFragment.onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //初始化布局
        View rootView = inflater.inflate(R.layout.fragment_another,container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
        //主动后退
        rootView.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        System.out.println("AnotherFragment.onCreateView");
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("AnotherFragment.onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("AnotherFragment.onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("AnotherFragment.onDestroy");
    }
}
