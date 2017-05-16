package systmorder.apporder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;


public class OwnerMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar OwnerbtmBar;
    final Context context = this;

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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (menuItemId == R.id.btmBarDashboard){

            OwnerDashboardTab fragOwnerDashboardTab = new OwnerDashboardTab();
            transaction.replace(R.id.owner_activity_main, fragOwnerDashboardTab);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarDetails){

            OwnerMenuTab fragOwnerMenuTab = new OwnerMenuTab();
            transaction.replace(R.id.owner_activity_main, fragOwnerMenuTab);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarUser){

            OwnerUserTab fragOwnerUserTab = new OwnerUserTab();
            transaction.replace(R.id.owner_activity_main, fragOwnerUserTab);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarSetting){

            OwnerSettingTab fragOwnerSettingTab = new OwnerSettingTab();
            transaction.replace(R.id.owner_activity_main, fragOwnerSettingTab);
            transaction.commit();

        }

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
}
