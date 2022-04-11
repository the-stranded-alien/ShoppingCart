package com.shop.ShoppingCart.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private Integer quantity;
    private Float subTotalWithoutTax;
    private Float subSalesTax;
    private Float subTotal;

    public CartItem () {

    }

    public CartItem(Integer quantity, Float subTotalWithoutTax, Float subSalesTax, Float subTotal) {
        this.quantity = quantity;
        this.subTotalWithoutTax = subTotalWithoutTax;
        this.subSalesTax = subSalesTax;
        this.subTotal = subTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getSubTotalWithoutTax() {
        return subTotalWithoutTax;
    }

    public void setSubTotalWithoutTax(Float subTotalWithoutTax) {
        this.subTotalWithoutTax = subTotalWithoutTax;
    }

    public Float getSubSalesTax() {
        return subSalesTax;
    }

    public void setSubSalesTax(Float subSalesTax) {
        this.subSalesTax = subSalesTax;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof CartItem)) return false;
        CartItem other = (CartItem) o;
        return (id != null && id.equals(other.getId()));
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "quantity=" + quantity +
                ", subTotalWithoutTax=" + subTotalWithoutTax +
                ", subSalesTax=" + subSalesTax +
                ", subTotal=" + subTotal +
                '}';
    }
}
