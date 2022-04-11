package step1.ShoppingCart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;

    @BeforeEach
    void setUp() {
        double salesTaxRate = 12.5d;
        cart = new Cart(salesTaxRate);
        assertEquals(salesTaxRate, this.cart.salesTaxRate);
    }

    @Test
    void addProduct() {
        String productName = "Dove";
        double unitPrice = 39.99;
        this.cart.addProduct(productName, unitPrice);
        assertEquals(1, this.cart.products.size());
    }

    @Test
    void addCartItem() {
        String productName = "Dove";
        this.cart.addProduct(productName, 39.99);
        int quantity = 5;
        this.cart.addCartItem(productName, quantity);
        assertEquals(1, this.cart.cartItems.size());
        int newQuantity = 3;
        this.cart.addCartItem(productName, newQuantity);
        assertEquals(1, this.cart.cartItems.size());
    }

    @Test
    void showCartItems() {
        String productName1 = "Dove";
        this.cart.addProduct(productName1, 39.99);
        this.cart.addCartItem(productName1, 2);
        assertEquals(1, this.cart.cartItems.size());
        String productName2 = "Axe";
        this.cart.addProduct(productName2, 99.99);
        this.cart.addCartItem(productName2, 2);
        assertEquals(2, this.cart.cartItems.size());
        HashMap<Double, Integer> it1 = this.cart.showCartItem(productName1);
        HashMap<Double, Integer> it2 = this.cart.showCartItem(productName2);
        assertEquals(2, it1.get(39.99));
        assertEquals(2, it2.get(99.99));
        assertEquals(35.00, this.cart.showSalesTax());
        assertEquals(314.96, this.cart.showCartTotal());
    }
}