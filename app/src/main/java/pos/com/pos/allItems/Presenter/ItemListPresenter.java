package pos.com.pos.allItems.Presenter;

import android.support.test.espresso.idling.CountingIdlingResource;

import pos.com.pos.allItems.model.ItemListModel;
import pos.com.pos.allItems.model.SKUItem;
import pos.com.pos.allItems.view.ItemListView;

/**
 * Created by HJ Chin on 29/12/2017.
 */

public class ItemListPresenter {

    private final ItemListModel model;
    private final ItemListView view;
    private final CountingIdlingResource countingIdlingResource;

    public ItemListPresenter(CountingIdlingResource countingIdlingResource, ItemListModel model, ItemListView view){
        this.countingIdlingResource = countingIdlingResource;
        this.model = model;
        this.view = view;
        loadItems();
    }

    public void loadItems(){
        countingIdlingResource.increment();
        model.getItems(new ItemListModel.GetItemListCallback() {
            @Override
            public void onResponse(SKUItem[] sku) {
                view.showSKUItems(sku);
                countingIdlingResource.decrement();
            }

            @Override
            public void onErrorResponse(Throwable throwable) {
                view.showError(throwable);
                countingIdlingResource.decrement();
            }
        });
    }
}
