package com.example.xeTraining.service;

import com.example.xeTraining.exception.BadRequestException;
import com.example.xeTraining.exception.OrderNotFoundException;
import com.example.xeTraining.model.MagicWandCatalogue;
import com.example.xeTraining.model.Orders;
import com.example.xeTraining.model.WizardInfo;
import com.example.xeTraining.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService
{
    @Autowired
    private final OrderRepository orderRepo;
    @Autowired
    private final WizardService wizardService;
    @Autowired
    private final MagicWandService magicWandService;

    RestTemplate restTemplate = new RestTemplate();

    public OrderService(OrderRepository orderRepo, WizardService wizardService, MagicWandService magicWandService) {
        this.orderRepo = orderRepo;
        this.wizardService = wizardService;
        this.magicWandService = magicWandService;
    }

    public List<Orders> getAllOrder ()
    {
        return orderRepo.findAll();
    }

    public Orders getOrderById (Long id)
    {
        return orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException("No order with ID: " + id));
    }

    public void addOrderInfo(Orders order)
    {
        WizardInfo wizardInfo = wizardService.getWizardByID(order.getWizardId()).getBody();
        MagicWandCatalogue magicWand = magicWandService.getWandByID(order.getWandId()).getBody();

        if(wizardInfo != null && magicWand != null)
        {
            if(!wizardInfo.getStatus())
                throw new BadRequestException("Cannot Add Order: Wizard is inactive");
            else if(magicWand.getStock() == 0)
                throw new BadRequestException("Cannot Add Order: Wand is out of stock");
            else if(wizardInfo.getAge() < magicWand.getAge_limit())
                throw new BadRequestException("Cannot Add Order: Wizard's age is below the age requirement");
            else if(order.getQuantity() > magicWand.getStock())
                throw new BadRequestException("Cannot Add Order: Quantity requested is more than available wand stock");
            else
            {
                magicWand.setStock(magicWand.getStock() - order.getQuantity());
                restTemplate.put("http://localhost:8081/api/demo/magic-wand/update/" + magicWand.getId(), magicWand);
                orderRepo.save(order);
            }
        }
        else
            System.out.println("Error occurred");
    }

    public void updateOrderInfo(Long id, Orders order)
    {
        Orders orderUpdated = new Orders();
        orderUpdated.setId(id);
        orderUpdated.setWizardId(order.getWizardId());
        orderUpdated.setWandId(order.getWandId());

        orderRepo.save(orderUpdated);
    }

    public void deleteOrderInfo(Long id)
    {
        Orders order = getOrderById(id);
        MagicWandCatalogue magicWand = magicWandService.getWandByID(order.getWandId()).getBody();
        if(magicWand != null)
        {
            magicWand.setStock(magicWand.getStock() + order.getQuantity());
            restTemplate.put("http://localhost:8081/api/demo/magic-wand/update/" + magicWand.getId(), magicWand);
        }

        orderRepo.deleteById(id);
    }
}
