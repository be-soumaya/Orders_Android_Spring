package com.example.orders_backend.service;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("orders")
public class MyController {
    private final OrderRepository repo;

    public MyController(OrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/all")
    public List<OrdersEntity> getAll() {
        return repo.findAll();
    }

    @PostMapping("/save")
    public OrdersEntity save(@RequestBody OrdersEntity order) {
        System.out.println(order.toString());
        return repo.save(order);
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    public void deleteOrderById(@PathVariable("id") Integer orderId) {
        repo.deleteById(orderId);
    }


}
