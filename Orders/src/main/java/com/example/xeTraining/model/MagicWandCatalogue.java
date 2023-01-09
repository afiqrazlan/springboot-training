package com.example.xeTraining.model;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@Table(name = "wand_info")
public class MagicWandCatalogue
{
    @Id
    Long id;
    String name;
    String desc;
    int age_limit;
    int stock;
}
