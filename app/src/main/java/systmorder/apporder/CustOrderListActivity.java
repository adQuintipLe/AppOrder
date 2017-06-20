package systmorder.apporder;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 15/6/2017.
 */

public class CustOrderListActivity extends AppCompatActivity {

    private static DatabaseReference databaseReference;
    private RecyclerView rvAddtoCart;

    public static String strListMenuName = "";
    public static String strListMenuPrice = "";
    public static String strListMenuAmount = "";

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
                databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child(CustChooseRestaurant.orderId)
                        .child("OrderMenu")
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, final OrderList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuPrice(model.getMenuPrice());
                viewHolder.setMenuQuantity(model.getMenuQuantity());

                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strListMenuName = model.getMenuName();
                        strListMenuPrice = model.getMenuPrice();
                        strListMenuAmount = model.getMenuQuantity();

                        startActivity(new Intent(CustOrderListActivity.this, CustHomeTabMenuAdd.class));
//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        CustHomeTabMenuEdit fragCustHomeTabMenuEdit = new CustHomeTabMenuEdit();
//                        transaction.replace(R.id.cust_activity_main, fragCustHomeTabMenuEdit);
//                        transaction.addToBackStack(null);
//                        transaction.commit();

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
            TextView orderMenuItemList = (TextView) fView.findViewById(R.id.orderMenuItemList);
            orderMenuItemList.setText(menuName);
        }

        public void setMenuPrice(String menuPrice) {
            TextView orderMenuPriceList = (TextView) fView.findViewById(R.id.orderMenuPriceList);
            orderMenuPriceList.setText(menuPrice);
        }

        public void setMenuQuantity(String menuQuantity) {
           TextView orderMenuQuantityList = (TextView) fView.findViewById(R.id.orderMenuQuantityList);
            orderMenuQuantityList.setText(menuQuantity);


        }
    }
}
