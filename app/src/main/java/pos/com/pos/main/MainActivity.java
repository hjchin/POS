package pos.com.pos.main;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pos.com.pos.R;
import pos.com.pos.item.model.Item;
import pos.com.pos.library.view.ItemListItem;
import pos.com.pos.item.view.ItemListFragment;
import pos.com.pos.databinding.ActivityMainBinding;
import pos.com.pos.library.view.DiscountItem;
import pos.com.pos.discount.view.DiscountListFragment;
import pos.com.pos.library.view.LibraryFragment;

public class MainActivity extends AppCompatActivity implements
        MainView,
        LibraryFragment.Callback,
        DiscountListFragment.Callback,
        ItemListFragment.Callback{

    private ActivityMainBinding binding;
    private MainPresenter presenter;
    private static CountingIdlingResource countingIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countingIdlingResource = new CountingIdlingResource("counter");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this);
    }

    @Override
    public void showLibraryFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.leftFrame,LibraryFragment.newInstance(),"library");
        transaction.commit();
    }

    @Override
    public void showDiscountFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.leftFrame, DiscountListFragment.newInstance(),"discount");
        transaction.addToBackStack("discount");
        transaction.commit();
    }

    @Override
    public void showItemListFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.leftFrame, ItemListFragment.newInstance(countingIdlingResource),"itemList");
        transaction.addToBackStack("itemList");
        transaction.commit();
    }

    @Override
    public void onItemClick(pos.com.pos.library.view.Item item) {
        if(item instanceof DiscountItem){
            showDiscountFragment();
        }

        if(item instanceof ItemListItem){
            showItemListFragment();
        }
    }

    @Override
    public void onItemClick(DiscountItem item) {

    }

    @Override
    public void onItemClick(Item item) {

    }

    public void backStack(){
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        });
    }

    public CountingIdlingResource getIdlingCounter(){
        return countingIdlingResource;
    }

}
