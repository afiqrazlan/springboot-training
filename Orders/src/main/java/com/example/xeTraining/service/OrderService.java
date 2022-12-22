package com.example.xeTraining.service;

import com.example.xeTraining.entity.Orders;
import com.example.xeTraining.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    @Autowired
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Orders> getAllOrder ()
    {
        return orderRepo.findAll();
    }

    public Orders getOrderById (Long id)
    {
        return orderRepo.findById(id).orElse(null);
    }

    public void addOrderInfo(Orders order)
    {
        orderRepo.save(order);
    }

    public void updateOrderInfo(Long id, Orders order)
    {
        Orders orderUpdated = new Orders();
        orderUpdated.setId(id);

        orderRepo.save(orderUpdated);
    }

    public void deleteOrderInfo(Long id)
    {
        orderRepo.deleteById(id);
    }
}
