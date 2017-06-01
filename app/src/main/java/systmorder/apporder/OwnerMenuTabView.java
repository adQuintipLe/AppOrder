package systmorder.apporder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 1/6/2017.
 */

public class OwnerMenuTabView extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private RecyclerView rvAllMenuView;

    public static String strMenuItem = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_menu_tab_view, container,false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("Menu Items");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvAllMenuView = (RecyclerView) v.findViewById(R.id.rvAllMenuView);
        rvAllMenuView.setHasFixedSize(true);
        rvAllMenuView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<MenuList, MenuItemViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MenuList, MenuItemViewHolder>(

                MenuList.class,
                R.layout.owner_fragment_menu_tab_viewrow,
                MenuItemViewHolder.class,
                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu").child(OwnerMenuTab.strMenuMain)
                        .child(OwnerMenuTab.strMenuMain)
        ) {
            @Override
            protected void populateViewHolder(MenuItemViewHolder viewHolder, final MenuList model, int position) {

                viewHolder.setMenuItem(model.getMenuItem());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strMenuItem = model.getMenuItem();
                    }
                });

            }
        };
        rvAllMenuView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setMenuItem(String menuItem) {
            TextView txtAllMenuView = (TextView)fView.findViewById(R.id.txtAllMenuView);
            txtAllMenuView.setText(menuItem);

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.owner_modify_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.modifyMenuCatogery){

        }else if (id == R.id.addMenu){

            LayoutInflater li = LayoutInflater.from(getActivity());
            final View promptsView = li.inflate(R.layout.testmenu, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getActivity());

            alertDialogBuilder.setView(promptsView);

            final EditText userInputMenu = (EditText) promptsView
                    .findViewById(R.id.edtMenuItem);

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id  ) {
                                    strMenuItem = userInputMenu.getText().toString();

                                    databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu").child(OwnerMenuTab.strMenuMain)
                                            .child(OwnerMenuTab.strMenuMain).child(strMenuItem).child("menuItem").setValue(strMenuItem);
                                    Toast.makeText(getActivity(),strMenuItem, Toast.LENGTH_LONG).show();
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
