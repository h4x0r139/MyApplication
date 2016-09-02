package com.example.yinxm.lib.swipemenu.interfaces;


import com.example.yinxm.lib.swipemenu.bean.SwipeMenu;
import com.example.yinxm.lib.swipemenu.view.SwipeMenuView;

public interface OnSwipeItemClickListener {

    void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);

}