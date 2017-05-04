package systmorder.apporder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class OwnerChooseActivity extends AppCompatActivity {

    Button btnGoToOwner, btnTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_choosing);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        btnGoToOwner = (Button) findViewById(R.id.btnGoToOwner);
        btnTest = (Button) findViewById(R.id.btnTest);
        btnGoToOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerChooseActivity.this, OwnerMainActivity.class));
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null){
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(OwnerChooseActivity.this, "bye2", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(OwnerChooseActivity.this, AllLoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(OwnerChooseActivity.this, "failed delete lorh", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
