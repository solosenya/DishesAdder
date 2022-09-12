package com.example.dishesadder.service;


import com.example.dishesadder.DTO.Dish;
import com.example.dishesadder.DTO.ParameterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class DishService {

    private static final Logger logger = LoggerFactory.getLogger(DishService.class);

    private Dish dish;

    public static List<Dish> parseDishes(String url) throws IOException {

        List<Dish> dishes = new ArrayList<>();

        List<String> names = DishService.createNames(url);
        List<Double> proteins = DishService.createParameters(url, ParameterType.protein);
        List<Double> lipids = DishService.createParameters(url, ParameterType.fat);
        List<Double> carbs = DishService.createParameters(url, ParameterType.carbohydrate);
        List<Double> calories = DishService.createParameters(url, ParameterType.kcal);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).length() >= 50) {
                logger.info("Dish with name " + names.get(i) + " was skipped because it has huge name!");
                continue;
            }

            Dish newDish = new Dish();
            newDish.setName(names.get(i));
            newDish.setProteins(proteins.get(i));
            newDish.setLipids(lipids.get(i));
            newDish.setCarbs(carbs.get(i));
            newDish.setCalories(calories.get(i));
            dishes.add(newDish);
            logger.info("Dish " + newDish + " has been added!");
        }

        return dishes;
    }

    public static List<String> createNames(String url) throws IOException {
        return Jsoup
                .connect(url)
                .get()
                .getElementsByClass("views-field views-field-title active")
                .stream()
                .skip(1)
                .map(Element::text)
                .toList();
    }

    public static List<Double> createParameters(String url, ParameterType type) throws IOException {
        return Jsoup
                .connect(url)
                .get()
                .getElementsByClass("views-field views-field-field-" + type + "-value")
                .stream()
                .skip(1)
                .map(Element::text)
                .map(Double::parseDouble)
                .toList();

    }
}
