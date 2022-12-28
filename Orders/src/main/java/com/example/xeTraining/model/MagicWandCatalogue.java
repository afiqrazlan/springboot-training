package com.example.xeTraining.model;

import javax.persistence.*;

@Entity
@Table(name = "wand_info")
public class MagicWandCatalogue
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wand_id")
    Long id;
    @Column(name = "wand_name")
    String name;
    @Column(name = "wand_desc")
    String desc;
    @Column(name = "age_limit")

    int age_limit;
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
