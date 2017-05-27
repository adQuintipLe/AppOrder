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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 19/5/2017.
 */

public class AdminUserTab extends Fragment {

    private RecyclerView rvAllUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public static String strAllEmailList = "";
    public static String strUserId = "";
    public static String strAllPassList = "";
    public static String strAllNameList = "";
    public static String strUserTypeList = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_user_tab,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("Users");

//        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        rvAllUser = (RecyclerView) v.findViewById(R.id.rvAllUser);
        rvAllUser.setHasFixedSize(true);
        rvAllUser.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<UserList, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserList, UserViewHolder>(

                UserList.class,
                R.layout.admin_fragment_user_tabrow,
                UserViewHolder.class,
                databaseReference.child(AllRegisterActivity.strUserId)

        ) {
            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, final UserList model, int position) {

                viewHolder.setUserName(model.getUserName());
                viewHolder.fView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        strAllEmailList = model.getUserEmail();
                        strUserId = model.getUserID();
                        strAllPassList = model.getUserPass();
                        strAllNameList = model.getUserName();
                        strUserTypeList = model.getUserType();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        AdminUserTabView fragAdminUserTabView = new AdminUserTabView();
                        transaction.replace(R.id.admin_activity_main, fragAdminUserTabView);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });


            }
        };
        rvAllUser.setAdapter(firebaseRecyclerAdapter);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        View fView;

        public UserViewHolder(View itemView){

            super(itemView);
            fView = itemView;
        }

        public void setUserName(String userName){

            TextView txtUserName = (TextView) fView.findViewById(R.id.txtUserName);
            txtUserName.setText(userName);
        }
    }
}
