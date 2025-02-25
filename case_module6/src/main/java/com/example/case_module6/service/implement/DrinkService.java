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
        // Chỉ lấy các drink chưa bị đánh dấu xóa
        return drinkRepository.findByIsDeletedFalse();
    }

    @Override
    public void save(Drink entity) {
        drinkRepository.save(entity);
    }

    @Override
    public void update(Long id, Drink entity) {
        Optional<Drink> drink = drinkRepository.findByIdAndIsDeletedFalse(id);
        if (drink.isPresent()) {
            entity.setId(id);
            drinkRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        // Thực hiện soft delete: đánh dấu isDeleted = true
        Optional<Drink> drink = drinkRepository.findByIdAndIsDeletedFalse(id);
        if (drink.isPresent()) {
            Drink existingDrink = drink.get();
            existingDrink.setDeleted(true);
            drinkRepository.save(existingDrink);
        }
    }

    @Override
    public Drink findById(Long id) {
        Optional<Drink> drink = drinkRepository.findByIdAndIsDeletedFalse(id);
        return drink.orElse(null);
    }

    @Override
    public List<Drink> findDrinkByCategory(Long categoryId) {
        return drinkRepository.findByCategoryIdAndIsDeletedFalse(categoryId);
    }
}
