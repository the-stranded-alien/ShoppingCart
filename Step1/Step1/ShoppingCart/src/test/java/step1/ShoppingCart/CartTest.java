package step1.ShoppingCart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
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
    }

    @Test
    void showCartItems() {
        String productName = "Dove";
        this.cart.addProduct(productName, 39.99);
        this.cart.addCartItem(productName, 5);
        assertEquals(1, this.cart.cartItems.size());
        HashMap<Double, Integer> res = this.cart.showCartItem(productName);
        assertEquals(true, res.containsKey(39.99));
        assertEquals(5, res.get(39.99));
    }
}