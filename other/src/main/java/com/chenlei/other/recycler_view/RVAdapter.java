package com.chenlei.other.recycler_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenlei.other.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Chen Lei
 *
 * RecyclerView适配器需要自定义类继承RecyclerView.Adapter，
 * 并且实现内部类继承RecyclerView.ViewHolder作为RecyclerView.Adapter的泛型参数
 * 并重写该类的三个方法
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    //列表项数据
    private List<RecyclerViewItem> rviList;

    /**
     * 继承自RecyclerView.ViewHolder，初始化列表项内控件
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.recycler_view_tv);
        }
    }

    /**
     * 构造函数出入列表项数据
     * @param rviList
     */
    public RVAdapter(List<RecyclerViewItem> rviList){
        this.rviList=rviList;
    }

    /**
     * 用于创建ViewHolder实例，动态加载列表项视图
     * @param viewGroup 最外层布局
     * @param i 列表项id
     * @return 列表项视图
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    /**
     * 对RecyclerView子项的数据进行赋值
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RecyclerViewItem rvi=rviList.get(i);
        viewHolder.tv.setText(rvi.getTv());
    }

    /**
     * 得到列表长度
     * @return 列表长度
     */
    @Override
    public int getItemCount() {
        return rviList.size();
    }

}
