package com.shop.ShoppingCart.controllers;

import com.shop.ShoppingCart.models.Cart;
import com.shop.ShoppingCart.models.CartItem;
import com.shop.ShoppingCart.models.Product;
import com.shop.ShoppingCart.models.UserInfo;
import com.shop.ShoppingCart.services.CartService;
import com.shop.ShoppingCart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewCart(Model model) {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = cartService.getCartByUser(userInfo.getUser());
        System.out.println(cart);
        Set<CartItem> cartItems = cart.getCartItems();
        if(cartItems.isEmpty()) {
            cart.setProductCount(0);
            cart.setTotalWithoutSalesTax(0.0F);
            cart.setSalesTax(0.0F);
            cart.setCartTotal(0.0F);
            model.addAttribute("cart", cart);
            return "cart/homeCart";
        }
        List<CartItem> cartItemList = new ArrayList<>();
        int itemCount = 0;
        float totalWithoutTax = 0.0F;
        float tax = 0.0F;
        float cartTotal = 0.0F;
        for(CartItem item : cartItems) {
            itemCount += item.getQuantity();
            totalWithoutTax += item.getSubTotalWithoutTax();
            tax += item.getSubSalesTax();
            cartTotal += item.getSubTotal();
        }
        totalWithoutTax = (float) Math.round(totalWithoutTax * 100) / 100;
        tax = (float) Math.round(tax * 100) / 100;
        cartTotal = (float) Math.round(cartTotal * 100) / 100;
        cart.setProductCount(itemCount);
        cart.setTotalWithoutSalesTax(totalWithoutTax);
        cart.setSalesTax(tax);
        cart.setCartTotal(cartTotal);
        model.addAttribute("cart", cart);
        return "cart/homeCart";
    }

    @PostMapping("/saveCart")
    public String saveCart(@ModelAttribute("cart") Cart cart) {
        Cart newCart = new Cart();
        newCart.setProductCount(cart.getProductCount());
        newCart.setTotalWithoutSalesTax(cart.getTotalWithoutSalesTax());
        newCart.setSalesTax(cart.getSalesTax());
        newCart.setCartTotal(cart.getCartTotal());
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newCart.setUser(userInfo.getUser());
        Set<CartItem> cartItems = cart.getCartItems();
        newCart.addCartItems(cartItems);
        this.cartService.saveCart(cart);
        return "redirect:/cart/homeCart";
    }
    @GetMapping("/addCartItem")
    public String addCartItem(Model model) {
        CartItem cartItem = new CartItem();
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = cartService.getCartByUser(userInfo.getUser());
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("cart", cart);
        model.addAttribute("cartItem", cartItem);
        return "cart/newCartItem";
    }

    @PostMapping("/addCartItem")
    public String saveCartItem(@ModelAttribute("cartItem") @RequestBody CartItem cartItem, Model model) {
        CartItem newCartItem = new CartItem();
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = cartService.getCartByUser(userInfo.getUser());
        Set<CartItem> items = cart.getCartItems();
        Product product = cartItem.getProduct();
        for(CartItem c : items) {
            if(c.getProduct().equals(product)) {
                CartItem old = c;
                Integer newQuan = old.getQuantity() + cartItem.getQuantity();
                old.setQuantity(newQuan);
                old.setSubSalesTax(product.getSalesTaxRate());
                float subSalesTaxVal = (float) Math.round((newQuan * product.getUnitPrice() * (product.getSalesTaxRate() / 100)) * 100) / 100;
                old.setSubSalesTax(subSalesTaxVal);
                float subTotalWithoutTax = (float) Math.round((newQuan * product.getUnitPrice()) * 100) / 100;
                old.setSubTotalWithoutTax(subTotalWithoutTax);
                old.setSubTotal((float) Math.round((subTotalWithoutTax + subSalesTaxVal) * 100) / 100);
                this.cartService.saveCart(cart);
                return "redirect:/cart";
            }
        }
        newCartItem.setProduct(product);
        newCartItem.setQuantity(cartItem.getQuantity());
        newCartItem.setSubSalesTax(product.getSalesTaxRate());
        float subSalesTaxVal = (float) Math.round((cartItem.getQuantity() * product.getUnitPrice() * (product.getSalesTaxRate() / 100)) * 100) / 100;
        newCartItem.setSubSalesTax(subSalesTaxVal);
        float subTotalWithoutTax = (float) Math.round((cartItem.getQuantity() * product.getUnitPrice()) * 100) / 100;
        newCartItem.setSubTotalWithoutTax(subTotalWithoutTax);
        newCartItem.setSubTotal((float) Math.round((subSalesTaxVal + subTotalWithoutTax) * 100) / 100);
        cart.addCartItem(newCartItem);
        this.cartService.saveCart(cart);
        return "redirect:/cart";
    }
}
