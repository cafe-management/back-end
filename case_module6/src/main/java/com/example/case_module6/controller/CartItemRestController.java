package com.example.case_module6.controller;

import com.example.case_module6.dto.BestSellingDrinkDTO;
import com.example.case_module6.model.CartItem;

import com.example.case_module6.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartItems")
@CrossOrigin(origins = "*")
public class CartItemRestController {
    @Autowired
    private ICartItemService cartItemService;


    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
        return ResponseEntity.ok(cartItemService.findById(id));
    }
    @GetMapping("/top")
    public ResponseEntity<List<BestSellingDrinkDTO>> getTopCartItems() {
        return new ResponseEntity<>(cartItemService.getTopBestSellingDrinks(), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Void> createCartItem(@RequestBody CartItem cartItem) {
        cartItemService.save(cartItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        cartItemService.update(id, cartItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.delete(id);
        return ResponseEntity.ok().build();
    }

}
