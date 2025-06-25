package com.wihanga.bookstore.service;

import com.wihanga.bookstore.model.CartItem;
import com.wihanga.bookstore.model.User;
import com.wihanga.bookstore.model.Book;
import com.wihanga.bookstore.repository.CartItemRepository;
import com.wihanga.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<CartItem> getCartForUser(User user) {
        return cartItemRepository.findByUser(user);
    }

    public CartItem addToCart(User user, Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }
        // Prevent duplicates
        if (cartItemRepository.existsByUserAndBook_Id(user, bookId)) {
            throw new RuntimeException("Book already in cart");
        }
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setBook(book);
        return cartItemRepository.save(cartItem);
    }
}
