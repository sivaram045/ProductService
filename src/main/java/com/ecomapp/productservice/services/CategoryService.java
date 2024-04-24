package com.ecomapp.productservice.services;

import com.ecomapp.productservice.exceptions.CategoryNotExistException;
import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public List<Product> getInCategory(String category) throws CategoryNotExistException;
    public List<String> getAllCategoryTitles();

}
