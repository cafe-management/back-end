package com.example.case_module6.service.implement;

import com.example.case_module6.model.Cart;
import com.example.case_module6.repository.CartRepository;
import com.example.case_module6.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void update(Long id, Cart cart) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isPresent()){
            cart.setId(id);
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found with id: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    }

    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }
}
