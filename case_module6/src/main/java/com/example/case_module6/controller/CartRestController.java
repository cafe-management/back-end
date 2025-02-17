package com.example.case_module6.controller;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.CartItem;
import com.example.case_module6.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "*")
public class CartRestController {
    @Autowired
    private ICartService cartService;
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        if (cart.getItems() != null) {
            for (CartItem item : cart.getItems()) {
                item.setCart(cart);
            }
        }
        Cart savedCart = cartService.saveCart(cart);
        return ResponseEntity.ok(savedCart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        cartService.update(id, cart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-table/{tableId}")
    public ResponseEntity<List<Cart>> getCartByTableId(@PathVariable Long tableId) {
        List<Cart> carts = cartService.findCartsByTableId(tableId);
        return ResponseEntity.ok(carts);
    }
}
