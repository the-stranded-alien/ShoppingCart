package com.shop.ShoppingCart.repositories;

import com.shop.ShoppingCart.models.Cart;
import com.shop.ShoppingCart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}

