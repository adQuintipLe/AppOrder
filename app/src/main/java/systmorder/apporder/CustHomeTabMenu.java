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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by mansoull on 14/6/2017.
 */

public class CustHomeTabMenu extends Fragment {

    private DatabaseReference databaseReference;
    private RecyclerView rvCustMenu;

    public static String strCustMenuItem = "";
    public static String strCustMenuPrice = "";
    public static String strCustMenuImage = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_fragment_home_tab_menu,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle(CustHomeTab.strCustMenuMain);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvCustMenu = (RecyclerView) v.findViewById(R.id.rvCustMenu);
        rvCustMenu.setHasFixedSize(true);
        rvCustMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<MenuList, MenuViewCustViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MenuList, MenuViewCustViewHolder>(

                MenuList.class,
                R.layout.cust_fragment_home_tab_menurow,
                MenuViewCustViewHolder.class,
                databaseReference.child(CustChooseRestaurant.intentResult.getContents()).child("tblMenu").child(CustHomeTab.strCustMenuMain)
                        .child(CustHomeTab.strCustMenuMain)
        ) {
            @Override
            protected void populateViewHolder(MenuViewCustViewHolder viewHolder, final MenuList model, int position) {

                viewHolder.setCustMenuItem(model.getMenuItem());
                viewHolder.setCustMenuPrice(model.getMenuPrice());
                viewHolder.setCustMenuImage(getContext().getApplicationContext(), model.getMenuImage());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strCustMenuItem = model.getMenuItem();
                        strCustMenuPrice = model.getMenuPrice();
                        strCustMenuImage = model.getMenuImage();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        CustHomeTabMenuAdd fragCustHomeTabMenuAdd = new CustHomeTabMenuAdd();
                        transaction.replace(R.id.cust_activity_main, fragCustHomeTabMenuAdd);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });

            }
        };
        rvCustMenu.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MenuViewCustViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public MenuViewCustViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setCustMenuItem(String custMenuItem) {
            TextView txtCustMenu = (TextView) fView.findViewById(R.id.txtCustMenu);
            txtCustMenu.setText(custMenuItem);
        }

        public void setCustMenuPrice(String custMenuPrice) {
            TextView txtCustPrice = (TextView) fView.findViewById(R.id.txtCustPrice);
            txtCustPrice.setText(custMenuPrice);
        }

        public void setCustMenuImage(Context applicationContext, String menuImage) {
            ImageView IvCustMenu = (ImageView) fView.findViewById(R.id.IvCustMenu);
            Picasso.with(applicationContext).load(menuImage).into(IvCustMenu);
        }
    }
}
