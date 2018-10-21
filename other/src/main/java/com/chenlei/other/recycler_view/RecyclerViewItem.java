package com.chenlei.other.recycler_view;

/**
 * @author Chen Lei
 *
 * RecyclerView列表项的实体类
 */
public class RecyclerViewItem {
    private String tv;

    public RecyclerViewItem(String tv){
        this.tv=tv;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }
}
