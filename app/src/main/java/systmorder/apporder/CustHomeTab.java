package systmorder.apporder;

import android.content.Context;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by mansoull on 18/5/2017.
 */

public class CustHomeTab extends Fragment {

    private DatabaseReference databaseReference;
    private RecyclerView rvCustMenuCatogery;

    public static String strCustMenuMain = "";
    public static String strCustImgMain = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_fragment_home_tab,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("Home");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvCustMenuCatogery = (RecyclerView) v.findViewById(R.id.rvCustMenuCatogery);
        rvCustMenuCatogery.setHasFixedSize(true);
        rvCustMenuCatogery.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<MenuList, MenuCustViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MenuList, MenuCustViewHolder>(

                MenuList.class,
                R.layout.cust_fragment_home_tabrow,
                MenuCustViewHolder.class,
                databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblMenu")
        ) {
            @Override
            protected void populateViewHolder(MenuCustViewHolder viewHolder, final MenuList model, int position) {

                viewHolder.setCustMenuMain(model.getMenuMain());
                viewHolder.setCustMenuImg(getContext().getApplicationContext(),model.getImgMain());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strCustMenuMain = model.getMenuMain();
                        strCustImgMain = model.getMenuImage();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        CustHomeTabMenu fragCustHomeTabMenu = new CustHomeTabMenu();
                        transaction.replace(R.id.cust_activity_main, fragCustHomeTabMenu);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });
            }
        };
        rvCustMenuCatogery.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MenuCustViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public MenuCustViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setCustMenuMain(String custMenuMain) {
            TextView txtCustMenuCatogery = (TextView) fView.findViewById(R.id.txtCustMenuCatogery);
            txtCustMenuCatogery.setText(custMenuMain);
        }

        public void setCustMenuImg(Context applicationContext, String custMenuImg) {
            ImageView IvCustMenuCatogery = (ImageView) fView.findViewById(R.id.IvCustMenuCatogery);
            Picasso.with(applicationContext).load(custMenuImg).into(IvCustMenuCatogery);
        }
    }
}
