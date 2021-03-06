package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class AdminUserTabView  extends Fragment {

    private TextView txtViewUserEmail, txtViewUserPass, txtViewUserName;
    private EditText edtViewUserType, edtViewRestrntId, edtViewRestrbtName;
    private Button btnsave;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener firebaseAithListener;

    public static String strGetUserId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_user_tab_view,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("User Details");

        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();
//        strGetUserId = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        txtViewUserEmail = (TextView) v.findViewById(R.id.txtViewUserEmail);
        txtViewUserEmail.setText(AdminUserTab.strAllEmailList);

        txtViewUserPass = (TextView) v.findViewById(R.id.txtViewUserPass);
        txtViewUserPass.setText(AdminUserTab.strAllPassList);

        txtViewUserName = (TextView) v.findViewById(R.id.txtViewUserName);
        txtViewUserName.setText(AdminUserTab.strAllNameList);

        edtViewUserType = (EditText) v.findViewById(R.id.edtViewUserType);
        edtViewUserType.setText(AdminUserTab.strUserTypeList);

        edtViewRestrntId = (EditText) v.findViewById(R.id.edtViewRestrntId);
        edtViewRestrntId.setText(AdminUserTab.strRestaurantId);

        edtViewRestrbtName = (EditText) v.findViewById(R.id.edtViewRestrntName);
        edtViewRestrbtName.setText(AdminUserTab.strStrRestaurantNaMe);

        btnsave = (Button) v.findViewById(R.id.btnsave);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String strUserType = edtViewUserType.getText().toString().trim();
                final String strRstrntId = edtViewRestrntId.getText().toString().trim();
                final String strRstrntName = edtViewRestrbtName.getText().toString().trim();

                databaseReference.child("User").child(AdminUserTab.strUserId).child("userType").setValue(strUserType);
                databaseReference.child("User").child(AdminUserTab.strUserId).child("userRestaurantID").setValue(strRstrntId);
                databaseReference.child("User").child(AdminUserTab.strUserId).child("userRestaurantName").setValue(strRstrntName);
//                databaseReference.child(AdminUserTab.strUserId).child("userRole").child(strUserType).child("userRestrntID").setValue(strRstrntId);
//                databaseReference.child(AdminUserTab.strUserId).child("userRole").child(strUserType).child("userRestrntID").setValue(strRstrntName);
                databaseReference.child("Auth").child(AdminUserTab.strUserId).setValue(strUserType);
                databaseReference.child("AuthRes").child(AdminUserTab.strUserId).setValue(strRstrntId);

            }
        });
    }
}
