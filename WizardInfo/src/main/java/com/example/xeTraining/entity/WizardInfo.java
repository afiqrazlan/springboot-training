package com.example.xeTraining.entity;

import lombok.Data;

import javax.persistence.*;
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
    Date joined_date;
    @Column(name = "wizard_status")
    String status;

    public WizardInfo() {
    }

    public WizardInfo(Long id, String name, int age, Date joined_date, String status) {
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

    public Date getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(Date joined_date) {
        this.joined_date = joined_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
