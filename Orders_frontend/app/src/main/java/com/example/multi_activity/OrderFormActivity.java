package com.example.multi_activity;


import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import com.example.multi_activity.models.Order;
import com.example.multi_activity.retrofit.RetrofitService;
import com.example.multi_activity.retrofit.OrderAPI;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditTextCustomer = findViewById(R.id.form_textFieldCustomer);
        TextInputEditText inputEditProducts = findViewById(R.id.form_textFieldProducts);
        TextInputEditText inputEditAmount = findViewById(R.id.form_textFieldAmount);
        TextInputEditText inputEditDate = findViewById(R.id.form_textFieldDate);
        AppCompatButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        OrderAPI orderapi = retrofitService.getRetrofit().create(OrderAPI.class);

        buttonSave.setOnClickListener(view -> {
            String customer = String.valueOf(inputEditTextCustomer.getText());
            String products = String.valueOf(inputEditProducts.getText());
            String amount = String.valueOf(inputEditAmount.getText());
            String date = String.valueOf(inputEditDate.getText());

            Order order = new Order();
            order.setCustomer(customer);
            order.setProducts(products);
            order.setAmount(Double.valueOf(amount));
            order.setDate(date);

            orderapi.save(order)
                    .enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            Toast.makeText(OrderFormActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
                            Intent redirect = new Intent(getApplicationContext(),WelcomeActivity.class);
                            startActivity(redirect);
                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Toast.makeText(OrderFormActivity.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(OrderFormActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });
        });
    }
}