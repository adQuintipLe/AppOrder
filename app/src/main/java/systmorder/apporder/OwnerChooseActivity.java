package systmorder.apporder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;


public class OwnerChooseActivity extends AppCompatActivity {

    private Button btnOwnerGoToStaff;
    private CardView clickToOwner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_choosing);

//        btnOwnerGoToOwner = (Button) findViewById(R.id.btnOwnerGoToOwner);
        clickToOwner = (CardView) findViewById(R.id.clickToOwner);
        btnOwnerGoToStaff = (Button) findViewById(R.id.btnOwnerGoToStaff);
//        btnOwnerGoToCust = (Button) findViewById(R.id.btnOwnerGoToCust);

        clickToOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerChooseActivity.this, OwnerMainActivity.class));
                finish();
            }
        });

        btnOwnerGoToStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerChooseActivity.this, CustMainActivity.class));
                finish();
            }
        });

//        btnOwnerGoToCust.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(OwnerChooseActivity.this, CustMainActivity.class));
//                finish();
//
//            }
//        });
    }
}
