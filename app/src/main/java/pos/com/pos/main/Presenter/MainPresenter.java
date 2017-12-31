package pos.com.pos.main.Presenter;

import pos.com.pos.item.model.Item;
import pos.com.pos.main.view.MainView;

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

    public void addItemToShoppingCart(Item skuItem){
        view.refreshShoppingCart();

    }
}
