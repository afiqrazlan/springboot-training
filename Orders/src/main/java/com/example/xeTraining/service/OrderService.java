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
    private OrderRepository orderRepo;
    @Autowired
    private WizardService wizardService;
    @Autowired
    private MagicWandService magicWandService;

    RestTemplate restTemplate = new RestTemplate();

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
        boolean isOrderExist = checkExistingOrder(order);
        if(isOrderExist == true)
        {
            WizardInfo wizardInfo = wizardService.getWizardByID(order.getWizardId()).getBody();
            MagicWandCatalogue magicWand = magicWandService.getWandByID(order.getWandId()).getBody();

            if (wizardInfo != null && magicWand != null)
            {
                boolean isOrderValid = validateOrderInfo(order, wizardInfo, magicWand);
                if (isOrderValid == true)
                {
                    magicWand.setStock(magicWand.getStock() - order.getQuantity());
                    magicWandService.updateMagicWandStock(magicWand);
                    orderRepo.save(order);
                }
            }
        }
    }

    public void updateOrderInfo(Long id, Orders order)
    {
        Orders existingOrder = getOrderById(id);
        if(existingOrder != null)
        {
            WizardInfo wizardInfo = wizardService.getWizardByID(order.getWizardId()).getBody();
            MagicWandCatalogue magicWand = magicWandService.getWandByID(order.getWandId()).getBody();

            if(wizardInfo != null && magicWand != null)
            {
                boolean isOrderValid = validateOrderInfo(order, wizardInfo, magicWand);
                if (isOrderValid == true) {
                    Orders orderUpdated = new Orders();
                    orderUpdated.setId(id);
                    orderUpdated.setWizardId(order.getWizardId());
                    orderUpdated.setWandId(order.getWandId());
                    orderUpdated.setQuantity(order.getQuantity());

                    if(existingOrder.getQuantity() < orderUpdated.getQuantity())
                    {
                        magicWand.setStock(magicWand.getStock() - (orderUpdated.getQuantity() - existingOrder.getQuantity()));
                        magicWandService.updateMagicWandStock(magicWand);
                    }

                    else if(existingOrder.getQuantity() > orderUpdated.getQuantity())
                    {
                        magicWand.setStock(magicWand.getStock() + (existingOrder.getQuantity() - orderUpdated.getQuantity()));
                        magicWandService.updateMagicWandStock(magicWand);
                    }

                    orderRepo.save(orderUpdated);
                }
            }
        }

    }

    public void deleteOrderInfo(Long id)
    {
        Orders order = getOrderById(id);
        MagicWandCatalogue magicWand = magicWandService.getWandByID(order.getWandId()).getBody();
        if(magicWand != null)
        {
            magicWand.setStock(magicWand.getStock() + order.getQuantity());
            magicWandService.updateMagicWandStock(magicWand);
        }

        orderRepo.deleteById(id);
    }

    public boolean validateOrderInfo(Orders order, WizardInfo wizardInfo, MagicWandCatalogue magicWand)
    {
        if(!wizardInfo.isStatus())
            throw new BadRequestException("Cannot Add Order: Wizard is inactive");
        else if(magicWand.getStock() == 0)
            throw new BadRequestException("Cannot Add Order: Wand is out of stock");
        else if(wizardInfo.getAge() < magicWand.getAge_limit())
            throw new BadRequestException("Cannot Add Order: Wizard's age is below the age requirement");
        else if(order.getQuantity() > magicWand.getStock())
            throw new BadRequestException("Cannot Add Order: Quantity requested is more than available wand stock");
        else
            return true;
    }

    public boolean checkExistingOrder(Orders order)
    {
        List<Orders> existingOrders = orderRepo.findOrdersByWizardId(order.getWizardId());

        for(Orders checkOrder: existingOrders)
        {
            if (checkOrder.getWizardId().equals(order.getWizardId())
                    && checkOrder.getWandId().equals(order.getWandId()))
            {
                throw new BadRequestException("Wizard already ordered this wand, please use update to change anything.");
            }

            else
                break;
        }

        return true;
    }
}
