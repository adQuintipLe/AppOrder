package systmorder.apporder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by mansoull on 2/6/2017.
 */

public class OwnerMenuTabViewDetails extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private TextView tvItemName, tvItemPrice;
    private ImageView imgView;

    public static String strMenuItemName = "";
    public static String strMenuItemImage = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_menu_tab_view_details, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle(OwnerMenuTabView.strMenuItem);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        imgView = (ImageView) v.findViewById(R.id.imgView);
//        Picasso.with(getActivity()).load(OwnerMenuTabEditDetails.uriImg).into(imgView);

        tvItemName = (TextView) v.findViewById(R.id.tvItemName);
        tvItemName.setText(OwnerMenuTabView.strMenuItem);

        tvItemPrice = (TextView) v.findViewById(R.id.tvItemPrice);

//        databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu").child(OwnerMenuTab.strMenuMain)
//                .child(OwnerMenuTab.strMenuMain).child(OwnerMenuTabView.strMenuItem).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                strMenuItemImage = getContext().getApplicationContext().toString();
////                strMenuItemImage = dataSnapshot.getValue().toString();
//                Log.v("Frak1", strMenuItemImage );
//                strMenuItemName = dataSnapshot.getValue().toString();
//                Log.v("Frak2", strMenuItemName);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu").child(OwnerMenuTab.strMenuMain)
                .child(OwnerMenuTab.strMenuMain).child(OwnerMenuTabView.strMenuItem).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                strMenuItemImage = dataSnapshot.getValue().toString();
                strMenuItemImage = getContext().getApplicationContext().toString();
                Log.v("Frak1", strMenuItemImage );
                strMenuItemName = dataSnapshot.getValue().toString();
                Log.v("Frak2", strMenuItemName);

                Picasso.with(getActivity()).load(OwnerMenuTabView.strMenuImage).into(imgView);
                tvItemName.setText(strMenuItemName);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.owner_modify_menu_details, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.modifyMenuCatogery){

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            OwnerMenuTabEditDetails fragOwnerMenuTabEditDetails = new OwnerMenuTabEditDetails();
            transaction.replace(R.id.owner_activity_main, fragOwnerMenuTabEditDetails);
            transaction.addToBackStack(null);
            transaction.commit();

        }
        return super.onOptionsItemSelected(item);
    }
}
