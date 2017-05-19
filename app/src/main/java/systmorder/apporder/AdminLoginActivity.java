package systmorder.apporder;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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


public class AdminLoginActivity extends AppCompatActivity {

    private Button btnLoginAdmin;
    private EditText edtEmailAdmin,edtPsswrdAdmin;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        edtEmailAdmin = (EditText) findViewById(R.id.edtEmailAdmin);
        edtPsswrdAdmin = (EditText) findViewById(R.id.edtPsswrdAdmin);
        btnLoginAdmin = (Button) findViewById(R.id.btnLoginAdmin);

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String strEmailAdmin = edtEmailAdmin.getText().toString();
                final String strPassAdmin = edtPsswrdAdmin.getText().toString();

                if (TextUtils.isEmpty(strEmailAdmin) && TextUtils.isEmpty(strPassAdmin)){

                    Toast.makeText(AdminLoginActivity.this, "Both fields are empty", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strEmailAdmin)){

                    Toast.makeText(AdminLoginActivity.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                    return;

                } else if (TextUtils.isEmpty(strPassAdmin)){

                    Toast.makeText(AdminLoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;

                }

                firebaseAuth.signInWithEmailAndPassword(strEmailAdmin, strPassAdmin).addOnCompleteListener
                        (AdminLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()){
                                    if (strPassAdmin.length() < 6){
                                        edtPsswrdAdmin.setError("Password too short, enter minimum 6 characters");
                                    }else {

                                        Toast.makeText(AdminLoginActivity.this, "Authentication failed, check your email and password", Toast.LENGTH_SHORT).show();

                                    }
                                } else {

                                    Intent intent = new Intent(AdminLoginActivity.this, AdminMainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }

                            }
                        });
            }
        });
    }
}
