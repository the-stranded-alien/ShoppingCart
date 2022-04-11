package com.shop.ShoppingCart.services.implementations;

import com.shop.ShoppingCart.models.Cart;
import com.shop.ShoppingCart.models.CartItem;
import com.shop.ShoppingCart.models.User;
import com.shop.ShoppingCart.models.UserInfo;
import com.shop.ShoppingCart.repositories.CartItemRepository;
import com.shop.ShoppingCart.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAllCartItemsByCart(Cart cart) {
        return this.cartItemRepository.findAllByCart(cart);
    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        return this.cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> saveAllCartItems(List<CartItem> cartItems) {
        return this.cartItemRepository.saveAll(cartItems);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        CartItem existingCartItem = this.cartItemRepository.findById(cartItem.getId()).orElse(null);
        existingCartItem.setQuantity(cartItem.getQuantity());
        existingCartItem.setSubSalesTax(cartItem.getSubSalesTax());
        existingCartItem.setSubTotalWithoutTax(cartItem.getSubTotalWithoutTax());
        existingCartItem.setSubTotal(cartItem.getSubTotal());
        return this.cartItemRepository.save(existingCartItem);
    }

    @Override
    public void deleteCartItemById(Long id) { this.cartItemRepository.deleteById(id); }

    @Override
    public Page<CartItem> findPaginated(Cart cart, Integer pageNo, Integer pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.cartItemRepository.findAllByCart(cart, pageable);
    }
}
