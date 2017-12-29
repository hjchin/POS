package pos.com.pos.allItems.model;

import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pos.com.pos.data.HttpClientInterface;

/**
 * Created by HJ Chin on 29/12/2017.
 */

public class ItemListModel {

    public interface GetItemListCallback{
            void onResponse(SKUItem[] sku);
            void onErrorResponse(Throwable throwable);
    }

    private HttpClientInterface httpClient;
    private SKUItem[] skuItems;

    public ItemListModel(HttpClientInterface httpClient){
        this.httpClient = httpClient;
    }

    public SKUItem[] getSKUItems(){
        return skuItems;
    }

    public void getItems(final GetItemListCallback callback){

        httpClient.getPOSApi().getSKUItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<SKUItem[]>() {
                @Override
                public void accept(SKUItem[] items) throws Exception {
                    skuItems = items;
                    assignPrice();
                    callback.onResponse(skuItems);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    callback.onErrorResponse(throwable);
                }
            });
    }

    private void assignPrice() {
        if(skuItems != null){
            for(SKUItem item: skuItems){
                item.price = item.id * new Random().nextInt(99 - 10 + 1) + 10;
            }
        }
    }
}
