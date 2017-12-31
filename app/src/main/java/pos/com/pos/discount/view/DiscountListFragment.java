package pos.com.pos.discount.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pos.com.pos.R;
import pos.com.pos.databinding.FragmentLeftFrameBinding;
import pos.com.pos.discount.model.DiscountItem;

public class DiscountListFragment extends Fragment {

    private Callback callback;

    public static ArrayList<DiscountItem> discountItemArrayList;

    static{
        discountItemArrayList = new ArrayList<>();
        discountItemArrayList.add(new DiscountItem("discount1","Discount A",0));
        discountItemArrayList.add(new DiscountItem("discount2","Discount B",10));
        discountItemArrayList.add(new DiscountItem("discount3","Discount C",35.5));
        discountItemArrayList.add(new DiscountItem("discount4","Discount D",50));
        discountItemArrayList.add(new DiscountItem("discount5","Discount E",100));
    }

    public DiscountListFragment() {
    }

    public static DiscountListFragment newInstance() {
        DiscountListFragment fragment = new DiscountListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        FragmentLeftFrameBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_left_frame,container,false);
        binding.frameName.setText(getString(R.string.all_discounts));
        binding.list.setLayoutManager(new LinearLayoutManager(container.getContext()));
        binding.list.setAdapter(new DiscountAdapter(discountItemArrayList, callback));
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
        void onItemClick(pos.com.pos.library.view.DiscountItem item);
    }
}
