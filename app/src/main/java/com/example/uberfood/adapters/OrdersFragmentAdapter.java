package com.example.uberfood.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uberfood.R;
import com.example.uberfood.models.PlacedOrder;
import com.example.uberfood.models.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;


public class OrdersFragmentAdapter extends RecyclerView.Adapter<OrdersFragmentAdapter.MyViewHolder> {


    Context context ;
    ArrayList<PlacedOrder> lisrOfPlacedOrder ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public OrdersFragmentAdapter(Context context, ArrayList<PlacedOrder> lisrOfPlacedOrder) {
        this.context = context;
        this.lisrOfPlacedOrder = lisrOfPlacedOrder;

    }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
            View item = null;


            item = LayoutInflater.from(context).inflate(R.layout.orders_item, parent, false);


            return new OrdersFragmentAdapter.MyViewHolder(item);
        }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final PlacedOrder placedOrder = lisrOfPlacedOrder.get(position);
        String idRestaurant = placedOrder.getRestaurant_id();
        db.collection(RESTAURANT_KEY).document(idRestaurant).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    Restaurant restaurant = task.getResult().toObject(Restaurant.class);
                    holder.speciality.setText(restaurant.getCuisine());
                    holder.name.setText(restaurant.getName());
                    holder.price.setText(placedOrder.getPrice()+"");
                    holder.price.setText(placedOrder.getOrder_time()+"");




                }


            }
        });
    }



        @Override
        public int getItemCount () {
            //return listOfRestaurants.size();

            return lisrOfPlacedOrder.size();

        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView speciality , name , date , price ;
            public MyViewHolder(View itemView) {
                super(itemView);
                speciality = itemView.findViewById(R.id.speciality_from_orders_fragment);
                name = itemView.findViewById(R.id.name_from_orders_fragment);
                date = itemView.findViewById(R.id.date_from_orders_fragment);
                price = itemView.findViewById(R.id.price_from_orders_fragment);


            }
        }
    }
