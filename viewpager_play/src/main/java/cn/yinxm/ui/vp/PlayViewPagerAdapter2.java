package cn.yinxm.ui.vp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.LinkedList;
import java.util.List;

import static cn.yinxm.ui.vp.LogUtil.TAG;

/**
 * Created by yinxm on 2016/5/16.
 */
public class PlayViewPagerAdapter2 extends PagerAdapter{
    List<Integer> data;
    private LinkedList<View> viewCache;

    LayoutInflater inflater;
    PlayViewPagerAdapter2(Context context, List<Integer> data) {
        this.data = data;
        viewCache = new LinkedList<View>();
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        if (data == null) {
            return 0;
        }else {
            return data.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d(TAG, "destroyItem position="+position+", viewCache.size="+viewCache.size());
        View convertView = (View) object;
        container.removeView(convertView);
        this.viewCache.add(convertView);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem position="+position+", viewCache.size="+viewCache.size());
        ViewHolder viewHolder = null;
        View convertView = null;
        if (viewCache.size() == 0) {
            convertView = inflater.inflate(R.layout.layout_music, null);
            viewHolder = new ViewHolder();

            viewHolder.img_audioIcon = (RoundImageViewByXfermode) convertView.findViewById(R.id.img_audioIcon);
            viewHolder.img_playTriangle = (ImageView) convertView.findViewById(R.id.img_playTriangle);
//            Glide.with(this).load(audioBean.cover).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.moren).into(roundImageIcon);
            viewHolder.player_seekbar = (RoundProgress) convertView.findViewById(R.id.player_seekbar);
/*            roundProgress.setMax(audioBean.duration);
            roundProgress.setProgress(0);*/
            convertView.setTag(viewHolder);

        } else  {
            convertView = viewCache.removeFirst();
            viewHolder = (ViewHolder) convertView.getTag();
        }
        switch (position) {
            case 0:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number01);
                break;
            case 1:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number02);
                break;
            case 2:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number03);
                break;
            case 3:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number04);
                break;
            case 4:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number05);
                break;
            case 5:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number06);
                break;

        }
        container.addView(convertView);
        return convertView;
    }

    public final class ViewHolder{
        public RoundImageViewByXfermode img_audioIcon;
        public ImageView img_playTriangle;
        public RoundProgress  player_seekbar;
    }
}
