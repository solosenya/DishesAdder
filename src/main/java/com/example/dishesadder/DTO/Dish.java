package com.example.dishesadder.DTO;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dishesDB")
@Data
public class Dish {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name")
    String name;

    @Column(name = "proteins")
    double proteins;

    @Column(name = "carbs")
    double carbs;

    @Column(name = "lipids")
    double lipids;

    @Column(name = "kiloCalories")
    double calories;

}
