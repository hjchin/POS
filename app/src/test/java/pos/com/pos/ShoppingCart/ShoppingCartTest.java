package pos.com.pos.ShoppingCart;

import org.junit.Before;
import org.junit.Test;

import pos.com.pos.discount.model.DiscountItem;
import pos.com.pos.item.model.Item;
import pos.com.pos.shoppingCart.ShoppingCart;
import pos.com.pos.shoppingCart.model.ShoppingCartItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by HJ Chin on 31/12/2017.
 */

public class ShoppingCartTest {

    @Before
    public void setup(){
        ShoppingCart.getInstance().emptyCart();
    }

    @Test
    public void testDefaultEmptyCart(){
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        assertEquals("0.00",shoppingCart.getSubTotalString());
        assertEquals("0.00",shoppingCart.getDiscountString());
        assertEquals("0.00",shoppingCart.getChargeString());
        assertEquals(0,shoppingCart.getItemCount());
    }

    @Test
    public void testAddItem(){

        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        Item skuItem = new Item(1,
                "product A",
                "url",
                "thumbUrl",
                100);

        /*
        * Add single Item
        * */
        ShoppingCartItem item = new ShoppingCartItem(skuItem,
                DiscountItem.discountA,
                1);
        shoppingCart.addItem(item);
        assertEquals("100.00",shoppingCart.getSubTotalString());
        assertEquals("0.00",shoppingCart.getDiscountString());
        assertEquals("100.00",shoppingCart.getChargeString());
        assertEquals(1,shoppingCart.getItemCount());

        /*
        * Add another same skuItem with different discount
        * */
        ShoppingCartItem item2 = new ShoppingCartItem(skuItem,
                DiscountItem.discountB,
                2);

        shoppingCart.addItem(item2);
        assertEquals("300.00",shoppingCart.getSubTotalString());
        assertEquals("20.00",shoppingCart.getDiscountString());
        assertEquals("280.00",shoppingCart.getChargeString());
        assertEquals(2,shoppingCart.getItemCount());

        /*
        * Add another same skuItem with same previous discount
        * */
        ShoppingCartItem item3 = new ShoppingCartItem(skuItem,
                DiscountItem.discountB,
                1);

        shoppingCart.addItem(item3);
        assertEquals("400.00",shoppingCart.getSubTotalString());
        assertEquals("30.00",shoppingCart.getDiscountString());
        assertEquals("370.00",shoppingCart.getChargeString());
        assertEquals(2,shoppingCart.getItemCount());

        Item skuItem2 = new Item(2,
                "product B",
                "url",
                "thumbUrl",
                200);

        /*
        * Add different skuItem with discountC
        * */
        ShoppingCartItem item4 = new ShoppingCartItem(skuItem2,
                DiscountItem.discountC,
                2);

        shoppingCart.addItem(item4);
        assertEquals("800.00",shoppingCart.getSubTotalString());
        assertEquals("172.00",shoppingCart.getDiscountString());
        assertEquals("628.00",shoppingCart.getChargeString());
        assertEquals(3,shoppingCart.getItemCount());

        Item skuItem3 = new Item(3,
                "product C",
                "url",
                "thumbUrl",
                50);
        /*
            Add different skuItem with discountE
         */
        ShoppingCartItem item5 = new ShoppingCartItem(skuItem3,
                DiscountItem.discountE,
                1);
        shoppingCart.addItem(item5);
        assertEquals("850.00",shoppingCart.getSubTotalString());
        assertEquals("222.00",shoppingCart.getDiscountString());
        assertEquals("628.00",shoppingCart.getChargeString());
        assertEquals(4,shoppingCart.getItemCount());
    }

