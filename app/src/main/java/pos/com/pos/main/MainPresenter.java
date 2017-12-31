package pos.com.pos.main;

/**
 * Created by HJ Chin on 28/12/2017.
 */

public class MainPresenter {

    private MainView view;

    MainPresenter(MainView view){
        this.view = view;
        loadLibraryFragment();
        loadShoppingCartFragment();
    }

    void loadLibraryFragment(){
        view.showLibraryFragment();
    }

    void loadDiscountFragement(){

    }

    void loadItemListFragement(){

    }

    void loadShoppingCartFragment(){
        view.showShoppingCartFragment();
    }


}
