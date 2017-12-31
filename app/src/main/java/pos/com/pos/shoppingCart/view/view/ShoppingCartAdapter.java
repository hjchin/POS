package pos.com.pos.shoppingCart.view.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pos.com.pos.R;
import pos.com.pos.databinding.FragmentShoppingCartItemBinding;
import pos.com.pos.shoppingCart.view.model.ShoppingCartItem;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private final List<ShoppingCartItem> values;
    private final ShoppingCartFragment.Callback callback;

    public ShoppingCartAdapter(List<ShoppingCartItem> items, ShoppingCartFragment.Callback listener) {
        values = items;
        callback = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentShoppingCartItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.fragment_shopping_cart_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = values.get(position);
        holder.binding.name.setText(values.get(position).getName());
        holder.binding.quantity.setText(values.get(position).getQuantity());

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does nothing
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ShoppingCartItem item;
        public final FragmentShoppingCartItemBinding binding;

        ViewHolder(FragmentShoppingCartItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
