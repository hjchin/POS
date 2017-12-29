package pos.com.pos.allItems.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pos.com.pos.R;
import pos.com.pos.allItems.model.AllItemsItem;
import pos.com.pos.databinding.FragmentItemListBinding;

public class ItemListFragment extends Fragment {

    public static ArrayList<AllItemsItem> allItemsItemArrayList;

    static{
        allItemsItemArrayList = new ArrayList<>();
        allItemsItemArrayList.add(new AllItemsItem("item1","Item 1"));
    }

    private Callback callback;

    public ItemListFragment() {
    }

    public static ItemListFragment newInstance() {
        ItemListFragment fragment = new ItemListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentItemListBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_item_list,container,false);
        binding.frameName.setText(getString(R.string.all_items));
        binding.list.setLayoutManager(new LinearLayoutManager(container.getContext()));
        binding.list.setAdapter(new AllItemsRecyclerViewAdapter(allItemsItemArrayList, callback));
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

    public interface Callback {
        void onItemClick(AllItemsItem item);
    }
}
