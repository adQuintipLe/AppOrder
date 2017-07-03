package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 22/6/2017.
 */

public class OwnerOrderTabView extends Fragment {

    private DatabaseReference databaseReference;
    private RecyclerView rvOwnerOrderView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_order_tab_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("View Order");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvOwnerOrderView = (RecyclerView) v.findViewById(R.id.rvOwnerOrderView);
        rvOwnerOrderView.setHasFixedSize(true);
        rvOwnerOrderView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<OrderList, OrderOwnerViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<OrderList, OrderOwnerViewHolder>(

                OrderList.class,
                R.layout.owner_fragment_order_tab_viewrow,
                OrderOwnerViewHolder.class,
                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblOrder").child("OrderList").child(OwnerOrderTab.strOwnerViewOrderId)
                        .child("viewOrderMenu")
        ) {
            @Override
            protected void populateViewHolder(OrderOwnerViewHolder viewHolder, final OrderList model, int position) {

                viewHolder.setOwnerMenuName(model.getMenuName());
                viewHolder.setOwnerMenuQuantity(model.getMenuQuantity());
            }
        };
        rvOwnerOrderView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class OrderOwnerViewHolder extends RecyclerView.ViewHolder{

        public static int intListOrderMenu;
        View fView;

        public OrderOwnerViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setOwnerMenuName(String ownerMenuName) {
            TextView txtOwnerViewMenuItem = (TextView) fView.findViewById(R.id.txtOwnerViewMenuItem);
            txtOwnerViewMenuItem.setText(ownerMenuName);
        }

        public void setOwnerMenuQuantity(String ownerMenuQuantity) {
            TextView txtOwnerViewQuantity = (TextView) fView.findViewById(R.id.txtOwnerViewQuantity);
            txtOwnerViewQuantity.setText(ownerMenuQuantity);
        }
    }
}
