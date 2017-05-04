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

import java.security.acl.Owner;


public class AllLoginActivity extends AppCompatActivity {

    public static String strEmail, strPassword;
    public static String strUserType = "";
    public static String strUserID = "";

    private EditText edtEmail;
    private EditText edtPsswrd;
    private TextView btnReg;
    private Button btnLogin;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fAuthListenerAdmin;
    private DatabaseReference fDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_login);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblUser");

        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPsswrd = (EditText)findViewById(R.id.edtPsswrd);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnReg = (TextView) findViewById(R.id.btnReg);

        if(fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllLoginActivity.this, OwnerChooseActivity.class));
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

