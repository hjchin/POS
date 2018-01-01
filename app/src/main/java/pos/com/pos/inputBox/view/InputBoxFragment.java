package pos.com.pos.inputBox.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import pos.com.pos.R;
import pos.com.pos.databinding.InputBoxBinding;
import pos.com.pos.inputBox.presenter.InputBoxPresenter;
import pos.com.pos.shoppingCart.view.ShoppingCart;
import pos.com.pos.shoppingCart.view.model.ShoppingCartItem;
import pos.com.pos.util.Util;

/**
 * Created by HJ Chin on 1/1/2018.
 */

public class InputBoxFragment extends DialogFragment implements InputBoxFragmentView {

    public interface Callback{
        void onCancel();
        void onSave(ShoppingCartItem item);
    }

    private Callback callback;
    private InputBoxBinding binding;
    private InputBoxPresenter presenter;

    private static String SHOPPING_CART_ITEM = "shoppingCartItem";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (Callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement InputBoxFragment callback");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        binding = DataBindingUtil.inflate(inflater, R.layout.input_box,null,false);

        final ShoppingCartItem item = getArguments().getParcelable(SHOPPING_CART_ITEM);

        if(item == null){
            throw new NullPointerException("shopping cart item is null");
        }

        builder.setView(binding.getRoot());

        presenter = new InputBoxPresenter(ShoppingCart.getInstance(), item,this);
        return builder.create();
    }

    @Override
    public void initView(ShoppingCartItem item) {
        binding.label.setText(item.getItem().title+" "+ Util.formatMoney(item.getItem().price));

        binding.btnCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                callback.onCancel();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                presenter.save();
            }
        });

        binding.btnDecrease.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                presenter.decreaseQuantity();
            }
        });


    }

    @Override
    public void refreshQuantity(int quantity) {
        binding.quantity.setText(String.valueOf(quantity));
    }

    @Override
    public void finish() {

    }
}
