package com.example.multi_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.multi_activity.adapters.OrderAdapter;
import com.example.multi_activity.models.Order;
import com.example.multi_activity.retrofit.OrderAPI;
import com.example.multi_activity.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {
    ListView orderslistView;
    FloatingActionButton b1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);
        orderslistView = (ListView) findViewById(R.id.OrdersList);
        b1 = findViewById(R.id.Orders_add);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect2 = new Intent(getApplicationContext(), OrderFormActivity.class);
                startActivity(redirect2);

            }
        });

    }

    private void loadOrders() {
        RetrofitService retrofitService = new RetrofitService();
        OrderAPI orderApi = retrofitService.getRetrofit().create(OrderAPI.class);
        orderApi.getAll().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                populateListView(response.body());
                TextView count = findViewById(R.id.count);
                count.setText(String.valueOf(response.body().size()));

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(WelcomeActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView(List<Order> orderList) {
        OrderAdapter orderAdapter = new OrderAdapter(this, R.layout.welcome_activity, orderList);
        orderslistView.setAdapter(orderAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadOrders();
    }


}
