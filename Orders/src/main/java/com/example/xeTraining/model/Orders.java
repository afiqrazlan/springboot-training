package com.example.xeTraining.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long id;

    @NotNull(message = "Wizard id cannot be null")
    @Column(name = "wizard_id")
    Long wizardId;
    @NotNull(message = "Wand id cannot be null")
    @Column(name = "wand_id")
    Long wandId;
    @PositiveOrZero (message = "Quantity cannot be less than 0")
    @Column(name = "quantity")
    int quantity;

    public Orders() {
    }

    public Orders(Long id, Long wizardId, Long wandId, int quantity) {
        this.id = id;
        this.wizardId = wizardId;
        this.wandId = wandId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWizardId() {
        return wizardId;
    }

    public void setWizardId(Long wizardId) {
        this.wizardId = wizardId;
    }

    public Long getWandId() {
        return wandId;
    }

    public void setWandId(Long wandId) {
        this.wandId = wandId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
