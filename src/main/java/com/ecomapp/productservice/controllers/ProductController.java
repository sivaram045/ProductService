package com.ecomapp.productservice.controllers;

import com.ecomapp.productservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping()
    public ArrayList<Product> getAllProducts() {
        ArrayList products = new ArrayList<>();
        return products;
    }


}
