package systmorder.apporder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by mansoull on 28/4/2017.
 */

//this is for tester only

public class AdminTest extends AppCompatActivity {

    private Button scanQR;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_test);

        scanQR = (Button) findViewById(R.id.scanQR);

        final Activity activity = this;

        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("Scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                Log.d("strqrcode",result.getContents());
                Log.v("str1", result.getContents());
                startActivity(new Intent(AdminTest.this, AllLoginActivity.class));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        Log.wtf("str",result.getContents());
    }
}

/**
 *  public class System extends AppCompatActivity {
 *
 *      System.Out.Println("hello world");
 *      return 0;
 *
 *      //i forgot to do this
 *  }
 */
