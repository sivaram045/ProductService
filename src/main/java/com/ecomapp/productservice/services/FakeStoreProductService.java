package com.ecomapp.productservice.services;

import com.ecomapp.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }
}
