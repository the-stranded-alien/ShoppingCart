package com.shop.ShoppingCart.repositories;

import com.shop.ShoppingCart.models.Cart;
import com.shop.ShoppingCart.models.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(Cart cart);
    Page<CartItem> findAllByCart(Cart cart, Pageable pageable);
}
