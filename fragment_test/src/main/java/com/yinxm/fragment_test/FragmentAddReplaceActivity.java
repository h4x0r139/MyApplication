package com.yinxm.fragment_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

/**
 * 问题；从热门搜索页跳转到搜索结果页后返回，不显示热门搜索
 */
public class FragmentAddReplaceActivity extends FragmentActivity {
    public static final String TAG = FragmentAddReplaceActivity.class.getSimpleName();

    Img1Fm mHotFragment;
    Img2Fm fm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_add_replace);

        mHotFragment = new Img1Fm();

        mHotFragment = new Img1Fm();
        Log.d(TAG, "AudioSearchActivity.onClick 1 mHotFragment="+mHotFragment+", isAdded="+mHotFragment.isAdded()+", isVisible="+mHotFragment.isVisible()+", isDetached="+mHotFragment.isDetached()+", isRemoving="+mHotFragment.isRemoving());
        if (mHotFragment.isAdded()) {
            Log.d(TAG, "show ");
            getSupportFragmentManager().beginTransaction().show(mHotFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().remove(mHotFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.content, mHotFragment).commit();
            Log.d(TAG, "add ");
        }


        //先搜索
        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fm2 = new Img2Fm();
                fragmentTransaction.replace(R.id.content, fm2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commitAllowingStateLoss();
            }
        });

        //再从搜索结果页返回
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.content);
                Log.d(TAG, "AudioSearchActivity.onClick fragment="+fragment);
                if (fragment instanceof Img2Fm){
//                    mEditText.setText("");
//                    mEditText.clearFocus();
                    Log.d(TAG, "AudioSearchActivity.onClick 5 mHotFragment="+mHotFragment+", isAdded="+mHotFragment.isAdded()+", isVisible="+mHotFragment.isVisible()+", isDetached="+mHotFragment.isDetached()+", isRemoving="+mHotFragment.isRemoving()+", "+mHotFragment.isInLayout());

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, mHotFragment);
                    fragmentTransaction.commit();
//                    delete.setVisibility(View.GONE);
                    Log.d(TAG, "AudioSearchActivity.onClick 6 mHotFragment="+mHotFragment+", isAdded="+mHotFragment.isAdded()+", isVisible="+mHotFragment.isVisible()+", isDetached="+mHotFragment.isDetached()+", isRemoving="+mHotFragment.isRemoving());

                }else{
                    finish();
                }

            }
        });

    }
}
