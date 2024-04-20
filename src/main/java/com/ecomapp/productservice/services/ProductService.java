package com.ecomapp.productservice.services;

import com.ecomapp.productservice.exceptions.ProductNotExistException;
import com.ecomapp.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getSingleProduct(Long id) throws ProductNotExistException;

    public Product addNewProduct(Product product);
    public Product updateProduct(Long id, Product product) throws ProductNotExistException;
//    public void addNewCategory(Product product);
    public Product replaceProduct(Long id, Product product) throws ProductNotExistException;
    public List<Product> searchByLimit(int a);
//    Product replaceProduct(Long id, Product product);
    Product deleteProduct(Long id) throws ProductNotExistException;

}
