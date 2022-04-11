package com.shop.ShoppingCart.repositories;

import com.shop.ShoppingCart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
