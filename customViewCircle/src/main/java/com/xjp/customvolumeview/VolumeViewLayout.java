package com.xjp.customvolumeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.R;

/**
 * Description:组合布局实现类似小米手机音量UI
 * User: xjp
 * Date: 2015/5/29
 * Time: 18:06
 */

public class VolumeViewLayout extends FrameLayout {

    private VolumeView volumeView;
    private ImageView icon;
    private TextView title;

    public VolumeViewLayout(Context context) {
        this(context, null);
    }

    public VolumeViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.volume_view_layout, this);
        volumeView = (VolumeView) view.findViewById(R.id.volume);
        icon = (ImageView) view.findViewById(R.id.img_volume);
        title = (TextView) view.findViewById(R.id.text);
    }

    /**
     * 设置标题
     *
     * @param msg
     */
    public void setTitle(String msg) {
        title.setText(msg);
    }

    /**
     * 设置图片
     *
     * @param resId
     */
    public void setIcon(int resId) {
        icon.setImageResource(resId);
    }

    /**
     * 加音量
     */
    public void volumeUp() {
        volumeView.volumeUp();
    }

    /**
     * 减音量
     */
    public void volumeDown() {
        volumeView.volumeDown();
    }
}
