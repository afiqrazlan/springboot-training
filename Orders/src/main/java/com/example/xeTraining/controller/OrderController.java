package com.example.xeTraining.controller;

import com.example.xeTraining.entity.Orders;
import com.example.xeTraining.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/demo/orders")
public class OrderController {

    @Autowired
    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<?> test(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(orderRepo.findById(id).get());
    }

    @GetMapping("/orderList")
    public List<Orders> getOrderList ()
    {
        return orderRepo.findAll();
    }

}
