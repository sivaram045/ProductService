package com.ecomapp.productservice.services;

import com.ecomapp.productservice.DTOs.FakeStoreProductDTO;
import com.ecomapp.productservice.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long id);
    Product addNewProduct(Product product);
//    Product updateProduct(Long id, Product product);
//    Product replaceProduct(Long id, Product product);
//    boolean deleteProduct(Long id);

}
