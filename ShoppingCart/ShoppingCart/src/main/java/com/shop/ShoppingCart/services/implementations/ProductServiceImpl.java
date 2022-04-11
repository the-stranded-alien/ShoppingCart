package com.shop.ShoppingCart.services.implementations;

import com.shop.ShoppingCart.models.Product;
import com.shop.ShoppingCart.repositories.ProductRepository;
import com.shop.ShoppingCart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> optional = this.productRepository.findById(product.getId());
        if(optional.isPresent()) {
            Product existingProduct = optional.get();
            existingProduct.setName(product.getName());
            existingProduct.setUnitPrice(product.getUnitPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setSalesTaxRate(product.getSalesTaxRate());
            existingProduct.setQuantityAvailable(product.getQuantityAvailable());
            return this.productRepository.save(existingProduct);
        }
        throw new RuntimeException("Product Not Found !");
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if(optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException("Product with Id : " + id + " Not Found !");
        }
        return product;
    }

    @Override
    public void deleteProductById(Long id) { this.productRepository.deleteById(id); }

    @Override
    public Page<Product> findPaginated(Integer pageNo, Integer pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.productRepository.findAll(pageable);
    }
}
