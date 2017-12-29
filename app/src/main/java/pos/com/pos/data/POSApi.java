package pos.com.pos.data;

import io.reactivex.Observable;
import pos.com.pos.allItems.model.SKUItem;
import retrofit2.http.GET;

/**
 * Created by HJ Chin on 29/12/2017.
 */

public interface POSApi {

    @GET("photos")
    Observable<SKUItem[]> getSKUItems();

}
