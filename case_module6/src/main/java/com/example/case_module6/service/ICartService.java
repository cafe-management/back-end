package com.example.case_module6.service;

import com.example.case_module6.model.Cart;
public interface ICartService extends IService<Cart,Long> {
    Cart saveCart(Cart cart);
}
