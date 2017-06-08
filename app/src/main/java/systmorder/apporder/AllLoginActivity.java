package systmorder.apporder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AllLoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPsswrd;
    private TextView btnReg, btnForgetPass;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    public static String strAllLoginID = "";
    public static String strUserType = "";
    public static String strAllRestrntID = "";
    public static String strAllPassword = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPsswrd = (EditText)findViewById(R.id.edtPsswrd);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnForgetPass = (TextView) findViewById(R.id.btnForgetPass);
        btnReg = (TextView) findViewById(R.id.btnReg);
        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() !=null){
            firebaseAuth.signOut();
        }


        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() !=null){

                    strAllLoginID = firebaseAuth.getCurrentUser().getUid();
                    Log.v("strUserID", strAllLoginID);

                    databaseReference.child("AuthRes").child(strAllLoginID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            strAllRestrntID = dataSnapshot.getValue().toString();
                            Log.v("test", strAllRestrntID);

                            databaseReference.child(AdminUserTab.strUserId).child(strAllRestrntID);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    databaseReference.child("Auth").child(strAllLoginID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            strUserType = dataSnapshot.getValue().toString();
                            Log.v("strUserType", strUserType);

                            databaseReference.child(AdminUserTab.strUserId).child(strUserType);
                            databaseReference.child("User").child(strAllLoginID).child("userPass").setValue(strAllPassword);

                            if (strUserType.equals("manager")){
                                startActivity(new Intent(AllLoginActivity.this, OwnerMainActivity.class));
                            } else if (strUserType.equals("staff")){
                                startActivity(new Intent(AllLoginActivity.this, StaffChooseActivity.class));
                            } else if (strUserType.equals("customer")){
                                startActivity(new Intent(AllLoginActivity.this, CustMainActivity.class));
                            }
                            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllLoginActivity.this, AllRegisterActivity.class));
            }
        });
        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllLoginActivity.this, AllForgotPasswordActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    private void signIn(){

        final String strUserEmail = edtEmail.getText().toString();
        strAllPassword = edtPsswrd.getText().toString();

        if (TextUtils.isEmpty(strUserEmail) && TextUtils.isEmpty(strAllPassword)){

            Toast.makeText(AllLoginActivity.this, "Both fields are empty", Toast.LENGTH_SHORT).show();
            return;

        } else if (TextUtils.isEmpty(strUserEmail)){

            Toast.makeText(AllLoginActivity.this, "please enter your email address", Toast.LENGTH_SHORT).show();
            return;

        } else if (TextUtils.isEmpty(strAllPassword)){

            Toast.makeText(AllLoginActivity.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;

        }
        progressDialog.setMessage("Login...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(strUserEmail, strAllPassword).addOnCompleteListener
                (AllLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            if (strAllPassword.length() < 6){
                                progressDialog.dismiss();
                                edtPsswrd.setError("Password too short, enter minimum 6 characters");
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(AllLoginActivity.this, "Authentication failed, check your email and password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(AllLoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}

