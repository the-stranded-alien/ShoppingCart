package com.shop.ShoppingCart.controllers;

import com.shop.ShoppingCart.models.Product;
import com.shop.ShoppingCart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/newProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        this.productService.saveProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/showUpdateProductForm/{id}")
    public String showUpdateProductForm(@PathVariable(value="id") Long id, Model model) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product) {
        Product updatedProduct = this.productService.updateProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value="id") Long id) {
        this.productService.deleteProductById(id);
        return "redirect:/product";
    }


    @GetMapping
    public String viewProduct(Model model) {
        List<Product> allProducts = this.productService.getAllProducts();
        model.addAttribute("listProducts", allProducts);
        return "product/homeProduct";
    }
}
