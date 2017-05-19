package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 19/5/2017.
 */

public class AdminUserTab extends Fragment {

    private EditText edtEmailRegAdmin, edtPsswrdRegAdmin, edtNameAdmin, edtUserTypeAdmin;
    private Button btnUpdateUserAdmin;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_user_tab,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("Users");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        edtEmailRegAdmin = (EditText) v.findViewById(R.id.edtEmailRegAdmin);
        edtPsswrdRegAdmin = (EditText) v.findViewById(R.id.edtPsswrdRegAdmin);
        edtNameAdmin = (EditText) v.findViewById(R.id.edtNameAdmin);
        edtUserTypeAdmin = (EditText) v.findViewById(R.id.edtUserTypeAdmin);
        btnUpdateUserAdmin = (Button) v.findViewById(R.id.btnUpdateUserAdmin);

        btnUpdateUserAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String strUserEmailAdmin = edtEmailRegAdmin.getText().toString();
            }
        });
    }
}
