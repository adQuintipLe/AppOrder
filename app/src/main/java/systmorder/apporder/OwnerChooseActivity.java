package systmorder.apporder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class OwnerChooseActivity extends AppCompatActivity {

    Button btnGoToOwner, btnGoToStaff, btnGoToCust;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_choosing);

        btnGoToOwner = (Button) findViewById(R.id.btnGoToOwner);
        btnGoToStaff = (Button) findViewById(R.id.btnGoToStaff);
        btnGoToCust = (Button) findViewById(R.id.btnGoToCust);

        btnGoToOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerChooseActivity.this, OwnerMainActivity.class));
            }
        });

        btnGoToStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnGoToCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
