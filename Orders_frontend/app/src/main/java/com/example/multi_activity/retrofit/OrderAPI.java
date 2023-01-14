package com.example.multi_activity.retrofit;

import com.example.multi_activity.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderAPI {
    @GET("orders/all")
    Call<List<Order>> getAll();

    @POST("/orders/save")
    Call<Order> save(@Body Order order);

    @DELETE("/orders/delete/{id}")
    Call<Void> deleteOrderById(@Path("id") Integer orderId);
}
