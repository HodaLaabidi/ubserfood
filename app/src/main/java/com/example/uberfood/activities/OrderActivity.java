package com.example.uberfood.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.OrderActivityAdapter;
import com.example.uberfood.models.Menu;
import com.example.uberfood.models.PlacedOrder;
import com.example.uberfood.models.Restaurant;
import com.example.uberfood.models.User;
import com.example.uberfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.uberfood.R.string.send_order_message;
import static com.example.uberfood.activities.MenuActivity.id_restaurant;
import static com.example.uberfood.utils.Constants.PLACED_ORDER_KEY;
import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;
import static com.example.uberfood.utils.Constants.USER_COLLECTION;

public class OrderActivity extends AppCompatActivity {

    private static final double TAX_AMOUNT = 0.02 ;
    private static final double DELIVERY_FREE = 6.0;
    LinearLayout arrowBack ;
    LinearLayout cancel , ok ;
    PlacedOrder placedOrder = new PlacedOrder();
    User user = new User();
    double operationResult = 0;
    RecyclerView recyclerView;
    Restaurant restaurant = new Restaurant() ;
    AppCompatTextView subtotal , taxAmount , deliveryFree , Total ;
    LinkedHashMap<Menu, Integer> listOfOrderedMenu = new LinkedHashMap<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initializeViews();
        calculateValues();

    }

    private void initializeViews() {

        subtotal = findViewById(R.id.subtotal);
        taxAmount = findViewById(R.id.tax_amount) ;
        deliveryFree = findViewById(R.id.delivery_free);
        Total = findViewById(R.id.total);

        arrowBack = findViewById(R.id.return_icon_order_activity);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cancel = findViewById(R.id.activity_order_cancel);
        ok = findViewById(R.id.activity_order_ok);
        recyclerView = findViewById(R.id.recycler_view_orders);
        DocumentReference docRef = FirebaseFirestore.getInstance().collection(USER_COLLECTION).document(FirebaseAuth.getInstance().getCurrentUser().getUid()+"");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

               if (documentSnapshot.getData() != null){
                   Log.e( "DocumentSnapshot data: " , documentSnapshot.getData().toString());
                   user =  documentSnapshot.toObject(User.class);
               }



            }});
        DocumentReference docRef2 = FirebaseFirestore.getInstance().collection(RESTAURANT_KEY).document(id_restaurant);
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.e( "DocumentSnapshot data: " , document.getData()+"!");
                        restaurant = document.toObject(Restaurant.class);
                        if (restaurant != null){


                        }

                    } else {
                        Log.e( "No such document","no");
                    }
                } else {
                    Log.e( "get failed with ", task.getException()+"!");
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setPlacedOrderObject();
                sendPlacedOrderObjectToBackend();
                Utils.price = 0;
                MenuActivity.priceText.setText(Utils.price + " DT");
                Utils.listOfOrderedMenu.clear();
                Snackbar snackbar = Snackbar.make(MenuActivity.priceText, R.string.send_order_message, Snackbar.LENGTH_LONG)

                        .setAction(R.string.undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Respond to the click, such as by undoing the modification that caused
                                // this message to be displayed
                            }
                        });
                snackbar.show();
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }});



        LinearLayoutManager mLayoutManager = new LinearLayoutManager(OrderActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);

        OrderActivityAdapter orderActivityAdapter = new OrderActivityAdapter(OrderActivity.this ,  Utils.listOfOrderedMenu) ;
        recyclerView.setAdapter(orderActivityAdapter);
    }

    private void sendPlacedOrderObjectToBackend() {
        if (placedOrder != null){
            FirebaseFirestore.getInstance().collection(PLACED_ORDER_KEY)
                    .add(placedOrder)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.e("test" ,"DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("test", "Error writing document", e);
                        }
                    });
        }





    }

    private void setPlacedOrderObject() {


        placedOrder.setDelivery_address(restaurant.getAdress_street());
        placedOrder.setEstimated_delivery_time("01:00h");
        placedOrder.setFood_ready(true);
        HashMap<String , Integer> listOfOrderedMenu = new HashMap<>();
        for (Map.Entry<Menu, Integer> item : Utils.listOfOrderedMenu.entrySet()) {
            String key = item.getKey().getId();
            Integer value = item.getValue();
            listOfOrderedMenu.put(key , value);
        }
        placedOrder.setMenu_item(listOfOrderedMenu);
        DateFormat df = new SimpleDateFormat("dd MM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        placedOrder.setOrder_time(Calendar.getInstance().getTime());
        placedOrder.setFinal_price(operationResult);
        placedOrder.setPrice(Utils.price);
        placedOrder.setRestaurant_id(restaurant.getId());
    }

    private void calculateValues() {


        subtotal.setText(Utils.price+" DT");
        taxAmount.setText(TAX_AMOUNT +" DT");
        deliveryFree.setText(DELIVERY_FREE +" DT");
         operationResult = Utils.price + (Utils.price*TAX_AMOUNT) + DELIVERY_FREE ;
        Total.setText(operationResult+" DT");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
