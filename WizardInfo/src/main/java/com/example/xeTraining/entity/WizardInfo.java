package com.example.xeTraining.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "wizard_info")
@Data
public class WizardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wizard_id")
    Long id;
    @Column(name = "wizard_name")
    String name;
    @Column(name = "wizard_age")
    int age;
    @Column(name = "joined_date")
    String joined_date;
    @Column(name = "wizard_status")
    boolean status;

    public WizardInfo() {
    }

    public WizardInfo(Long id, String name, int age, String joined_date, boolean status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.joined_date = joined_date;
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

    public String getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(String joined_date) {
        this.joined_date = joined_date;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
