package com.example.yinxm.lib.pulltorefresh.interfaces;

import android.view.View;
import android.widget.AbsListView.OnScrollListener;

/**
 * you can listen ListView.OnScrollListener or this one. it will invoke
 * onXScrolling when header/footer scroll back.
 */
public interface OnXScrollListener extends OnScrollListener {
    void onXScrolling(View view);
}