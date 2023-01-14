package com.example.multi_activity.adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.multi_activity.OrderFormActivity;
import com.example.multi_activity.R;
import com.example.multi_activity.WelcomeActivity;
import com.example.multi_activity.models.Order;
import com.example.multi_activity.retrofit.OrderAPI;
import com.example.multi_activity.retrofit.RetrofitService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends ArrayAdapter<Order> {
    //the list values in the List of type hero
    List<Order> ordersList;
    ImageButton btn_delete;

    //activity context
    Context context;


    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public OrderAdapter(Context context, int resource, List<Order> ordersList) {
        super(context, resource, ordersList);
        this.context = context;
        this.resource = resource;
        this.ordersList = ordersList;
    }


    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(resource, null, false);



        TextView customer = view.findViewById(R.id.customer);
        TextView products = view.findViewById(R.id.products);
        TextView amount = view.findViewById(R.id.amount);
        TextView date = view.findViewById(R.id.date);

        btn_delete = view.findViewById(R.id.btn_delete);

        Order order = ordersList.get(position);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer posID = order.getId();
                deleteItemFromList(v,posID);

            }
        });
//        id.setText(String.valueOf(order.getId()));
        customer.setText(order.getCustomer());
        products.setText((order.getProducts().replaceAll("\\[|\\]", "")).replaceAll(",","\n"));
        amount.setText(String.valueOf(order.getAmount()));
        date.setText(order.getDate());



        //finally returning the view
        return view;
    }

    // confirmation dialog box to delete an unit
    private void deleteItemFromList(View v, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        //builder.setTitle("Delete ");
        builder.setMessage("Delete Item ?")
                .setCancelable(false)
                .setPositiveButton("CONFIRM",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.out.println("postition :::::: "+position);
                                deleteOrders(position);
                                notifyDataSetChanged();
                                Intent refresh = new Intent(getContext().getApplicationContext(), WelcomeActivity.class);
                                context.startActivity(refresh);

                            }
                        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    }
                });

        builder.show();

    }
    private void deleteOrders(int Posid){
        RetrofitService retrofitService = new RetrofitService();
        OrderAPI orderApi = retrofitService.getRetrofit().create(OrderAPI.class);
        orderApi.deleteOrderById(Posid).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext().getApplicationContext(), "order "+Posid+" deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext().getApplicationContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Failed to delete order", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public int getItemCount() {
        return ordersList.size();
    }

}