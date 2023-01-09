package com.example.xeTraining.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "wizard_info")
@Data
public class WizardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wizard_id")
    Long id;
    @NotNull(message = "Wizard name cannot be null")
    @NotBlank(message = "Wizard name cannot be blank")
    @Size(min =3, max= 100, message = "Name must be between 3 - 100 characters")
    @Column(name = "wizard_name")
    String name;
    @Column(name = "wizard_age")
    @Min(value = 18, message = "Wizard must be 18 or above")
    @Max(value = 100, message = "Wizard age cannot exceed 100")
    int age;
    @Column(name = "joined_date")
    String joinedDate;
    @Column(name = "wizard_status")
    boolean status;

    public WizardInfo() {
    }

    public WizardInfo(Long id, String name, int age, String joined_date, boolean status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.joinedDate = joined_date;
        this.status = status;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
