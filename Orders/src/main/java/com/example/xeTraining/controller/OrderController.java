package com.example.xeTraining.controller;

import com.example.xeTraining.entity.Orders;
import com.example.xeTraining.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public void addOrder(@RequestBody Orders order)
    {
        orderRepo.save(order);
    }

    @PutMapping("/update/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Orders order)
    {
        Orders orderUpdated = new Orders();
        orderUpdated.setId(id);

        orderRepo.save(orderUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable(value = "order_id") Long id)
    {
        orderRepo.deleteById(id);
    }
}
