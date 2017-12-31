package pos.com.pos;

import org.junit.Test;

import pos.com.pos.discount.model.DiscountItem;
import pos.com.pos.discount.view.DiscountListFragment;
import pos.com.pos.item.model.Item;
import pos.com.pos.shoppingCart.view.model.ShoppingCartItem;

import static org.junit.Assert.assertEquals;

/**
 * Created by HJ Chin on 31/12/2017.
 */

public class ShoppingCartModelTest {

    private Item skuItem;

    public ShoppingCartModelTest(){
        skuItem = new Item();
        skuItem.title = "product A";
        skuItem.price = 100;
    }

    @Test
    public void testNoDiscount(){

        DiscountItem discountItem0 = DiscountListFragment.discountItemArrayList.get(0);

        ShoppingCartItem cartItem1 = new ShoppingCartItem(
                skuItem,
                discountItem0,
                1);

        assertEquals("100.00",cartItem1.totalString());
        assertEquals("0.00",cartItem1.discountTotalString());

        ShoppingCartItem cartItem2 = new ShoppingCartItem(
                skuItem,
                discountItem0,
                100);

        assertEquals("10000.00",cartItem2.totalString());
        assertEquals("0.00",cartItem2.discountTotalString());
    }

    @Test
    public void test10Discount(){
        DiscountItem discountItem10 = DiscountListFragment.discountItemArrayList.get(1);

        ShoppingCartItem cartItem3 = new ShoppingCartItem(
                skuItem,
                discountItem10,
                1);

        assertEquals("90.00",cartItem3.totalString());
        assertEquals("10.00",cartItem3.discountTotalString());

        ShoppingCartItem cartItem4 = new ShoppingCartItem(
                skuItem,
                discountItem10,
                20);

        assertEquals("1800.00",cartItem4.totalString());
        assertEquals("200.00",cartItem4.discountTotalString());
    }

    @Test
    public void test355Discount(){
        DiscountItem discountItem355 = DiscountListFragment.discountItemArrayList.get(2);

        ShoppingCartItem cartItem1 = new ShoppingCartItem(
                skuItem,
                discountItem355,
                1);

        assertEquals("64.50",cartItem1.totalString());
        assertEquals("35.50",cartItem1.discountTotalString());

        ShoppingCartItem cartItem2 = new ShoppingCartItem(
                skuItem,
                discountItem355,
                12);

        assertEquals("774.00",cartItem2.totalString());
        assertEquals("426.00",cartItem2.discountTotalString());
    }

    @Test
    public void test100Discount(){
        DiscountItem discountItem100 = DiscountListFragment.discountItemArrayList.get(4);

        ShoppingCartItem cartItem1 = new ShoppingCartItem(
                skuItem,
                discountItem100,
                1);

        assertEquals("0.00",cartItem1.totalString());
        assertEquals("100.00",cartItem1.discountTotalString());

        ShoppingCartItem cartItem2 = new ShoppingCartItem(
                skuItem,
                discountItem100,
                12);

        assertEquals("0.00",cartItem1.totalString());
        assertEquals("100.00",cartItem1.discountTotalString());
    }
}
