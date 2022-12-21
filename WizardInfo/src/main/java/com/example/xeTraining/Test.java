package com.example.xeTraining;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "test_table")
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
}
