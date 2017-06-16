package systmorder.apporder;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 15/6/2017.
 */

public class CustOrderListActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView rvAddtoCart;

    public static String strListMenuName = "";
    public static String strListMenuPrice = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_order_list);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvAddtoCart = (RecyclerView) findViewById(R.id.rvAddtoCart);
        rvAddtoCart.setHasFixedSize(true);
        rvAddtoCart.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<OrderList, OrderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<OrderList, OrderViewHolder>(

                OrderList.class,
                R.layout.cust_activity_order_listrow,
                OrderViewHolder.class,
                databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder")
                        .child(CustChooseRestaurant.qrCodeTableNo).child("OrderMenu")
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, final OrderList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuPrice(model.getMenuPrice());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strListMenuName = model.getMenuName();
                        strListMenuPrice = model.getMenuPrice();

                    }
                });
            }
        };
        rvAddtoCart.setAdapter(firebaseRecyclerAdapter);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setMenuName(String menuName) {
            TextView a = (TextView) fView.findViewById(R.id.a);
            a.setText(menuName);
        }

        public void setMenuPrice(String menuPrice) {
            TextView b = (TextView) fView.findViewById(R.id.b);
            b.setText(menuPrice);
        }
    }
}
