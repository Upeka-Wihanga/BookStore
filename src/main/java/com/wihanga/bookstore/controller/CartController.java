package com.wihanga.bookstore.controller;

import com.wihanga.bookstore.model.CartItem;
import com.wihanga.bookstore.model.User;
import com.wihanga.bookstore.repository.UserRepository;
import com.wihanga.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<CartItem> getCart(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return cartService.getCartForUser(user);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam Long bookId, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        try {
            return ResponseEntity.ok(cartService.addToCart(user, bookId));
        } catch (RuntimeException ex) {
            // Return 400 with message
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
