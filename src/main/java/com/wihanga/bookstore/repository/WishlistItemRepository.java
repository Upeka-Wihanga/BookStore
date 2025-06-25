package com.wihanga.bookstore.repository;

import com.wihanga.bookstore.model.WishlistItem;
import com.wihanga.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUser(User user);
    boolean existsByUserAndBook_Id(User user, Long bookId);
}