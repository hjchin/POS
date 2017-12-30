package pos.com.pos.item.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by HJ Chin on 29/12/2017.
 */

@Entity
public class Item {
    public int albumId;

    @PrimaryKey
    public int id;
    public String title;
    public String url;
    public String thumbnailUrl;
    public int price;
}
