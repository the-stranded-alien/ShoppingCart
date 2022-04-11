package com.shop.ShoppingCart.services;

import com.shop.ShoppingCart.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);

    Product updateProduct(Product product);

    Product getProductById(Long id);
    void deleteProductById(Long id);
    Page<Product> findPaginated(Integer pageNo, Integer pageSize, String sortField, String sortDirection);
}
