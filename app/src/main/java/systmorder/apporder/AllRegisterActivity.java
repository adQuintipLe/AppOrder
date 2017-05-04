package systmorder.apporder;

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

import java.util.HashMap;


public class AllRegisterActivity extends AppCompatActivity {

    private EditText edtEmailReg, edtPsswrdReg, edtName;
    private Button btnLoginReg;
    private FirebaseAuth fAuth;
    private DatabaseReference fDatabase;

    public static String strUserID = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_register);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance().getReference().child("tblUser");
        edtEmailReg = (EditText) findViewById(R.id.edtEmailReg);
        edtPsswrdReg = (EditText) findViewById(R.id.edtPsswrdReg);
        edtName = (EditText) findViewById(R.id.edtName);
        btnLoginReg = (Button) findViewById(R.id.btnLoginReg);

        btnLoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String strUserEmail = edtEmailReg.getText().toString().trim();
                final String strUserPass = edtPsswrdReg.getText().toString().trim();
                final String strUserName = edtName.getText().toString().trim();

                if(TextUtils.isEmpty(strUserEmail)){
                    Toast.makeText(AllRegisterActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(strUserPass)){
                    Toast.makeText(AllRegisterActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(strUserName)){
                    Toast.makeText(AllRegisterActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                strUserID = fAuth.getCurrentUser().getUid();

                fAuth.createUserWithEmailAndPassword(strUserEmail,strUserPass).addOnCompleteListener(AllRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(AllRegisterActivity.this, "what is this" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()){
                            Toast.makeText(AllRegisterActivity.this, "register error", Toast.LENGTH_SHORT).show();
                        } else {

                            strUserID = fAuth.getCurrentUser().getUid();

                            final HashMap<String, String> dataMap = new HashMap<String, String>();
                            dataMap.put("email", strUserEmail);
                            dataMap.put("password", strUserPass);
                            dataMap.put("name", strUserName);

                            fDatabase.child(strUserID).setValue(dataMap);

                            startActivity(new Intent(AllRegisterActivity.this, OwnerChooseActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
