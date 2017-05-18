package systmorder.apporder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;


public class CustMainActivity extends AppCompatActivity implements OnMenuTabClickListener {

    private BottomBar CustbtmBar;
    final Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustbtmBar = BottomBar.attach(this, savedInstanceState);
        CustbtmBar.setItemsFromMenu(R.menu.cust_tab, this);
    }


    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cart) {
//            startActivity(new Intent(CustMainActivity.this, OwnerChooseActivity.class));

            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.testcust, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.editTextDialogUserInput);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
//                                    result.setText(userInput.getText());
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuTabSelected(@IdRes int menuItemId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(menuItemId == R.id.btmBarHomeCust){

            CustHomeTab fragCustHomeTab = new CustHomeTab();
            transaction.replace(R.id.cust_activity_main, fragCustHomeTab);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarOrderHistoryCust){

            CustOrderHistoryTab fragCustOrderHistoryTab = new CustOrderHistoryTab();
            transaction.replace(R.id.cust_activity_main, fragCustOrderHistoryTab);
            transaction.commit();

        } else if (menuItemId == R.id.btmBarSettingCust){

            CustSettingTab fragCustSettingTab = new CustSettingTab();
            transaction.replace(R.id.cust_activity_main, fragCustSettingTab);
            transaction.commit();
        }

    }

    @Override
    public void onMenuTabReSelected(@IdRes int menuItemId) {

    }
}
