package pos.com.pos.shoppingCart.view;

import java.util.LinkedHashMap;
import java.util.Map;

import pos.com.pos.shoppingCart.view.model.ShoppingCartItem;
import pos.com.pos.util.Util;

/**
 * Created by HJ Chin on 31/12/2017.
 */

public class ShoppingCart {

    private static ShoppingCart cart;

    private Map<String,ShoppingCartItem> shoppingList;

    private double subTotal;
    private double discount;
    private double charge;

    private ShoppingCart(){
        shoppingList = new LinkedHashMap<>();
    }

    public static ShoppingCart getInstance(){
        if(cart == null){
            cart = new ShoppingCart();
        }

        return cart;
    }

    public void addItem(ShoppingCartItem scItem){
        ShoppingCartItem retrieved = shoppingList.get(getId(scItem));

        if(retrieved == null){
            shoppingList.put(getId(scItem),scItem);
        }else{
            int newTotal = retrieved.getQuantity()+scItem.getQuantity();
            ShoppingCartItem mergeScItem = new ShoppingCartItem(scItem.getItem(),scItem.getDiscount(),newTotal);
            shoppingList.put(getId(mergeScItem),mergeScItem);
        }

        calTotal();
    }

    public void updateItem(ShoppingCartItem scItem){

        if(shoppingList.get(getId(scItem)) == null){
            throw new IllegalArgumentException("Invalid shopping cart item");
        }

        shoppingList.put(getId(scItem),scItem);
        calTotal();
    }

    public void removeItem(ShoppingCartItem scItem){

        if(shoppingList.get(getId(scItem)) == null){
            throw new IllegalArgumentException("Invalid shopping cart item");
        }

        shoppingList.remove(getId(scItem));
        calTotal();
    }

    public int getItemCount(){
        return shoppingList.size();
    }

    public void emptyCart(){
        shoppingList.clear();
        calTotal();
    }

    public boolean has(ShoppingCartItem scItem){
        if(shoppingList.get(getId(scItem)) == null){
            return false;
        }
        return true;
    }

    private String getId(ShoppingCartItem item){
        return String.valueOf(item.getItem().id)+"_"+item.getDiscount().id;
    }

    private void calTotal(){
        subTotal = 0;
        discount = 0;
        charge = 0;

        for(Map.Entry<String, ShoppingCartItem> entry: shoppingList.entrySet()){
            subTotal += entry.getValue().totalBeforeDiscount();
            discount += entry.getValue().totalDiscount();
            charge += entry.getValue().totalAfterDiscount();
        }
    }


    public String getSubTotalString(){
        return Util.formatDisplay(subTotal);
    }

    public String getDiscountString(){
        return Util.formatDisplay(discount);
    }

    public String getChargeString(){
        return Util.formatDisplay(charge);
    }
}
