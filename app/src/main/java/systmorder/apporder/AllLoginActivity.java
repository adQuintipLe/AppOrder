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


public class AllLoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPsswrd;
    private TextView btnReg;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_login);


        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPsswrd = (EditText)findViewById(R.id.edtPsswrd);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnReg = (TextView) findViewById(R.id.btnReg);


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

