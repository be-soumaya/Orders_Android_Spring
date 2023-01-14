package com.example.orders_backend.service;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public", catalog = "exercice")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "customer")
    private String customer;
    @Basic
    @Column(name = "products")
    private String products;
    @Basic
    @Column(name = "amount")
    private Double amount;
    @Basic
    @Column(name = "date")
    private String date;

    public long getId() {
        return id;
    }


    public void setId(long id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id && Objects.equals(customer, that.customer) && Objects.equals(products, that.products) && Objects.equals(amount, that.amount) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, products, amount, date);
    }

    @Override
    public String toString() {
        return "OrdersEntity{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", products=" + products +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
