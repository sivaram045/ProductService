package com.ecomapp.productservice.controllers;

import com.ecomapp.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping()
    public ArrayList<Product> getAllProducts() {
        ArrayList products = new ArrayList<>();
        return products;
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct (@PathVariable long id, @RequestBody Product product) {
        return new Product();
    }

}
