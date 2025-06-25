package com.wihanga.bookstore.repository;

import com.wihanga.bookstore.model.CartItem;
import com.wihanga.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    boolean existsByUserAndBook_Id(User user, Long bookId);
}
