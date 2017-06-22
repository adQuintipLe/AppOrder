package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class OwnerOrderTab extends Fragment {

    private DatabaseReference databaseReference;
    private RecyclerView rvOwnerOrder;

    public static String strOwnerViewTableNo = "";
    public static String strOwnerViewOrderId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_order_tab, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("Order List");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvOwnerOrder = (RecyclerView) v.findViewById(R.id.rvOwnerOrder);
        rvOwnerOrder.setHasFixedSize(true);
        rvOwnerOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<TableList, TableOwnerViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableList, TableOwnerViewHolder>(

                TableList.class,
                R.layout.owner_fragment_order_tabrow,
                TableOwnerViewHolder.class,
                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblOrder").child("OrderList")
        ) {
            @Override
            protected void populateViewHolder(TableOwnerViewHolder viewHolder, final TableList model, int position) {

                viewHolder.setOwnerTableNo(model.getTblNo());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strOwnerViewTableNo = model.getTblNo();
                        strOwnerViewOrderId = model.getViewOrderId();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        OwnerOrderTabView fragOwnerOrderTabView = new OwnerOrderTabView();
                        transaction.replace(R.id.owner_activity_main, fragOwnerOrderTabView);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });

            }
        };
        rvOwnerOrder.setAdapter(firebaseRecyclerAdapter);
    }

    public static class TableOwnerViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public TableOwnerViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setOwnerTableNo(String ownerTableNo) {
            TextView txtOwnerViewTableNo = (TextView) fView.findViewById(R.id.txtOwnerViewTableNo);
            txtOwnerViewTableNo.setText(ownerTableNo);
        }
    }
}
