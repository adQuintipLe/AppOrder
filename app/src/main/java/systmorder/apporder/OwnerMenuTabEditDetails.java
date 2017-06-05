//package systmorder.apporder;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
///**
// * Created by mansoull on 5/6/2017.
// */
//
//public class OwnerMenuTabEditDetails extends Fragment {
//
//    private DatabaseReference databaseReference;
//    private FirebaseAuth firebaseAuth;
//
//    private ImageView ivUpNewPic;
//    private RecyclerView rvItemName, rvItemPrice;
//
//    public static String strItemName = "";
//    public static String strItemPrice = "";
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.owner_fragment_menu_tab_edit_details,container,false);
//        setHasOptionsMenu(true);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        View v = getView();
//
//        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        mbar.setTitle("testinh");
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");
//
//        ivUpNewPic = (ImageView) v.findViewById(R.id.ivUpNewPic);
//
//        rvItemName = (RecyclerView) v.findViewById(R.id.rvItemName);
//        rvItemName.setHasFixedSize(true);
//        rvItemName.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        rvItemPrice = (RecyclerView) v.findViewById(R.id.rvItemPrice);
//        rvItemPrice.setHasFixedSize(true);
//        rvItemPrice.setLayoutManager(new LinearLayoutManager(getActivity()));
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        FirebaseRecyclerAdapter<MenuList, MenuItemEditViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MenuList, MenuItemEditViewHolder>(
//
//                MenuList.class,
//                R.layout.owner_fragment_menu_tab_viewrow,
//                MenuItemEditViewHolder.class,
//                databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu").child(OwnerMenuTab.strMenuMain)
//                        .child(OwnerMenuTab.strMenuMain)
//
//        ) {
//            @Override
//            protected void populateViewHolder(MenuItemEditViewHolder viewHolder, final MenuList model, int position) {
//
//                viewHolder.setMenuItem(model.getMenuItem());
//                viewHolder.setMenuPrice(model.getMenuPrice());
//                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        strItemName = model.getMenuItem();
//                        strItemPrice = model.getMenuPrice();
//
//                    }
//                });
//            }
//        };
//        rvItemName.setAdapter(firebaseRecyclerAdapter);
//        rvItemPrice.setAdapter(firebaseRecyclerAdapter);
//    }
//
//    public static class MenuItemEditViewHolder extends RecyclerView.ViewHolder{
//
//        View fView;
//
//        public MenuItemEditViewHolder(View itemView) {
//            super(itemView);
//            fView = itemView;
//        }
//
//        public void setMenuItem(String menuItem) {
//            TextView txtItemName = (TextView)fView.findViewById(R.id.txtItemName);
//            txtItemName.setText(menuItem);
//
//        }
//        public void setMenuPrice(String menuPrice) {
//            TextView txtItemPrice = (TextView)fView.findViewById(R.id.txtItemPrice);
//            txtItemPrice.setText(menuPrice);
//        }
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.owner_save_menu_details, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if (id == R.id.saveMenuDetails){
//
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            OwnerMenuTabViewDetails fragOwnerMenuTabViewDetails = new OwnerMenuTabViewDetails();
//            transaction.replace(R.id.owner_activity_main, fragOwnerMenuTabViewDetails);
//            transaction.commit();
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
