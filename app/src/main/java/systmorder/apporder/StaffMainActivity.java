package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.OnMenuTabClickListener;

/**
 * Created by mansoull on 4/5/2017.
 */

public class StaffMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
}
