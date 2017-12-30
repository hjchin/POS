package pos.com.pos.discount.model;

/**
 * Created by HJ Chin on 30/12/2017.
 */

public class Item {
    public String id;
    public String name;
    public double discount;

    public Item(String id, String name, double discount){
        this.id = id;
        this.name = name;
        this.discount = discount;
    }
}
