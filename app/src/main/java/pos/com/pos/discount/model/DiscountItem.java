package pos.com.pos.discount.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by HJ Chin on 30/12/2017.
 */

public class DiscountItem implements Comparable<DiscountItem>{
    public String id;
    public String name;
    public double discount;

    public DiscountItem(String id, String name, double discount){
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    @Override
    public int compareTo(@NonNull DiscountItem o) {
        return id.compareTo(o.id);
    }

    public static final ArrayList<DiscountItem> discountItemArrayList = new ArrayList<>();

    public static final DiscountItem discount0 = new DiscountItem("discountA","Discount A",0);
    public static final DiscountItem discount10 = new DiscountItem("discountB","Discount B",10);
    public static final DiscountItem discount355 = new DiscountItem("discount3","Discount C",35.5);
    public static final DiscountItem discount50 = new DiscountItem("discount4","Discount D",50);
    public static final DiscountItem discount100 = new DiscountItem("discount5","Discount E",100);

    static{
        discountItemArrayList.add(discount0);
        discountItemArrayList.add(discount10);
        discountItemArrayList.add(discount355);
        discountItemArrayList.add(discount50);
        discountItemArrayList.add(discount100);
    }


}
