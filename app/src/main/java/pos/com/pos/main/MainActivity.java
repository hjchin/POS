package pos.com.pos.main;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pos.com.pos.R;
import pos.com.pos.databinding.ActivityMainBinding;
import pos.com.pos.library.LibraryFragment;
import pos.com.pos.library.model.LibraryItem;

public class MainActivity extends AppCompatActivity implements
        MainView,
        LibraryFragment.Callback {


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

    }

    @Override
    public void showItemListFragment() {

    }

    @Override
    public void onItemClick(LibraryItem item) {

    }
}
