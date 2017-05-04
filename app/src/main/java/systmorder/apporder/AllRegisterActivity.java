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



public class AllRegisterActivity extends AppCompatActivity {

    private EditText edtEmailReg, edtPsswrdReg, edtName;
    private Button btnLoginReg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_register);

        edtEmailReg = (EditText) findViewById(R.id.edtEmailReg);
        edtPsswrdReg = (EditText) findViewById(R.id.edtPsswrdReg);
        edtName = (EditText) findViewById(R.id.edtName);
        btnLoginReg = (Button) findViewById(R.id.btnLoginReg);

        btnLoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}
