package com.yinxm.drawlayout_test;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {

    private TextView textView;

    public ContentFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_content,container, false);
        view.findViewById(R.id.openDrawLayoutActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Main3Activity.class));
            }
        });
        textView = (TextView) view.findViewById(R.id.textView);

        //接受传入的内容数据
        String str = getArguments().getString("text");
        System.out.println("[ContentFragment.onCreateView], str="+str);
        textView.setText(str);
        return view;
    }


}
