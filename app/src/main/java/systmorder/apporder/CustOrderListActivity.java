package systmorder.apporder;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mansoull on 15/6/2017.
 */

public class CustOrderListActivity extends AppCompatActivity {

    private static DatabaseReference databaseReference;
    private RecyclerView rvAddtoCart;
    private Button button2;
    private Boolean btnProcess = false;

    public static String strListMenuName = "";
    public static String strListMenuPrice = "";
    public static String strListMenuAmount = "";
    public static String menuNam = "";
    public static String menuPri = "";
    public static String menuQty = "";
    public static String menuNames = "";
    public static String menuPrices = "";
    public static String menuQtys = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_order_list);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvAddtoCart = (RecyclerView) findViewById(R.id.rvAddtoCart);
        rvAddtoCart.setHasFixedSize(true);
        rvAddtoCart.setLayoutManager(new LinearLayoutManager(this));

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseReference dbView = databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("OrderList").child(CustChooseRestaurant.vieworderId);
                dbView.child("viewOrderId").setValue(CustChooseRestaurant.vieworderId);
                dbView.child("tblNo").setValue(CustChooseRestaurant.qrCodeTableNo);
                dbView.child("userID").setValue(CustChooseRestaurant.userId);
                dbView.child("orderStatus").setValue("New Order");

                final DatabaseReference dbViewKitchen = databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("OrderList")
                        .child(CustChooseRestaurant.vieworderId).child("viewOrderMenu");

