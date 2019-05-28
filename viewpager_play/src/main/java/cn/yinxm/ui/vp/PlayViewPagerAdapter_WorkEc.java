package cn.yinxm.ui.vp;
//package cn.com.work_ec.ec.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yinxm on 2016/5/16.
 */
public class PlayViewPagerAdapter_WorkEc  extends PagerAdapter {
    List<AudioBean> dataList = new ArrayList<>();
    ViewHolder currentView;
    private View[] viewCache;
    private Context context;
    private LayoutInflater inflater;

    public PlayViewPagerAdapter_WorkEc(Context context, List<AudioBean> data) {
        super();
        if (data != null) {
            this.dataList.addAll(data);
        }
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        viewCache = new View[3];
        for (int i=0; i<viewCache.length; i++) {
            View tempConvertView = inflater.inflate(R.layout.layout_play_logo, null);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.icon_layout = (LinearLayout) tempConvertView.findViewById(R.id.icon_layout);
            viewHolder.img_audioIcon = (RoundImageViewByXfermode) tempConvertView.findViewById(R.id.img_audioIcon);
            viewHolder.img_playTriangle = (ImageView) tempConvertView.findViewById(R.id.img_playTriangle);
            viewHolder.player_seekbar = (RoundProgress) tempConvertView.findViewById(R.id.player_seekbar);
            tempConvertView.setTag(viewHolder);
            viewCache[i] = tempConvertView;
        }
    }

    public void addData(List<AudioBean> data) {
        this.dataList.addAll(data);
    }
    public void setData(List<AudioBean> data) {
        this.dataList.clear();
        if (data != null) {
            data.addAll(data);
        }
    }



    @Override
    public int getCount() {
        int count = 0;
        if (dataList == null) {
            count = 0;
        }else {
            count = dataList.size();
        }
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int cachePosition = position % 3;
        LogUtil.d("instantiateItem position="+position+", cachePosition="+cachePosition);

        final ViewHolder viewHolder = (ViewHolder) viewCache[cachePosition].getTag();
        if (viewHolder != null) {
            viewHolder.img_audioIcon.setImageResource(R.mipmap.moren);
            Glide.with(context).load(dataList.get(position).cover).error(R.mipmap.moren).placeholder(R.mipmap.moren).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    viewHolder.img_audioIcon.setImageDrawable(resource);
                }
            });
        }
        ViewGroup parentGroup = (ViewGroup) viewCache[cachePosition].getParent();
        if (parentGroup != null) {
            parentGroup.removeView(viewCache[cachePosition]);
        }
        container.addView(viewCache[cachePosition]);
        currentView = viewHolder;
        return viewCache[cachePosition];
    }

    public ViewHolder getCurrentView(int position) {
        return (ViewHolder) viewCache[position%3].getTag();
    }

    public final class ViewHolder{
        public LinearLayout icon_layout;
        public RoundImageViewByXfermode img_audioIcon;
        public ImageView img_playTriangle;
        public RoundProgress  player_seekbar;
    }
}
