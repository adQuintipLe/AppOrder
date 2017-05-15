package systmorder.apporder;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import static systmorder.apporder.R.id.btmBarDetails;


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

        if (menuItemId == R.id.btmBarDetails){

            OwnerMenuTab fragOwnerMenuTab = new OwnerMenuTab();
            transaction.replace(R.id.owner_activity_main, fragOwnerMenuTab);
            transaction.commit();
        }

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
}
