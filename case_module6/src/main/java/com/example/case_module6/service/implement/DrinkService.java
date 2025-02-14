package com.example.case_module6.service.implement;

import com.example.case_module6.model.Drink;
import com.example.case_module6.repository.DrinkRepository;
import com.example.case_module6.service.IDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkService implements IDrinkService {
    @Autowired
    private DrinkRepository drinkRepository;
    @Override
    public List<Drink> getAll() {
        return drinkRepository.findAll();
    }

    @Override
    public void save(Drink entity) {
         drinkRepository.save(entity);
    }

    @Override
    public void update(Long id, Drink entity) {
        Optional<Drink> drink = drinkRepository.findById(id);
        if (drink.isPresent()) {
            entity.setId(id);
            drinkRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        drinkRepository.deleteById(id);
    }

    @Override
    public Drink findById(Long id) {
        Optional<Drink> drink = drinkRepository.findById(id);
        return drink.orElse(null);
    }

    @Override
    public List<Drink>findDrinkByCategory(Long categoryId) {
        return drinkRepository.findByCategoryId(categoryId);
    }
}
