package com.ecomapp.productservice.controllers;

import com.ecomapp.productservice.DTOs.FakeStoreProductDTO;
import com.ecomapp.productservice.exceptions.ProductNotExistException;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.services.ProductService;
import com.ecomapp.productservice.services.SelfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier ("SelfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable long id) throws ProductNotExistException {
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),HttpStatus.FORBIDDEN);
        return response;
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

    @GetMapping("/limit/{number}")
    public List<Product> getNProducts(@PathVariable int number) {
        List<Product> products = productService.searchByLimit(number);
        return products;
    }

    @PatchMapping("/{id}")
    public Product updateProduct (@PathVariable long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable long id) {
        return productService.deleteProduct(id);
    }


}
