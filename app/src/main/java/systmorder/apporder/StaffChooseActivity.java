package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class StaffChooseActivity extends AppCompatActivity {

    private Button btnStaffGoToStaff, btnStaffGoToCust;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity_choosing);

        btnStaffGoToStaff = (Button) findViewById(R.id.btnStaffGoToStaff);
        btnStaffGoToCust = (Button) findViewById(R.id.btnStaffGoToCust);

        btnStaffGoToStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnStaffGoToCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
