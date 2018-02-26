package com.yinxm.fragment_test;

import android.content.Intent;
import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MainActivityFragment.onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //点击曾宪另一个Fragment
        rootView.findViewById(R.id.btnShowAnotherFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //两种方式呈现，replace、add
//                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new AnotherFragment()).commit();//addToBackStack 添加到后退栈
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, new AnotherFragment()).commit();//addToBackStack 添加到后退栈
            }
        });
        rootView.findViewById(R.id.btnShowNavigationFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动NavigationActivity
                startActivity(new Intent(getActivity(), SliderActivity.class));
            }
        });

        rootView.findViewById(R.id.btnShowTabbedActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Tabs.class));
            }
        });

        rootView.findViewById(R.id.btnBackStack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FragmentBackStackActivity.class));
            }
        });

        //Fragment add replace 测试
         rootView.findViewById(R.id.btn_add_replace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FragmentAddReplaceActivity.class));
            }
        });



        System.out.println("MainActivityFragment.onCreateView");
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("MainActivityFragment.onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("MainActivityFragment.onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivityFragment.onDestroy");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        System.out.println("MainActivityFragment.setUserVisibleHint isVisibleToUser="+isVisibleToUser);

    }
}
