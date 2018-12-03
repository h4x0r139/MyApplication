package com.h4x0r.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.h4x0r.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {

    //声明Spinner对象
    private Spinner spinner = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        //通过ID，得到Spinner对象
        spinner = (Spinner) findViewById(R.id.spinnerId);
        List<String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        //调用ArrayAdapter的构造函数来创建ArrayAdapter对象
        //第一个参数是指上下文对象
        //第二个参数指定了下拉菜单当中每一个条目的样式
        //第三个参数指定了TextView控件的ID
        //第四个参数为整个列表提供数据
//        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.spinner_item,R.id.textViewId,list);


        //通过createFromResource方法创建一个ArrayAdapter对象
        //第一个参数是指上下文对象
        //第二参数引用了在strings.xml文件当中定义的String数组
        //第三个参数是用来指定Spinner的样式，是一个布局文件ID，该布局文件由Android系统提供，也可替换为自己定义的布局文件
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.planets_array,
				android.R.layout.simple_spinner_item);
		//设置Spinner当中每个条目的样式，同样是引用一个Android系统提供的布局文件
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//选项间间距比较大

        spinner.setAdapter(adapter);
        spinner.setPrompt("测试");
        //为spinner对象绑定监听器
        spinner.setOnItemSelectedListener(new SpinnerOnSelectedListener());
    }
    //这个监听器主要用来监听用户选择列表的动作
    class SpinnerOnSelectedListener implements OnItemSelectedListener{

        //当用户选定了一个条目时，就会调用该方法
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                   long id) {
            String selected = adapterView.getItemAtPosition(position).toString();
            System.out.println(selected);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // TODO Auto-generated method stub
            System.out.println("nothingSelected");
        }

    }
}