    @Test
    public void testUpdateItem(){
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        Item skuItem = new Item(1,
                "product A",
                "url",
                "thumbUrl",
                100);

        ShoppingCartItem item = new ShoppingCartItem(skuItem,
                DiscountItem.discountA,
                1);
        shoppingCart.addItem(item);
        assertEquals("100.00",shoppingCart.getSubTotalString());
        assertEquals("0.00",shoppingCart.getDiscountString());
        assertEquals("100.00",shoppingCart.getChargeString());
        assertEquals(1,shoppingCart.getItemCount());

        /*
            Update with new shopping cart item creation
         */
        ShoppingCartItem updateItem = new ShoppingCartItem(skuItem,
                DiscountItem.discountA,
                5);

        shoppingCart.updateItem(updateItem);
        assertEquals("500.00",shoppingCart.getSubTotalString());
        assertEquals("0.00",shoppingCart.getDiscountString());
        assertEquals("500.00",shoppingCart.getChargeString());
        assertEquals(1,shoppingCart.getItemCount());

        /*
            update with invalid item
         */
        ShoppingCartItem updateItem2 = new ShoppingCartItem(skuItem,
                DiscountItem.discountB,
                2);
        try{
            shoppingCart.updateItem(updateItem2);
            assertTrue("successfully update invalid item",false);
        }catch(IllegalArgumentException ex){
            assertTrue("invalid item to update",true);
        }
    }

    @Test
    public void testRemoveItem(){
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        Item skuItem = new Item(1,
                "product A",
                "url",
                "thumbUrl",
                100);

        ShoppingCartItem item = new ShoppingCartItem(skuItem,
                DiscountItem.discountA,
                1);
        shoppingCart.addItem(item);

        ShoppingCartItem item2 = new ShoppingCartItem(skuItem,
                DiscountItem.discountB,
                10);
        shoppingCart.addItem(item2);

        assertEquals("1100.00",shoppingCart.getSubTotalString());
        assertEquals("100.00",shoppingCart.getDiscountString());
        assertEquals("1000.00",shoppingCart.getChargeString());
        assertEquals(2,shoppingCart.getItemCount());

        shoppingCart.removeItem(item);
        assertEquals("1000.00",shoppingCart.getSubTotalString());
        assertEquals("100.00",shoppingCart.getDiscountString());
        assertEquals("900.00",shoppingCart.getChargeString());
        assertEquals(1,shoppingCart.getItemCount());

        ShoppingCartItem item3 = new ShoppingCartItem(skuItem,
                DiscountItem.discountD,
                10);
        try{
            shoppingCart.removeItem(item3);
            assertTrue("successfully remove invalid item",false);
        }catch (IllegalArgumentException ex){
            assertTrue("invalid item to remove",true);
        }
    }

    @Test
    public void testEmptyCart(){
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        Item skuItem = new Item(1,
                "product A",
                "url",
                "thumbUrl",
                100);

        ShoppingCartItem item = new ShoppingCartItem(skuItem,
                DiscountItem.discountA,
                1);
        shoppingCart.addItem(item);

        ShoppingCartItem item2 = new ShoppingCartItem(skuItem,
                DiscountItem.discountB,
                10);
        shoppingCart.addItem(item2);

        assertEquals("1100.00",shoppingCart.getSubTotalString());
        assertEquals("100.00",shoppingCart.getDiscountString());
        assertEquals("1000.00",shoppingCart.getChargeString());
        assertEquals(2,shoppingCart.getItemCount());

        shoppingCart.emptyCart();

        assertEquals("0.00",shoppingCart.getSubTotalString());
        assertEquals("0.00",shoppingCart.getDiscountString());
        assertEquals("0.00",shoppingCart.getChargeString());
        assertEquals(0,shoppingCart.getItemCount());
    }

    @Test
    public void testHasItem(){
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        Item skuItem = new Item(1,
                "product A",
                "url",
                "thumbUrl",
                100);

        ShoppingCartItem item = new ShoppingCartItem(skuItem,
                DiscountItem.discountA,
                1);
        shoppingCart.addItem(item);

        ShoppingCartItem item2 = new ShoppingCartItem(skuItem,
                DiscountItem.discountB,
                10);

        assertEquals(true,shoppingCart.has(item));
        assertEquals(false,shoppingCart.has(item2));
    }
}
