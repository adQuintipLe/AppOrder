package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;


public class OwnerMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar OwnerbtmBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        OwnerbtmBar = BottomBar.attach(this, savedInstanceState);
        OwnerbtmBar.setItemsFromMenu(R.menu.owner_tab, this);
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
}
