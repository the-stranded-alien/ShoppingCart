package com.shop.ShoppingCart.services.implementations;

import com.shop.ShoppingCart.dto.UserRegistrationDto;
import com.shop.ShoppingCart.models.Cart;
import com.shop.ShoppingCart.models.Role;
import com.shop.ShoppingCart.models.User;
import com.shop.ShoppingCart.repositories.UserRepository;
import com.shop.ShoppingCart.services.CartService;
import com.shop.ShoppingCart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll() { return userRepository.findAll(); }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setUsername(registration.getUsername());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        Set<Role> set = new HashSet<>();
        set.add(new Role("ROLE_USER"));
        user.setUserRoles(set);
        Cart cart = new Cart();
        cart.setUser(user);
        cartService.saveCart(cart);
        return userRepository.save(user);
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
