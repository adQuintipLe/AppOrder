package systmorder.apporder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by mansoull on 8/6/2017.
 */

public class CustChooseRestaurant extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private Button clickToCustomer;

    public static IntentResult intentResult = null;

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
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        databaseReference.child(String.valueOf(intentResult));

        Log.wtf("testBeta", String.valueOf(intentResult));

//        if (intentResult != null){
//            if (intentResult.getContents() == null){
//                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_SHORT).show();
//            } else if (intentResult.getContents() == "1000002"){
//
//                Log.wtf("strFromQRCODE", intentResult.getContents());
//                Log.wtf("strFromRestaurantID", AllLoginActivity.strAllRestrntID);
//                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
//            }
//        }

                if (!intentResult.equals(null)){
            if (intentResult.getContents().equals(null)){
                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
                Log.d("strqrcode",intentResult.getContents());
                Log.v("str1", intentResult.getContents());
            }
        }

//        if (intentResult != null){
//            if (intentResult.getContents() == null){
//                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
//                Log.d("strqrcode",intentResult.getContents());
//                Log.v("str1", intentResult.getContents());
////                startActivity(new Intent(this, CustHomeTab.class));
//            }
//        }

//        if (intentResult.getContents() == null){
//            Toast.makeText(this, "Cancellled", Toast.LENGTH_SHORT).show();
//        } else if (intentResult.getContents() == "1000002"){
//            Log.wtf("strFromQRCODE", intentResult.getContents());
//            Log.wtf("strFromRestaurantID", AllLoginActivity.strAllRestrntID);
//            Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
