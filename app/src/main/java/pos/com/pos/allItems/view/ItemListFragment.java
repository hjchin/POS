package pos.com.pos.allItems.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import pos.com.pos.R;
import pos.com.pos.allItems.Presenter.ItemListPresenter;
import pos.com.pos.allItems.model.ItemListModel;
import pos.com.pos.allItems.model.SKUItem;
import pos.com.pos.data.HttpClient;
import pos.com.pos.databinding.FragmentItemListBinding;

public class ItemListFragment extends Fragment implements ItemListView{

    private ItemListPresenter presenter;

    private Callback callback;
    private ItemListAdapter adapter;
    private FragmentItemListBinding binding;
    private static CountingIdlingResource countingIdlingResource = new CountingIdlingResource("counter");

    public ItemListFragment() {
        presenter = new ItemListPresenter(countingIdlingResource, new ItemListModel(HttpClient.getInstance()), this);
    }

    public static ItemListFragment newInstance() {
        ItemListFragment fragment = new ItemListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_item_list,container,false);
        binding.frameName.setText(getString(R.string.all_items));
        binding.list.setLayoutManager(new LinearLayoutManager(container.getContext()));

        adapter = new ItemListAdapter(new ArrayList<SKUItem>(), callback);
        binding.list.setAdapter(adapter);
        return binding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void showSKUItems(SKUItem[] items) {
        adapter.setValues(Arrays.asList(items));
    }

    @Override
    public void showError(Throwable throwable) {
        Snackbar.make(binding.frameName,"Error loading Items",Snackbar.LENGTH_INDEFINITE).show();
    }

    public interface Callback {
        void onItemClick(SKUItem item);
    }
}
