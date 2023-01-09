package com.example.xeTraining.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "wand_info")
public class MagicWandCatalogue
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wand_id")
    Long id;
    @NotBlank(message = "Wand name cannot be blank")
    @NotNull(message = "Wand name cannot be null")
    @Size(min = 5, message = "Wand name must be more than 5 characters")
    @Column(name = "wand_name")
    String name;
    @NotNull(message = "Wand description cannot be null")
    @NotBlank(message = "Wand description cannot be blank")
    @Size(min = 0, max = 200, message = "Wand description must be between 0-200 characters")
    @Column(name = "wand_desc")
    String desc;
    @Min(value = 18, message = "Age limit cannot be less than 18")
    @Max(value = 100, message = "Age limit cannot be more than 100")
    @Column(name = "age_limit")
    int age_limit;

    @PositiveOrZero(message = "Wand stock cannot be less than 0")
    @Column(name = "wand_stock")
    int stock;

    public MagicWandCatalogue() {
    }

    public MagicWandCatalogue(Long id, String name, String desc, int age_limit, int stock) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.age_limit = age_limit;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(int age_limit) {
        this.age_limit = age_limit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
