package systmorder.apporder;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 21/6/2017.
 */

public class StaffMainActivityKitchenView extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView rvStaffKitchenView;
    private Button btnPrepare, btnServing;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity_main_kitchen_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarKitchen);
        setSupportActionBar(toolbar);
        toolbar.setTitle("hax5");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvStaffKitchenView = (RecyclerView) findViewById(R.id.rvStaffKitchenView);
        rvStaffKitchenView.setHasFixedSize(true);
        rvStaffKitchenView.setLayoutManager(new LinearLayoutManager(this));

        btnPrepare = (Button) findViewById(R.id.btnPrepare);

        btnServing = (Button) findViewById(R.id.btnServing);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<OrderList, TableOrderKitchenViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<OrderList, TableOrderKitchenViewHolder>(

                OrderList.class,
                R.layout.staff_activity_content_kitchen_viewrow,
                TableOrderKitchenViewHolder.class,
                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblOrder").child("OrderList").child(StaffMainActivity.strViewOrderId)
                        .child("viewOrderMenu")
        ) {
            @Override
            protected void populateViewHolder(TableOrderKitchenViewHolder viewHolder, final OrderList model, int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuQuantity(model.getMenuQuantity());

            }
        };
        rvStaffKitchenView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class TableOrderKitchenViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public TableOrderKitchenViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setMenuName(String menuName) {
            TextView txtKitchenViewMenuItem = (TextView) fView.findViewById(R.id.txtKitchenViewMenuItem);
            txtKitchenViewMenuItem.setText(menuName);
        }

        public void setMenuQuantity(String menuQuantity) {
            TextView txtKitchenViewQuantity = (TextView) fView.findViewById(R.id.txtKitchenViewQuantity);
            txtKitchenViewQuantity.setText(menuQuantity);
        }
    }
}
