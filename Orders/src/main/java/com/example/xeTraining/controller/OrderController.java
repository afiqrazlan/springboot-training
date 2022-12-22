package com.example.xeTraining.controller;

import com.example.xeTraining.entity.Orders;
import com.example.xeTraining.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/test/{id}")
    public ResponseEntity<?> test(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/orderList")
    public List<Orders> getOrderList ()
    {
        return orderService.getAllOrder();
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody Orders order)
    {
        orderService.addOrderInfo(order);
    }

    @PutMapping("/update/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Orders order)
    {
        orderService.updateOrderInfo(id, order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable(value = "order_id") Long id)
    {
        orderService.deleteOrderInfo(id);
    }
}
