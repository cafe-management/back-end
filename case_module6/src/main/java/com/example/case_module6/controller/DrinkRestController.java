package com.example.case_module6.controller;

import com.example.case_module6.model.Drink;
import com.example.case_module6.repository.DrinkRepository;
import com.example.case_module6.service.IDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/drinks")
public class DrinkRestController {
    @Autowired
    private IDrinkService drinkService;
    @GetMapping
    public ResponseEntity<List<Drink>> getDrinks() {
        List<Drink> drinks = drinkService.getAll();
        if (drinks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable("id") Long id) {
        Drink drink = drinkService.findById(id);
        if (drink == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Drink>> getDrinksByCategory(@PathVariable("categoryId") Long categoryId) {
        List<Drink> drinks = drinkService.findDrinkByCategory(categoryId);
        if (drinks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }
}
