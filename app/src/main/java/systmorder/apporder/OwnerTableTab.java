package systmorder.apporder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class OwnerTableTab extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAuth firebaseAuth;
    private RecyclerView rvAllTable;

    public static String testshj = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_table_tab,container,false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("Table");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvAllTable = (RecyclerView) v.findViewById(R.id.rvAllTable);
        rvAllTable.setHasFixedSize(true);
        rvAllTable.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        FirebaseRecyclerAdapter<TableList, TableViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableList, TableViewHolder>(
//
//                TableList.class,
//                R.layout.owner_fragment_table_tabrow,
//                TableViewHolder.class,
//                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblTable").child("table 1")
//        ) {
//            @Override
//            protected void populateViewHolder(TableViewHolder viewHolder, TableList model, int position) {
//
//            }
//        }
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.owner_add_table, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addTable) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getActivity());

            DatabaseReference df = FirebaseDatabase.getInstance().getReference().child("tblRestrn");

            alertDialogBuilder.setMessage("Add New Table?")
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id  ) {

                                    String strresID = "table 1";
                                    databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblTable").child(strresID).child("tableNo").setValue(strresID);

                                    Toast.makeText(getActivity(), "Table added", Toast.LENGTH_SHORT).show();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }
}
