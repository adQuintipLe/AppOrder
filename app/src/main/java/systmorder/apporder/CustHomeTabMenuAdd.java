package systmorder.apporder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by mansoull on 15/6/2017.
 */

public class CustHomeTabMenuAdd extends Fragment {

    private DatabaseReference databaseReference;
    private ImageView imgCustView;
    private TextView tvCustMenuItem, tvCustMenuPrice, tvQuantityMenuItem;
    private Button addCustOrder,customizeCustOrder, increaseQuantity, decreaseQuantity;

    public static String strCustomizeCustOrder = "";
    int intQuantityItem;
    public static String strIntQuantity;
    public static Double dbTotalPriceEachMenu = 0.00;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_fragment_home_tab_menu_add,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle(CustHomeTabMenu.strCustMenuItem);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        imgCustView = (ImageView) v.findViewById(R.id.imgCustView);
        Picasso.with(getActivity()).load(CustHomeTabMenu.strCustMenuImage).into(imgCustView);

        tvCustMenuItem = (TextView) v.findViewById(R.id.tvCustMenuItem);
        tvCustMenuItem.setText(CustHomeTabMenu.strCustMenuItem);

        tvCustMenuPrice = (TextView) v.findViewById(R.id.tvCustMenuPrice);
        tvCustMenuPrice.setText(CustHomeTabMenu.strCustMenuPrice);

        tvQuantityMenuItem = (TextView) v.findViewById(R.id.tvQuantityMenuItem);
        tvQuantityMenuItem.setText(CustOrderListActivity.strListMenuAmount);

        increaseQuantity = (Button) v.findViewById(R.id.increaseQuantity);
        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == increaseQuantity){
                    intQuantityItem += 1;
                    strIntQuantity = Integer.toString(intQuantityItem);
                    tvQuantityMenuItem.setText(strIntQuantity);

                    Double dbMenuQuantityIncrease = Double.parseDouble(strIntQuantity);

                    dbTotalPriceEachMenu = CustHomeTabMenu.dbMenuPrice * dbMenuQuantityIncrease;

                    Log.v("cektotalPrice", String.format("%.2f",dbTotalPriceEachMenu).toString());
                }

            }
        });

        decreaseQuantity = (Button) v.findViewById(R.id.decreaseQuantity);
        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == decreaseQuantity){
                    intQuantityItem -= 1;
                    strIntQuantity = Integer.toString(intQuantityItem);
                    tvQuantityMenuItem.setText(strIntQuantity);

                    Double dbMenuQuantityDecrease = Double.parseDouble(strIntQuantity);

                    dbTotalPriceEachMenu = CustHomeTabMenu.dbMenuPrice * dbMenuQuantityDecrease;

                    Log.v("cektotalPrice1", String.format("%.2f",dbTotalPriceEachMenu).toString());
                }
            }
        });


        customizeCustOrder = (Button) v.findViewById(R.id.customizeCustOrder);
        customizeCustOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                final View viewDialog = layoutInflater.inflate(R.layout.testcustomizecustorder, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                alertDialogBuilder.setView(viewDialog);

                final EditText custInput = (EditText) viewDialog.findViewById(R.id.etCustomizeCustOrder);

                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        strCustomizeCustOrder = custInput.getText().toString();

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        addCustOrder = (Button) v.findViewById(R.id.addCustOrder);
        addCustOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference dbOrderId = databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId);

                dbOrderId.child("tblNo").setValue(CustChooseRestaurant.qrCodeTableNo);
                dbOrderId.child("orderID").setValue(CustChooseRestaurant.orderId);
                dbOrderId.child("userID").setValue(CustChooseRestaurant.userId);
                dbOrderId.child("orderStatus").setValue("Pending...");

                DatabaseReference dbOrderMenu = databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId)
                        .child("OrderMenu").child(CustHomeTabMenu.strCustMenuItem);
                dbOrderMenu.child("menuName").setValue(CustHomeTabMenu.strCustMenuItem);
                dbOrderMenu.child("menuPrice").setValue("RM " + String.format("%.2f", dbTotalPriceEachMenu).toString());
                dbOrderMenu.child("menuQuantity").setValue(strIntQuantity);

                Log.d("test2", CustHomeTabMenu.strCustMenuItem);
                Log.d("test3", String.valueOf(CustHomeTabMenu.strCustMenuPrice));

                Toast.makeText(getActivity(), "item added", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
