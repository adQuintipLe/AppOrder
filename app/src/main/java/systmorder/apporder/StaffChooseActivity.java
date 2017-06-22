package systmorder.apporder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;


public class StaffChooseActivity extends AppCompatActivity {

    private CardView clickToCashier, clickToKitchen, clickToCustomer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity_choosing);

        clickToCashier = (CardView) findViewById(R.id.clickToCashier);
        clickToCashier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffChooseActivity.this, StaffMainActivity.class));
                finish();
            }
        });

        clickToKitchen = (CardView) findViewById(R.id.clickToKitchen);
        clickToKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffChooseActivity.this, StaffMainActivity.class));
                finish();
            }
        });

        clickToCustomer = (CardView) findViewById(R.id.clickToCustomer);
        clickToCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffChooseActivity.this, CustChooseRestaurant.class));
                finish();
            }
        });

    }
}
