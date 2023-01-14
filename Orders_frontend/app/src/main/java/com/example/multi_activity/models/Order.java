package com.example.multi_activity.models;

import java.util.Arrays;

public class Order {

    private Integer id;
    private String customer;
    private String products;
    private Double amount;
    private String date;

    public Order(Integer id, String customer, String products, Double amount, String date) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.amount = amount;
        this.date = date;
    }

    public Order() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", products=" + products +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
