package com.shop.ShoppingCart.services.implementations;

import com.shop.ShoppingCart.models.Cart;
import com.shop.ShoppingCart.models.User;
import com.shop.ShoppingCart.repositories.CartRepository;
import com.shop.ShoppingCart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCartByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    @Override
    public Cart saveCart(Cart cart) { return this.cartRepository.save(cart); }

    @Override
    public Cart updateCart(Cart cart) {
        Cart existingCart = this.cartRepository.findById(cart.getId()).orElse(null);
        existingCart.setCartItems(cart.getCartItems());
        existingCart.setProductCount(cart.getProductCount());
        existingCart.setTotalWithoutSalesTax(cart.getTotalWithoutSalesTax());
        existingCart.setSalesTax(cart.getSalesTax());
        existingCart.setCartTotal(cart.getCartTotal());
        return this.cartRepository.save(existingCart);
    }
}
