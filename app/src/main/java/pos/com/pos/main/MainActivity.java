package pos.com.pos.main;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pos.com.pos.R;
import pos.com.pos.allItems.model.AllItemsItem;
import pos.com.pos.allItems.view.AllItemsFragment;
import pos.com.pos.data.Item;
import pos.com.pos.databinding.ActivityMainBinding;
import pos.com.pos.discount.model.DiscountItem;
import pos.com.pos.discount.view.DiscountFragment;
import pos.com.pos.library.view.LibraryFragment;

public class MainActivity extends AppCompatActivity implements
        MainView,
        LibraryFragment.Callback,
        DiscountFragment.Callback,
        AllItemsFragment.Callback{

    private ActivityMainBinding binding;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this);
    }

    @Override
    public void showLibraryFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.leftFrame,LibraryFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void showDiscountFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.leftFrame,DiscountFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void showItemListFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.leftFrame,AllItemsFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void onItemClick(Item item) {
        if(item instanceof DiscountItem){
            showDiscountFragment();
        }

        if(item instanceof AllItemsItem){
            showItemListFragment();
        }
    }

    @Override
    public void onItemClick(DiscountItem item) {

    }

    @Override
    public void onItemClick(AllItemsItem item) {

    }
}
