package com.example.case_module6.service.implement;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.News;
import com.example.case_module6.model.Notification;
import com.example.case_module6.repository.CartRepository;
import com.example.case_module6.service.ICartService;
import com.example.case_module6.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private INotificationService notificationService;

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
        Cart savedCart = cartRepository.save(cart);
        Notification notification = new Notification();
        notification.setContent("Có Đơn Hàng : #"+savedCart.getId());
        notification.setDateNote(LocalDateTime.now());
        notificationService.save(notification);
        messagingTemplate.convertAndSend("/topic/notifications", notification);
        return savedCart;
    }

    public List<Cart> findCartsByTableId(Long tableId) {
        List<Cart> carts = cartRepository.findByTable_Id(tableId);
        if (carts.isEmpty()){
            throw new RuntimeException("Cart not found with table id: " + tableId);
        }
        return carts;
    }

}
