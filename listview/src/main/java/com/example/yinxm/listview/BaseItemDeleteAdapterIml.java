package com.example.yinxm.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yinxm on 2016/9/1.
 */
public class BaseItemDeleteAdapterIml extends BaseItemDeleteAdapter_Add {
    private List<String> list;
    private Context context;

    public BaseItemDeleteAdapterIml(Context context, List<String> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if(convertView==null){
//            viewHolder=new ViewHolder();
//            convertView= LayoutInflater.from(context).inflate(R.layout.list_play_item,null);
//            viewHolder.tv_title = (TextView)convertView.findViewById(R.id.tv_titles);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder=(ViewHolder)convertView.getTag();
//        }
//        viewHolder.tv_title.setText(list.get(position));
//
//        return convertView;

//不使用缓存
        if(convertView==null){
            convertView=View.inflate(context, R.layout.list_play_item,null);
            //对于listview，注意添加这一行，即可在item上使用高度
        }
        TextView title = (TextView)convertView.findViewById(R.id.tv_titles);
        title.setText(list.get(position));
        return convertView;
    }

    @Override
    public void cancelDeleteOnClick() {
        super.cancelDeleteOnClick();
    }

    @Override
    public void okDeleteOnClick() {
    }

    static class ViewHolder{
        TextView tv_title;
    }
}
