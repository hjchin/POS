package pos.com.pos.main.Presenter;

import pos.com.pos.discount.model.DiscountItem;
import pos.com.pos.item.model.Item;
import pos.com.pos.main.view.MainView;
import pos.com.pos.shoppingCart.ShoppingCart;
import pos.com.pos.shoppingCart.model.ShoppingCartItem;

/**
 * Created by HJ Chin on 28/12/2017.
 */

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView view){
        this.view = view;
        loadLibraryFragment();
        loadShoppingCartFragment();
    }

    public void loadLibraryFragment(){
        view.showLibraryFragment();
    }

    void loadDiscountFragement(){

    }

    void loadItemListFragement(){

    }

    public void loadShoppingCartFragment(){
        view.showShoppingCartFragment();
    }

    public void addItemToShoppingCart(Item skuItem, DiscountItem discountItem, int quantity){
        ShoppingCart.getInstance().addItem(new ShoppingCartItem(skuItem,discountItem,quantity));
        view.refreshShoppingCart();
    }
}
