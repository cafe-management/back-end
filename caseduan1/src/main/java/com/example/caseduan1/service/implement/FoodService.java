package com.example.caseduan1.service.implement;

import com.example.caseduan1.dto.FoodDTO;
import com.example.caseduan1.model.Food;
import com.example.caseduan1.repository.FoodRepository;
import com.example.caseduan1.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodService implements IFoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<FoodDTO> getAllFoods() {
        return foodRepository.findAll().stream().map(FoodDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food updateFood(Long id, Food updatedFood) {
        return foodRepository.findById(id).map(food -> {
            food.setName(updatedFood.getName());
            food.setCategory(updatedFood.getCategory());
            food.setDescription(updatedFood.getDescription());
            food.setPrice(updatedFood.getPrice());
            food.setQuantity(updatedFood.getQuantity());
            food.setRating(updatedFood.getRating());
            food.setStatus(updatedFood.getStatus());
            return foodRepository.save(food);
        }).orElse(null);
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public List<FoodDTO> searchFoodByName(String name) {
        return foodRepository.findByNameContainingIgnoreCase(name).stream().map(FoodDTO::new).collect(Collectors.toList());
    }
}
