package com.yinxm.fragment_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FragmentBackStackActivity extends FragmentActivity {
    Button btnChangeFragment, btnBack;
    View fl;
    Fragment fragment1, fragment2;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_back_stack);

        fl = findViewById(R.id.fl);
        btnChangeFragment = (Button) findViewById(R.id.btnChangeFragment);
        btnBack = (Button) findViewById(R.id.btnBack);


        fragment1 = new Img1Fm();
        fragment2 = new Img2Fm();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fl,  fragment1).add(R.id.fl, fragment2).commit();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment2).show(fragment1);
        fragmentTransaction.commit();

        btnChangeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                //java.lang.IllegalStateException: Fragment already added: Img2Fm{b4de6ff #1 id=0x7f0c005a}
//                fragmentManager.beginTransaction().add(R.id.fl,  fragment2).addToBackStack(null).show(fragment2).commit();//
                Log.d("yinxm", "i="+i);
                if (i%2 == 0) {
                    fragmentManager.beginTransaction().hide(fragment2).show(fragment1).addToBackStack(null).commit();
                } else {
                    fragmentManager.beginTransaction().hide(fragment1).show(fragment2).addToBackStack(null).commit();
                }
                i += 1;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                int count = fragmentManager1.getBackStackEntryCount();

                Log.d("yinxm","getBackStackEntryCount="+count+", "+fragmentManager1.getFragments());
                for (int i=0; i<count; i++) {
                    Log.d("yinxm", i+"="+fragmentManager1.getBackStackEntryAt(i));
                }
                fragmentManager1.popBackStack();
            }
        });
    }
}
