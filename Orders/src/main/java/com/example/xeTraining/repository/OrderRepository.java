package com.example.xeTraining.repository;

import com.example.xeTraining.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>
{
    List<Orders> findOrdersByWizardId(Long id);
}
