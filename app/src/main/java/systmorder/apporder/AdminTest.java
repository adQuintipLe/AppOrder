package systmorder.apporder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

    public static String s = "";
    public static String s1 = "";

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


        s = result.getContents().substring(0,7);
        s1 = result.getContents().substring(7,14);
//        s.substring(6);
//                s1 = result.getContents();
//                s1.substring(7,13);

        Log.wtf("s",s);
        Log.wtf("s1",s1);

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

//        if (result != null){
//            if (result == null){
//                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "susses", Toast.LENGTH_SHORT).show();
////                Log.d("strqrcode",result.getFormatName());
////                Log.v("str1", result.getFormatName());
////                startActivity(new Intent(AdminTest.this, AllLoginActivity.class));
//                String getContent = result.getContents();
//                String getContent1 = result.getFormatName();
//                String getContent2 = result.toString();
//
//                Log.d("strqrcode", getContent);
//                Log.w("strqrcode1", getContent1);
//                Log.w("strqrcode2", getContent2);
//
//                String dataUri = result.getContents();
//                String[] dataElement = myData.breakString(dataUri);
//
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.putExtra("test1", dataElement[1]);
//                i.putExtra("test2", dataElement[2]);
//                i.setData(Uri.parse("SMSTO:" + dataElement[1]));
//                startActivity(i);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.wtf("str",result.getFormatName());
//    }
//
//    public static class myData{
//        public static String[] breakString(String s){
//
//            String[] dataElement = s.split(":");
//            return dataElement;
//        }
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
