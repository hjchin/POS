package pos.com.pos.inputBox.view;

import pos.com.pos.shoppingCart.view.model.ShoppingCartItem;

/**
 * Created by HJ Chin on 1/1/2018.
 */

public interface InputBoxFragmentView {
    void refreshQuantity(int quantity);
    void initView(ShoppingCartItem item);
    void finish();
}
