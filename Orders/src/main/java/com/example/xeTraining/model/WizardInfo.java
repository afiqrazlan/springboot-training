package com.example.xeTraining.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wizard_info")
@Data
@Valid
public class WizardInfo {
    @Id
    Long id;
    String name;
    int age;
    String joined_date;
    boolean status;
}
