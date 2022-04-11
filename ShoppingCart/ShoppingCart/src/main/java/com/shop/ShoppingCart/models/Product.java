package com.shop.ShoppingCart.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float unitPrice;
    private String category;
    private Float salesTaxRate;
    private Integer quantityAvailable;

    public Product() {

    }

    public Product(String name, Float unitPrice, String category, Float salesTaxRate, Integer quantityAvailable) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = category;
        this.salesTaxRate = salesTaxRate;
        this.quantityAvailable = quantityAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getSalesTaxRate() {
        return salesTaxRate;
    }

    public void setSalesTaxRate(Float salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product other = (Product) o;
        return (id != null && id.equals(other.getId()));
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", category='" + category + '\'' +
                ", salesTaxRate=" + salesTaxRate +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
