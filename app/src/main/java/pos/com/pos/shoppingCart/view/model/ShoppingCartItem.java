package pos.com.pos.shoppingCart.view.model;

import java.text.DecimalFormat;

import pos.com.pos.discount.model.DiscountItem;
import pos.com.pos.item.model.Item;

/**
 * Created by HJ Chin on 31/12/2017.
 */

public class ShoppingCartItem {

    private Item item;
    private DiscountItem discountItem;
    private double discountTotal;
    private int quantity;
    private Double subTotal;

    public ShoppingCartItem(Item skuItem, DiscountItem discountItem, int quantity){
        this.item = skuItem;
        this.discountItem = discountItem;
        this.quantity = quantity;
        calTotal();
    }

    private void calTotal(){
        //discount is in form of XX%

        if(Double.compare(discountItem.discount,0.0) == 0){
            subTotal = Math.round(item.price * quantity*100)/100.0;
            discountTotal = 0.0;
        }else if(Double.compare(discountItem.discount,100.0) == 0){
            subTotal = 0.0;
            discountTotal = Math.round(item.price * discountItem.discount * quantity)/100.0;
        }else{
            double total = Math.round(item.price * quantity * 100.0)/100.0;
            subTotal = Math.round(item.price * (100-discountItem.discount) * quantity)/100.0;
            discountTotal = total - subTotal;
        }
    }

    public double discountTotal(){
        return discountTotal;
    }

    public double total(){
         if(subTotal == null){
             calTotal();
         }

         return subTotal;
    }

    public String totalString(){
        DecimalFormat format = new DecimalFormat("#0.00");
        return format.format(total());
    }

    public String discountTotalString(){
        DecimalFormat format = new DecimalFormat("#0.00");
        return format.format(discountTotal());
    }

    public String getName(){
        return item.title;
    }

    public String getQuantity(){
        return String.valueOf(quantity);
    }


}
