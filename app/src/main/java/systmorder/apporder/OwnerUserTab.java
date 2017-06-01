package systmorder.apporder;

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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class OwnerUserTab extends Fragment {

    private RecyclerView rvStaffUser;
    private DatabaseReference databaseReference;

    public static String strStaffEmailList = "";
    public static String strStaffUserId = "";
    public static String strStaffPassList = "";
    public static String strStaffNameList = "";
    public static String strStaffUserTypeList = "";
    public static String strStaffRestaurantId = "";
    public static String strStaffRestaurantNaMe = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_user_tab,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("User");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        rvStaffUser = (RecyclerView) v.findViewById(R.id.rvStaffUser);
        rvStaffUser.setHasFixedSize(true);
        rvStaffUser.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<UserList, UserViewHolderStaff> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserList, UserViewHolderStaff>(

                UserList.class,
                R.layout.owner_fragment_table_tabrow,
                UserViewHolderStaff.class,
                databaseReference.child("User").child(AllRegisterActivity.strUserId)

        ) {
            @Override
            protected void populateViewHolder(UserViewHolderStaff viewHolder, final UserList model, int position) {

                viewHolder.setUserName(model.getUserName());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strStaffEmailList = model.getUserEmail();
                        strStaffUserId = model.getUserID();
                        strStaffPassList = model.getUserPass();
                        strStaffNameList = model.getUserName();
                        strStaffUserTypeList = model.getUserType();
                        strStaffRestaurantId = model.getUserRestaurantID();
                        strStaffRestaurantNaMe = model.getUserRestaurantName();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        AdminUserTabView fragAdminUserTabView = new AdminUserTabView();
                        transaction.replace(R.id.admin_activity_main, fragAdminUserTabView);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
        };
        rvStaffUser.setAdapter(firebaseRecyclerAdapter);
    }

    public static class UserViewHolderStaff extends RecyclerView.ViewHolder{

        View fView;

        public UserViewHolderStaff(View itemView) {
            super(itemView);
            fView = itemView;
        }

        public void setUserName(String userName) {
            TextView txtUserName = (TextView) fView.findViewById(R.id.txtStaffUserName);
            txtUserName.setText(userName);
        }
    }
}
