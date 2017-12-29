package pos.com.pos.allItems.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pos.com.pos.R;
import pos.com.pos.allItems.model.AllItemsItem;
import pos.com.pos.databinding.FragmentItemBinding;

import java.util.List;

public class AllItemsRecyclerViewAdapter extends RecyclerView.Adapter<AllItemsRecyclerViewAdapter.ViewHolder> {

    private final List<AllItemsItem> values;
    private final ItemListFragment.Callback callback;

    public AllItemsRecyclerViewAdapter(List<AllItemsItem> items, ItemListFragment.Callback listener) {
        values = items;
        callback = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.fragment_item, parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = values.get(position);
        holder.binding.id.setText(values.get(position).id);
        holder.binding.name.setText(values.get(position).name);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != callback) {
                    callback.onItemClick(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AllItemsItem item;
        FragmentItemBinding binding;

        ViewHolder(FragmentItemBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
