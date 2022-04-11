package com.shop.ShoppingCart.services;

import com.shop.ShoppingCart.models.Cart;
import com.shop.ShoppingCart.models.CartItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartItemService {
    List<CartItem> getAllCartItemsByCart(Cart cart);
    CartItem saveCartItem(CartItem cartItem);
    List<CartItem> saveAllCartItems(List<CartItem> cartItems);
    CartItem updateCartItem(CartItem cartItem);
    void deleteCartItemById(Long id);
    Page<CartItem> findPaginated(Cart cart, Integer pageNo, Integer pageSize, String sortField, String sortDirection);
}
