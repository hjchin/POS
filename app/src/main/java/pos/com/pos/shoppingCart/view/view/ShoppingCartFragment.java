package pos.com.pos.shoppingCart.view.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pos.com.pos.R;
import pos.com.pos.databinding.FragmentShoppingCartBinding;
import pos.com.pos.shoppingCart.view.model.ShoppingCartItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callback}
 * interface.
 */
public class ShoppingCartFragment extends Fragment {

    private Callback callback;

    public ShoppingCartFragment() {
    }

    public static ShoppingCartFragment newInstance() {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentShoppingCartBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shopping_cart,container,false);

        binding.frameName.setText(getActivity().getString(R.string.shopping_cart));
        binding.shoppingCartList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        binding.shoppingCartList.setAdapter(new ShoppingCartAdapter(new ArrayList<ShoppingCartItem>(), callback));
        binding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do nothing
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public interface Callback {
        void onItemClick(ShoppingCartItem item);
    }
}
