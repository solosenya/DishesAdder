package com.example.dishesadder.controller;

import com.example.dishesadder.DTO.Dish;
import com.example.dishesadder.repository.DishRepository;
import com.example.dishesadder.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class AdderController {

    Logger logger = LoggerFactory.getLogger(AdderController.class);

    @Autowired
    DishRepository dishRepository;

    List<Dish> dishes;

    @GetMapping
    public ResponseEntity<List<Dish>> showData(@RequestParam String url) throws IOException {

        dishes = DishService.parseDishes(url);

        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @PutMapping("/put")
    public ResponseEntity<HttpStatus> putAllCollectedDataToDB () {

        dishRepository.saveAll(dishes);
        logger.info("All this dishes have been saved in dishesDB!");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
