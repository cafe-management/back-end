package com.example.case_module6.service.implement;

import com.example.case_module6.model.CartItem;
import com.example.case_module6.model.Drink;
import com.example.case_module6.model.News;
import com.example.case_module6.repository.CartItemRepository;
import com.example.case_module6.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);

    }

    @Override
    public void update(Long id, CartItem cartItem) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isPresent()) {
            cartItem.setId(id);
            cartItemRepository.save(cartItem);
        } else {
            throw new RuntimeException("CartItem not found with id: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + id));
    }
    @Override
    public List<com.example.case_module6.DTO.BestSellingDrinkDTO> findTopProducts(int limit) {
        List<Object[]> results = cartItemRepository.findTopProducts(PageRequest.of(0, limit));
        List<com.example.case_module6.DTO.BestSellingDrinkDTO> topProducts = new ArrayList<>();
        for (Object[] result : results) {
            Drink drink = (Drink) result[0];
            Long totalQuantity = (Long) result[1];
            topProducts.add(new com.example.case_module6.DTO.BestSellingDrinkDTO(drink, totalQuantity));
        }
        return topProducts;
    }
}