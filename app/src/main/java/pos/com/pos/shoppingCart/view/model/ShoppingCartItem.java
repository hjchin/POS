package pos.com.pos.shoppingCart.view.model;

import java.text.DecimalFormat;

import pos.com.pos.discount.model.DiscountItem;
import pos.com.pos.item.model.Item;
import pos.com.pos.util.Util;

/**
 * Created by HJ Chin on 31/12/2017.
 */

public class ShoppingCartItem {

    private Item item;
    private DiscountItem discountItem;
    private double totalDiscount;
    private int quantity;
    private Double totalAfterDiscount;
    private Double totalBeforeDiscount;

    public ShoppingCartItem(Item skuItem, DiscountItem discountItem, int quantity){
        this.item = skuItem;
        this.discountItem = discountItem;
        this.quantity = quantity;
        calTotal();
    }

    private void calTotal(){
        //discount is in form of XX%

        if(Double.compare(discountItem.discount,0.0) == 0){
            totalBeforeDiscount = Math.round(item.price * quantity*100)/100.0;
            totalAfterDiscount = totalBeforeDiscount;
            totalDiscount = 0.0;
        }else if(Double.compare(discountItem.discount,100.0) == 0){
            totalAfterDiscount = 0.0;
            totalDiscount = Math.round(item.price * discountItem.discount * quantity)/100.0;
            totalBeforeDiscount = totalDiscount;
        }else{
            totalBeforeDiscount = Math.round(item.price * quantity * 100.0)/100.0;
            totalAfterDiscount = Math.round(item.price * (100-discountItem.discount) * quantity)/100.0;
            totalDiscount = totalBeforeDiscount - totalAfterDiscount;
        }
    }

    public double totalDiscount(){
        return totalDiscount;
    }

    public double totalAfterDiscount(){
         if(totalAfterDiscount == null){
             calTotal();
         }

         return totalAfterDiscount;
    }


    public double totalBeforeDiscount(){
        return totalBeforeDiscount;
    }

    public String totalAfterDiscountString(){
        return format(totalAfterDiscount);
    }

    public String totalDiscountString(){
        return format(totalDiscount);
    }

    public String totalBeforeDiscountString(){
        return format(totalBeforeDiscount);
    }

    private String format(double value){
        return Util.formatDisplay(value);
    }

    public String getItemName(){
        return item.title;
    }

    public String getQuantityString(){
        return String.valueOf(quantity);
    }

    public int getQuantity(){
        return quantity;
    }

    public DiscountItem getDiscount(){
        return discountItem;
    }

    public Item getItem(){
        return item;
    }



}
