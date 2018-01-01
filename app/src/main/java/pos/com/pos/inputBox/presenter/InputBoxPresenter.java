package pos.com.pos.inputBox.presenter;

import pos.com.pos.discount.model.DiscountItem;
import pos.com.pos.inputBox.view.InputBoxFragmentView;
import pos.com.pos.item.model.Item;
import pos.com.pos.shoppingCart.view.ShoppingCart;
import pos.com.pos.shoppingCart.view.model.ShoppingCartItem;

/**
 * Created by HJ Chin on 1/1/2018.
 */

public class InputBoxPresenter {

    private ShoppingCartItem item;
    private InputBoxFragmentView view;
    private ShoppingCart shoppingCart;
    private ShoppingCartItem itemFoundinShoppingCart;

    public InputBoxPresenter(
            ShoppingCart shoppingCart,
            ShoppingCartItem item,
            InputBoxFragmentView view){
        this.shoppingCart = shoppingCart;
        this.item = item;
        this.view = view;
        view.initView(item);
    }

    public void setQuantity(int quantity){
        if(quantity > -1 && quantity < 1001){
            item.setQuantity(quantity);
            view.refreshQuantity(quantity);
        }else{
            throw new IllegalArgumentException("quantity must be between 0 and 1000");
        }
    }

    public void decreaseQuantity(){

        try{

            int quantity = item.getQuantity();
            if( quantity > 0){
                --quantity;
            }

            setQuantity(quantity);
        }catch (Exception e){
        }
    }

    public void increaseQuantity(){
        try{

            int quantity = item.getQuantity();
            if( quantity < 1000){
                ++quantity;
            }

            setQuantity(quantity);
        }catch (Exception e){

        }
    }

    public void setDiscount(DiscountItem discountItem){

        if(item.getDiscount().compareTo(discountItem) == 0){
            return;
        }

        if(shoppingCart.has(item)) {
            itemFoundinShoppingCart = item;
        }

        item = new ShoppingCartItem(
            new Item(item.getItem().id,
                    item.getItem().title,
                    item.getItem().url,
                    item.getItem().thumbnailUrl,
                    item.getItem().price),
            discountItem,
            item.getQuantity()
        );
    }

    public void cancel(){
        view.finish();
    }

    public void save(){

        //existing item discount change
        if(itemFoundinShoppingCart != null){
            if(item.getQuantity() > 0) {
                shoppingCart.addItem(item);
                shoppingCart.removeItem(itemFoundinShoppingCart);
                itemFoundinShoppingCart = null;
            }else{
                //do nothing
                return;
            }
        }
        //existing item quantity change
        else if(shoppingCart.has(item)){
            if(item.getQuantity() > 0)
                shoppingCart.updateItem(item);
            else
                shoppingCart.removeItem(item);
        }
        //new item
        else if(item.getQuantity() > 0){
                shoppingCart.addItem(item);
        }
        //new item with 0 quantity
        else{
            //do nothing, keep the view visible
            return;
        }

        view.finish();
    }
}
