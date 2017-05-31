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

    public static String strTableNo = "";
    public static String strresID="";

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

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<TableList, TableViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TableList, TableViewHolder>(

                TableList.class,
                R.layout.owner_fragment_table_tabrow,
                TableViewHolder.class,
                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblTable").child(strresID)

        ) {
            @Override
            protected void populateViewHolder(TableViewHolder viewHolder, final TableList model, int position) {

                viewHolder.setTableNo(model.getTableNo());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strTableNo = model.getTableNo();

                    }
                });
            }
        };

        rvAllTable.setAdapter(firebaseRecyclerAdapter);
        Log.v("hai", AllLoginActivity.strAllRestrntID);
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public TableViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setTableNo(String tableNo) {
            TextView txtTableNo = (TextView) fView.findViewById(R.id.txtAllTableNo);
            txtTableNo.setText(tableNo);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.owner_add_table, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addTable) {

            LayoutInflater li = LayoutInflater.from(getActivity());
            final View promptsView = li.inflate(R.layout.testtable, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getActivity());

            alertDialogBuilder.setView(promptsView);

            final EditText userInputTbl = (EditText) promptsView
                    .findViewById(R.id.edttblno);


            alertDialogBuilder.setMessage("Add New Table?")
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id  ) {

                                    strresID = userInputTbl.getText().toString();
//                                    strresID = "table 1";
                                    databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblTable").child(strresID).child("tableNo").setValue(strresID);

                                    Log.v("hello", AllLoginActivity.strAllRestrntID);
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
