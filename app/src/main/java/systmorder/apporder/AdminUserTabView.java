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
    private EditText edtViewUserType;
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

        btnsave = (Button) v.findViewById(R.id.btnsave);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUserType = edtViewUserType.getText().toString().trim();

//                strGetUserId = firebaseAuth.getCurrentUser().getUid();
//                databaseReference.child(AllRegisterActivity.strUserId).child(strGetUserId).child("userEmail").setValue(AdminUserTab.strAllEmailList);
//                databaseReference.child(AllRegisterActivity.strUserId).child(strGetUserId).child("userPass").setValue(AdminUserTab.strAllPassList);
//                databaseReference.child(AllRegisterActivity.strUserId).child(strGetUserId).child("userName").setValue(AdminUserTab.strAllNameList);
                databaseReference.child(AdminUserTab.strUserId).child("userType").setValue(strUserType);
                databaseReference.child("Auth").child(AdminUserTab.strUserId).setValue(strUserType);

//                newPost.push().setValue(new AdminUserTabView());
//                HashMap<String, String> dataMap = new HashMap<String, String>();
//                dataMap.put("userEmail", AdminUserTab.strAllEmailList);
//                dataMap.put("userPass", AdminUserTab.strAllPassList);
//                dataMap.put("userName", AdminUserTab.strAllNameList);
//                dataMap.put("userType", strUserType);
//
//                databaseReference.child(AllRegisterActivity.strUserId).setValue(dataMap);


            }
        });
    }
}