//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        menuNam = dataSnapshot.getValue().toString();
//                        Log.v("yolo", menuNam);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
                databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId)
                        .child("OrderMenu").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot menuData : dataSnapshot.getChildren()){

                            menuNam = menuData.child("menuName").getValue(String.class);
                            menuPri = menuData.child("menuPrice").getValue(String.class);
                            menuQty = menuData.child("menuQuantity").getValue(String.class);

                            DatabaseReference lol2 = dbViewKitchen.child(menuNam);

                            lol2.child("menuName").setValue(menuNam);
                            lol2.child("menuPrice").setValue(menuPri);
                            lol2.child("menuQuantity").setValue(menuQty);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<OrderList, OrderViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<OrderList, OrderViewHolder>(

                OrderList.class,
                R.layout.cust_activity_order_listrow,
                OrderViewHolder.class,
                databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("listTable")
                        .child(CustChooseRestaurant.orderId).child("OrderMenu")
        ) {
            @Override
            protected void populateViewHolder(final OrderViewHolder viewHolder, final OrderList model, final int position) {

                viewHolder.setMenuName(model.getMenuName());
                viewHolder.setMenuPrice(model.getMenuPrice());
                viewHolder.setMenuQuantity(model.getMenuQuantity());

                strListMenuName = model.getMenuName();
                strListMenuPrice = model.getMenuPrice();
                strListMenuAmount = model.getMenuQuantity();

                viewHolder.btnUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId)
                                .child("OrderMenu").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                                    OrderList orderIncrease = snapshot.getValue(OrderList.class);

                                    if (model.getMenuName().equals(orderIncrease.getMenuName())){

                                        int[] intListOrderMenu = {Integer.parseInt(orderIncrease.getMenuQuantity())};

                                        Log.v("checkItemName", orderIncrease.getMenuName());
                                        Log.v("checkItemQuantity", orderIncrease.getMenuQuantity());

                                        Double dbOldQuantityMenuIncreased = Double.parseDouble(orderIncrease.getMenuQuantity().toString());
                                        Double dbOldPriceMenu = Double.parseDouble(orderIncrease.getMenuPrice().toString().substring(2));
                                        String quantityMenu = orderIncrease.getMenuQuantity();

                                        intListOrderMenu[0] += 1;
                                        quantityMenu = Integer.toString(intListOrderMenu[0]);
                                        Log.v("checkItemQuantity1", quantityMenu);

                                        Double dbNewQuantityIncreased = Double.parseDouble(quantityMenu);

                                        Double getOriPrice = dbOldPriceMenu / dbOldQuantityMenuIncreased;

                                        Log.v("checkOriPrice", String.format("%.2f",getOriPrice).toString());

                                        Double dbAnswerIncreased = dbNewQuantityIncreased - dbOldQuantityMenuIncreased;

                                        Log.v("checkNewQ", dbNewQuantityIncreased.toString());
                                        Log.v("checkOldQ", dbOldQuantityMenuIncreased.toString());

                                        Double getQuantityIncreased = dbOldQuantityMenuIncreased + dbAnswerIncreased;

                                        Log.v("checkAns", dbAnswerIncreased.toString());
                                        Log.v("checkGetQ", getQuantityIncreased.toString());

                                        Double realPriceEachItem = getOriPrice * getQuantityIncreased;

                                        Log.v("checkOldPrice", String.format("%.2f",dbOldPriceMenu).toString());
                                        Log.v("checkFinalPrice", String.format("%.2f",realPriceEachItem).toString());
//
                                        DatabaseReference dbRefUpd = databaseReference.child(CustChooseRestaurant.qrCodeResId)
                                                .child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId)
                                                .child("OrderMenu").child(orderIncrease.getMenuName());

                                        dbRefUpd.child("menuQuantity").setValue(quantityMenu);
                                        dbRefUpd.child("menuPrice").setValue("RM " + String.format("%.2f",realPriceEachItem).toString());
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

                viewHolder.btnDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId)
                                .child("OrderMenu").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                                    OrderList orderDecrease = snapshot.getValue(OrderList.class);

                                    if (model.getMenuName().equals(orderDecrease.getMenuName())){

                                        int[] intListOrderMenu = {Integer.parseInt(orderDecrease.getMenuQuantity())};

                                        Log.v("DcheckItemName", orderDecrease.getMenuName());
                                        Log.v("DcheckItemQuantity", orderDecrease.getMenuQuantity());
                                        Log.v("DcheckItemPrice", orderDecrease.getMenuPrice());

                                        Double dbOldQuantityMenuDecreased = Double.parseDouble(orderDecrease.getMenuQuantity().toString());
                                        Double dbOldPriceMenuDecreased = Double.parseDouble(orderDecrease.getMenuPrice().toString().substring(2));
                                        String quantityMenu = orderDecrease.getMenuQuantity();

                                        intListOrderMenu[0] -= 1;
                                        quantityMenu = Integer.toString(intListOrderMenu[0]);
                                        Log.v("DcheckItemQuantity1", quantityMenu);

                                        Double dbNewQuantityDecreased = Double.parseDouble(quantityMenu);

                                        Double getOriPrice = dbOldPriceMenuDecreased / dbOldQuantityMenuDecreased;

                                        Log.v("DcheckOriPrice", String.format("%.2f",getOriPrice).toString());

                                        Double dbAnswerDecreased = dbOldPriceMenuDecreased - dbNewQuantityDecreased;

                                        Log.v("DcheckNewQ", dbNewQuantityDecreased.toString());
                                        Log.v("DcheckOldQ", dbOldPriceMenuDecreased.toString());

                                        Double getQuantityDecreased = dbOldPriceMenuDecreased - dbAnswerDecreased;

                                        Log.v("DcheckAns", dbAnswerDecreased.toString());
                                        Log.v("DcheckGetQ", getQuantityDecreased.toString());

                                        Double realPriceEachItem = getOriPrice * getQuantityDecreased;

                                        Log.v("DcheckOldPrice", String.format("%.2f",dbOldPriceMenuDecreased).toString());
                                        Log.v("DcheckFinalPrice", String.format("%.2f",realPriceEachItem).toString());
//
                                        DatabaseReference dbRef = databaseReference.child(CustChooseRestaurant.qrCodeResId)
                                                .child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId)
                                                .child("OrderMenu").child(orderDecrease.getMenuName());

                                        dbRef.child("menuQuantity").setValue(quantityMenu);
                                        dbRef.child("menuPrice").setValue("RM " + String.format("%.2f",realPriceEachItem).toString());
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

                viewHolder.btnDeleteMenuItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId)
                                .child("OrderMenu").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                                    OrderList orderTemp = snapshot.getValue(OrderList.class);

                                    if (model.getMenuName().equals(orderTemp.getMenuName())){
                                        databaseReference.child(CustChooseRestaurant.qrCodeResId).child("tblOrder").child("listTable").child(CustChooseRestaurant.orderId)
                                                .child("OrderMenu").child(orderTemp.getMenuName()).removeValue();

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        };
        rvAddtoCart.setAdapter(firebaseRecyclerAdapter);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        View fView;
        Button btnUp,btnDown,btnDeleteMenuItem;

//        int intListOrderMenu = Integer.parseInt(CustHomeTabMenuAdd.strIntQuantity);

        public OrderViewHolder(View itemView) {
            super(itemView);
            fView = itemView;
            btnUp = (Button) fView.findViewById(R.id.btnUp);
            btnDown = (Button) fView.findViewById(R.id.btnDown);
            btnDeleteMenuItem = (Button) fView.findViewById(R.id.btnDeleteMenuItem);

        }

        public void setMenuName(String menuName) {
            TextView orderMenuItemList = (TextView) fView.findViewById(R.id.orderMenuItemList);
            orderMenuItemList.setText(menuName);
        }

        public void setMenuPrice(String menuPrice) {
            TextView orderMenuPriceList = (TextView) fView.findViewById(R.id.orderMenuPriceList);
            orderMenuPriceList.setText(menuPrice);
        }

        public void setMenuQuantity(String menuQuantity) {
           final TextView orderMenuQuantityList = (TextView) fView.findViewById(R.id.orderMenuQuantityList);
            orderMenuQuantityList.setText(menuQuantity);

        }
    }
}
