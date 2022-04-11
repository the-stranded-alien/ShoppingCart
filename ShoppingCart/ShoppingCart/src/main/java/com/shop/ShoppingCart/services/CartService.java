package com.shop.ShoppingCart.services;

import com.shop.ShoppingCart.models.Cart;
import com.shop.ShoppingCart.models.User;

public interface CartService {
    Cart getCartByUser(User user);
    Cart saveCart(Cart cart);
    Cart updateCart(Cart cart);
}
