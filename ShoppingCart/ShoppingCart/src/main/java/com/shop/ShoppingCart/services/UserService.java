package com.shop.ShoppingCart.services;

import com.shop.ShoppingCart.dto.UserRegistrationDto;
import com.shop.ShoppingCart.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    User save(UserRegistrationDto registration);
}
