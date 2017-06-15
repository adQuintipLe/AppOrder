package systmorder.apporder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mansoull on 8/6/2017.
 */

public class CustChooseRestaurant extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private Button clickToCustomer;

    public static IntentResult intentResult = null;
    public static String qrCodeResId = "";
    public static String qrCodeTableNo = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_chooose_restaurant);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        clickToCustomer = (Button) findViewById(R.id.clickToCustomer);

        final Activity activity = this;

        clickToCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("Scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.setCaptureActivity(AnyOrientationCaptureActivity.class);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        Log.wtf("testBeta", intentResult.getContents());

        qrCodeResId = intentResult.getContents().substring(0,7);
        qrCodeTableNo = intentResult.getContents().substring(7,14);

        Log.wtf("qrCodeResId", qrCodeResId);
        Log.wtf("qrCodeResId", qrCodeTableNo);

        if (intentResult != null){
            if (intentResult.getContents() == null){
                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, qrCodeResId + " with " + qrCodeTableNo, Toast.LENGTH_SHORT).show();
                Log.d("strqrcode",intentResult.getContents());
                startActivity(new Intent(this, CustMainActivity.class));
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}
