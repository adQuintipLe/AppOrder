package systmorder.apporder;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 8/6/2017.
 */

public class AllForgotPasswordActivity extends AppCompatActivity {

    private EditText sendYourEmail;
    private Button btnResetPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_forgot_password_activity);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblUser");

        progressDialog = new ProgressDialog(this);
        sendYourEmail = (EditText) findViewById(R.id.sendYourEmail);
        btnResetPassword = (Button) findViewById(R.id.btnResetPassword);
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {

        final String strResetPassword = sendYourEmail.getText().toString().trim();

        if (TextUtils.isEmpty(strResetPassword)){

            Toast.makeText(AllForgotPasswordActivity.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("sending to your email");
        progressDialog.show();
        firebaseAuth.sendPasswordResetEmail(strResetPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    progressDialog.dismiss();
                    Toast.makeText(AllForgotPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                    
                } else {

                    progressDialog.dismiss();
                    Toast.makeText(AllForgotPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
