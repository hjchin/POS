package pos.com.pos.allItems.view;

import pos.com.pos.allItems.model.SKUItem;

/**
 * Created by HJ Chin on 29/12/2017.
 */

public interface ItemListView {

    void showSKUItems(SKUItem[] items);
    void showError(Throwable throwable);
}
