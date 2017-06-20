package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roughike.bottombar.OnMenuTabClickListener;

/**
 * Created by mansoull on 4/5/2017.
 */

public class StaffMainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView rvStaffKitchen;

    public static String strViewTblNo = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvStaffKitchen = (RecyclerView) findViewById(R.id.rvStaffKitchen);
        rvStaffKitchen.setHasFixedSize(true);
        rvStaffKitchen.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<TableList, TableKitchenViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableList, TableKitchenViewHolder>(

                TableList.class,
                R.layout.staff_activity_kitchenrow,
                TableKitchenViewHolder.class,
                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblOrder")
        ) {
            @Override
            protected void populateViewHolder(TableKitchenViewHolder viewHolder, final TableList model, int position) {

                viewHolder.setTableNo(model.getTblNo());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strViewTblNo = model.getTblNo();
                    }
                });
            }
        };
        rvStaffKitchen.setAdapter(firebaseRecyclerAdapter);
    }

    public static class TableKitchenViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public TableKitchenViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setTableNo(String tableNo) {
            TextView txtStaffKitchen = (TextView) fView.findViewById(R.id.txtStaffKitchen);
            txtStaffKitchen.setText(tableNo);
        }
    }

}
