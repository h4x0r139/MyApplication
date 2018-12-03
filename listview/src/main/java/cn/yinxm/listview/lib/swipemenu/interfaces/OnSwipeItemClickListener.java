package cn.yinxm.listview.lib.swipemenu.interfaces;


import cn.yinxm.listview.lib.swipemenu.bean.SwipeMenu;
import cn.yinxm.listview.lib.swipemenu.view.SwipeMenuView;

public interface OnSwipeItemClickListener {

    void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);

}