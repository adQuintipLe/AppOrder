package systmorder.apporder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;


public class AllRegisterActivity extends AppCompatActivity {

    private EditText edtEmailReg, edtPsswrdReg, edtName;
    private Button btnLoginReg;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    public static String strUserId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        edtEmailReg = (EditText) findViewById(R.id.edtEmailReg);
        edtPsswrdReg = (EditText) findViewById(R.id.edtPsswrdReg);
        edtName = (EditText) findViewById(R.id.edtName);
        btnLoginReg = (Button) findViewById(R.id.btnLoginReg);

        btnLoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String strUserEmail = edtEmailReg.getText().toString().trim();
                final String strUserPass = edtPsswrdReg.getText().toString().trim();
                final String strUserName = edtName.getText().toString().trim();
                final String strUserType = "customer";

                if (TextUtils.isEmpty(strUserEmail) && TextUtils.isEmpty(strUserPass) && TextUtils.isEmpty(strUserName)){

                    Toast.makeText(AllRegisterActivity.this, "Please fill all the empty", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strUserEmail) && TextUtils.isEmpty(strUserPass)){

                    Toast.makeText(AllRegisterActivity.this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strUserEmail) && TextUtils.isEmpty(strUserName)){

                    Toast.makeText(AllRegisterActivity.this, "Please enter your email and name", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strUserPass) && TextUtils.isEmpty(strUserName)){

                    Toast.makeText(AllRegisterActivity.this, "Please enter your password and name", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strUserEmail)){

                    Toast.makeText(AllRegisterActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strUserPass)){

                    Toast.makeText(AllRegisterActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strUserName)){

                    Toast.makeText(AllRegisterActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;

                } if (strUserPass.length() < 6){

                    Toast.makeText(AllRegisterActivity.this, "Password too short, enter minimum 6 characters", Toast.LENGTH_SHORT).show();
                    return;

                }

//                strUserId = firebaseAuth.getCurrentUser().getUid();

                firebaseAuth.createUserWithEmailAndPassword(strUserEmail, strUserPass).addOnCompleteListener(AllRegisterActivity.this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Toast.makeText(AllRegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()){

                            Toast.makeText(AllRegisterActivity.this, "Register failed" + task.getException(), Toast.LENGTH_SHORT).show();

                        } else {

                            strUserId = firebaseAuth.getCurrentUser().getUid();
                            HashMap<String, String> dataMap = new HashMap<String, String>();
                            dataMap.put("userEmail", strUserEmail);
                            dataMap.put("userID", strUserId);
                            dataMap.put("userPass", strUserPass);
                            dataMap.put("userName", strUserName);
                            dataMap.put("userType", strUserType);

                            startActivity(new Intent(AllRegisterActivity.this, AllLoginActivity.class));
                            finish();

                            databaseReference.child(strUserId).setValue(dataMap);
                            databaseReference.child("Auth").child(strUserId).setValue(strUserType);

                        }
                    }
                });
            }
        });
    }
}
