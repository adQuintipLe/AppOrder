package systmorder.apporder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.GLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class OwnerMenuTab extends Fragment {

//    final OwnerMenuTab context = this;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private RecyclerView rvAllMenuCatogery;

    public static String strMenuMain = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_menu_tab,container,false);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("test");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvAllMenuCatogery = (RecyclerView) v.findViewById(R.id.rvAllMenuCatogery);
        rvAllMenuCatogery.setHasFixedSize(true);
        rvAllMenuCatogery.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<MenuList, MenuViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MenuList, MenuViewHolder>(

                MenuList.class,
                R.layout.owner_fragment_menu_tabrow,
                MenuViewHolder.class,
                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu")
        ) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, final MenuList model, int position) {

                viewHolder.setMenuMain(model.getMenuMain());
                viewHolder.setImageMain(getContext().getApplicationContext(),model.getImgMain());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strMenuMain = model.getMenuMain();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        OwnerMenuTabView fragOwnerMenuTabView = new OwnerMenuTabView();
                        transaction.replace(R.id.owner_activity_main, fragOwnerMenuTabView);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
        };
        rvAllMenuCatogery.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public MenuViewHolder(View itemView) {
            super(itemView);

            fView = itemView;
        }

        public void setMenuMain(String menuMain) {
            TextView txtAllMenuCategory = (TextView)fView.findViewById(R.id.txtAllMenuCategory);
            txtAllMenuCategory.setText(menuMain);
        }

        public void setImageMain(Context applicationContext, String imgMain) {
            ImageView asdf = (ImageView) fView.findViewById(R.id.asdf);
            Picasso.with(applicationContext).load(imgMain).into(asdf);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.owner_add_category, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.addCategory) {

            LayoutInflater li = LayoutInflater.from(getActivity());
            final View promptsView = li.inflate(R.layout.testcust, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getActivity());

            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.editTextDialogUserInput);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id  ) {
                                    strMenuMain = userInput.getText().toString();

                                    databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu").child(strMenuMain).child("menuMain").setValue(strMenuMain);

                                    Toast.makeText(getActivity(),strMenuMain, Toast.LENGTH_LONG).show();
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
