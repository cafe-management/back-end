package com.example.case_module6.controller;

import com.example.case_module6.model.Drink;
import com.example.case_module6.service.IDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
@CrossOrigin(origins = "*")
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

    @PostMapping
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drink) {
        drinkService.save(drink);
        return new ResponseEntity<>(drink, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Drink>> getDrinksByCategory(@PathVariable("categoryId") Long categoryId) {
        List<Drink> drinks = drinkService.findDrinkByCategory(categoryId);
        if (drinks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drink> updateDrink(@PathVariable("id") Long id, @RequestBody Drink drink) {
        Drink existingDrink = drinkService.findById(id);
        if (existingDrink == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        drinkService.update(id, drink);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable("id") Long id) {
        Drink existingDrink = drinkService.findById(id);
        if (existingDrink == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        drinkService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

