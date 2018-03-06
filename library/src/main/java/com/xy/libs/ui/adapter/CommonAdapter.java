package com.xy.libs.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xy.libs.util.app.NoDoubleClickListener;

import java.util.List;

public abstract class CommonAdapter<T, VH extends CommonViewHolder> extends RecyclerView.Adapter<VH> {
    private Context context;
    private List<T> itemData;
    private static final String TAG = "CommonAdapter";

    public CommonAdapter(Context context, List<T> itemData) {
        this.itemData = itemData;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, itemData.get(position));
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(layoutResId(viewType), parent, false);
        return holderInstance(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T item = itemData.get(position);
        /**instance */
        holder.position = position;
        holder.setAdapter(this);
        holder.context = context;
        fillView(holder, item, position);
        holder.itemView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                holder.OnItemClick(position);
            }
        });
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        T item = itemData.get(position);
        /**instance */
        holder.position = position;
        holder.setAdapter(this);
        holder.context = context;
        fillView(holder, item, position);
        holder.itemView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                holder.OnItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemData == null ? 0 : itemData.size();
    }

    /**
     * @param position The position in this adapter
     * @param data     the data at this position
     * @return return a int variable viewtype
     */
    public int getItemViewType(int position, T data) {
        return 0;
    }

    /**
     * @param viewType According to viewType return item layout id
     */
    public abstract int layoutResId(int viewType);

    /**
     * ViewHolder实例化
     */
    public abstract VH holderInstance(View itemView, int viewType);


    /**
     * 进行视图填充
     */
    public abstract void fillView(VH holder, T data, int position);


    /**
     * 关于数据源的增加
     */
    public void append(T item) {
        itemData.add(item);
        notifyItemInserted(itemData.size() - 1);
    }

    public void appendAll(@NonNull List<T> item) {
        itemData.addAll(item);
        notifyItemRangeInserted(itemData.size() - 1, item.size());
    }

    public void appendHeader(T item) {
        itemData.add(0, item);
        notifyItemInserted(0);
    }

    public void appendAt(T item, int poistion) {
        if (poistion >= itemData.size()) {
            return;
        }
        itemData.add(poistion, item);
        notifyItemInserted(poistion);
    }

    /**
     * 关于数据源的修改
     */
    public void repalceHeader(T item) {
        try {
            itemData.set(0, item);
            notifyItemChanged(0);
        } catch (Exception e) {
            Log.e(TAG, "repalceHeader: " + e.getMessage());
        }

    }

    public void repalceFooter(T item) {
        try {
            itemData.set(itemData.size() - 1, item);
            notifyItemChanged(itemData.size() - 1);
        } catch (Exception e) {
            Log.e(TAG, "repalceFooter: " + e.getMessage());
        }

    }

    public void replace(T item, int poistion) {
        if (poistion >= itemData.size()) {
            return;
        }
        itemData.set(poistion, item);
        notifyItemChanged(poistion);
    }

    /**
     * 关于数据源的删除
     */
    public void delete(int poistion) {
        if (poistion >= itemData.size()) {
            return;
        }
        itemData.remove(poistion);
        notifyItemRemoved(poistion);
    }

    public void delete(T item) {
        try {
            int index = getItemIndex(item);
            itemData.remove(item);
            notifyItemRemoved(index);
        } catch (Exception e) {
            Log.e(TAG, "delete: " + e.getMessage());
        }
    }

    public void deleteHeader() {
        try {
            itemData.remove(0);
            notifyItemRemoved(0);
        } catch (Exception e) {
            Log.e(TAG, "deleteHeader: " + e.getMessage());
        }
    }

    public void deleteFooter() {
        try {
            itemData.remove(itemData.size() - 1);
            notifyItemRemoved(itemData.size());
        } catch (Exception e) {
            Log.e(TAG, "deleteFooter: " + e.getMessage());
        }
    }

    public void clearAll() {
        itemData.clear();
        notifyDataSetChanged();
    }

    /**
     * 关于数据源的查找
     */
    public T getTheItem(int index) {
        if (index >= itemData.size()) {
            return null;
        }
        return itemData.get(index);
    }

    public T getItemHeader() {
        if (itemData.size() == 0) {
            return null;
        }
        return itemData.get(0);
    }

    public T getItemFooter() {
        if (itemData.size() == 0) {
            return null;
        }
        return itemData.get(itemData.size() - 1);
    }

    public int getItemIndex(T item) {
        return itemData.indexOf(item);
    }

    public List<T> getItemData() {
        return itemData;
    }
}