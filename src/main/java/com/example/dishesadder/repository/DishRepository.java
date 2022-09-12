package com.example.dishesadder.repository;

import com.example.dishesadder.DTO.Dish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DishRepository extends JpaRepository<Dish, Integer> {
}