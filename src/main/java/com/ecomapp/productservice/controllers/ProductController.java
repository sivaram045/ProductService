package com.ecomapp.productservice.controllers;

import com.ecomapp.productservice.DTOs.FakeStoreProductDTO;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        List <Product> products = productService.getAllProducts();
        return products;
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct (@PathVariable long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

}
