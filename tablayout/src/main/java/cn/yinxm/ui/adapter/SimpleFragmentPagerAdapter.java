package cn.yinxm.ui.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.yinxm.ui.R;
import cn.yinxm.ui.fragment.Test1Fragment;

/**
 * 功能：
 * Created by yinxm on 2017/5/2.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"tab1", "tab2", "tab3"};
//    private String tabTitles[] = new String[]{"tab1", "tab2", "tab3",
//            "推荐", "热门", "新闻", "财经", "本地"};
    private Context context;
    private int[] imageResId = {
            R.drawable.collect,
            R.drawable.down,
            R.drawable.main
    };

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return Test1Fragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

  /*  @Override
    public CharSequence getPageTitle(int position) {//标题会自动大写
        Log.d("yinxm","titile ="+tabTitles[position]);
        //        return tabTitles[position];

        //ICON添加到tab中使用SpannableString结合ImageSpan来实现,同时设置textAllCaps为false
//        Drawable imgIcon = context.getResources().getDrawable(imageResId[position%3]);
//        Log.d("yinxm","imgIcon ="+imgIcon+", width="+imgIcon.getIntrinsicWidth()+", height="+imgIcon.getIntrinsicHeight());
//
//        imgIcon.setBounds(0,0, imgIcon.getIntrinsicWidth(), imgIcon.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" "+tabTitles[position]);//图标控件预留
//        ImageSpan imageSpan = new ImageSpan(imgIcon, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;

        // TODO: 2017/5/2  

        //无法显示
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString("  ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;

//        ImageSpan imageSpan = new ImageSpan(context, R.drawable.collect);
//        SpannableString spannableString = new SpannableString("收藏图标！");
//        Log.d("yinxm", "spannableString="+spannableString.length()+", "+spannableString);
//        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        return spannableString;
    }*/

    public View getTabView(int position, TabLayout.Tab tab){

        View v = LayoutInflater.from(context).inflate(R.layout.layout_custom_tab,null);
        ImageView img = (ImageView)v.findViewById(R.id.tab_icon);
        img.setImageResource(imageResId[position]);
        TextView tv = (TextView)v.findViewById(R.id.tab_text);
        tv.setText(tabTitles[position]);
        if(position == 0){
            v.setSelected(true);
        }
        return v;
    }

}
