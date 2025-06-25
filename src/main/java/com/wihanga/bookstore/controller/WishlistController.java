package com.wihanga.bookstore.controller;

import com.wihanga.bookstore.model.WishlistItem;
import com.wihanga.bookstore.model.User;
import com.wihanga.bookstore.repository.UserRepository;
import com.wihanga.bookstore.repository.WishlistItemRepository;
import com.wihanga.bookstore.repository.BookRepository;
import com.wihanga.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {
    @Autowired
    private WishlistItemRepository wishlistItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<WishlistItem> getWishlist(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return wishlistItemRepository.findByUser(user);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToWishlist(@RequestParam Long bookId, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) return ResponseEntity.badRequest().body("Book not found");
        if (wishlistItemRepository.existsByUserAndBook_Id(user, bookId))
            return ResponseEntity.badRequest().body("Book already in wishlist");
        WishlistItem item = new WishlistItem();
        item.setUser(user);
        item.setBook(book);
        return ResponseEntity.ok(wishlistItemRepository.save(item));
    }
}
