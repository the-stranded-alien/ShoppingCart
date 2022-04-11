package com.shop.ShoppingCart.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart implements Serializable {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(
            mappedBy = "cart",
            cascade = {CascadeType.ALL}
    )
    private Set<CartItem> cartItems = new HashSet<>();

    private Integer productCount;
    private Float totalWithoutSalesTax;
    private Float salesTax;
    private Float cartTotal;

    public Cart () {

    }

    public Cart(Integer productCount, Float totalWithoutSalesTax, Float salesTax, Float cartTotal) {
        this.productCount = productCount;
        this.totalWithoutSalesTax = totalWithoutSalesTax;
        this.salesTax = salesTax;
        this.cartTotal = cartTotal;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
        cartItem.setCart(null);
    }

    public void addCartItems(Set<CartItem> cartItems) {
        this.cartItems.addAll(cartItems);
        for(CartItem cartItem : cartItems) {
            cartItem.setCart(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Float getTotalWithoutSalesTax() {
        return totalWithoutSalesTax;
    }

    public void setTotalWithoutSalesTax(Float totalWithoutSalesTax) {
        this.totalWithoutSalesTax = totalWithoutSalesTax;
    }

    public Float getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(Float salesTax) {
        this.salesTax = salesTax;
    }

    public Float getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Float cartTotal) {
        this.cartTotal = cartTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart other = (Cart) o;
        return (id != null && id.equals(other.getId()));
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productCount=" + productCount +
                ", totalWithoutSalesTax=" + totalWithoutSalesTax +
                ", salesTax=" + salesTax +
                ", cartTotal=" + cartTotal +
                '}';
    }
}
