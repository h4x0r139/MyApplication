package cn.yinxm.listview.lib.pulltorefresh.interfaces;

/**
 * implements this interface to get refresh/load more event.
 */
public interface IXListViewListener {
    void onRefresh();

    void onLoadMore();
}