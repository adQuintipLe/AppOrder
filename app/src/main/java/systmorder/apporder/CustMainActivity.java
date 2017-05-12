package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;


public class CustMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar CustbtmBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustbtmBar = BottomBar.attach(this, savedInstanceState);
        CustbtmBar.setItemsFromMenu(R.menu.cust_tab, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
}
