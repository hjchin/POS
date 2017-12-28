package pos.com.pos.library.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pos.com.pos.R;
import pos.com.pos.data.Item;
import pos.com.pos.databinding.FragmentItemBinding;

import java.util.List;


public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    private final List<Item> values;
    private final LibraryFragment.Callback callback;

    LibraryAdapter(List<Item> items, LibraryFragment.Callback listener) {
        values = items;
        callback = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.item = values.get(position);
        holder.binding.id.setText(values.get(position).id);
        holder.binding.name.setText(values.get(position).name);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(holder.item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FragmentItemBinding binding;
        Item item;

         ViewHolder(FragmentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
