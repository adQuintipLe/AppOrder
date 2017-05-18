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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AllLoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPsswrd;
    private TextView btnReg;
    private Button btnLogin;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPsswrd = (EditText)findViewById(R.id.edtPsswrd);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnReg = (TextView) findViewById(R.id.btnReg);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strUserEmail = edtEmail.getText().toString();
                final String strUserPass = edtPsswrd.getText().toString();

                if (TextUtils.isEmpty(strUserEmail) && TextUtils.isEmpty(strUserPass)){

                    Toast.makeText(AllLoginActivity.this, "Both fields are empty", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strUserEmail)){

                    Toast.makeText(AllLoginActivity.this, "please enter your email address", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strUserPass)){

                    Toast.makeText(AllLoginActivity.this, "please enter your password", Toast.LENGTH_SHORT).show();
                    return;

                }

                firebaseAuth.signInWithEmailAndPassword(strUserEmail, strUserPass).addOnCompleteListener
                        (AllLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    if (strUserPass.length() < 6){
                                        edtPsswrd.setError("Password too short, enter minimum 6 characters");
                                    } else {
                                        Toast.makeText(AllLoginActivity.this, "Authentication failed, check your email and password", Toast.LENGTH_SHORT).show();
                                    }
                                } else {

                                    Intent intent = new Intent(AllLoginActivity.this, OwnerChooseActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }
                        });

            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllLoginActivity.this, AllRegisterActivity.class));
            }
        });
    }

}

