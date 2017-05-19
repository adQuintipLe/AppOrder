package systmorder.apporder;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class AdminMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar btmBarAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btmBarAdmin = BottomBar.attach(this,savedInstanceState);
        btmBarAdmin.setItemsFromMenu(R.menu.admin_tab, this);
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (menuItemId == R.id.btmBarDashboardAdmin){

        } else if (menuItemId == R.id.btmBarDetailsAdmin){

        } else if (menuItemId == R.id.btmBarUserAdmin){

            AdminUserTab fragAdminUserTab = new AdminUserTab();
            transaction.replace(R.id.admin_activity_main, fragAdminUserTab);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarSettingAdmin){

            AdminSettingTab fragAdminSettingTab = new AdminSettingTab();
            transaction.replace(R.id.admin_activity_main, fragAdminSettingTab);
            transaction.commit();

        }

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
}
