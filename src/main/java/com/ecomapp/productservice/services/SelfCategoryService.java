package com.ecomapp.productservice.services;

import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService {
    CategoryRepository categoryRepository;
    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllCategories();
    }
    @Override
    public List<String> getAllCategoryTitles() {
        return categoryRepository.findAllTitles();
    }

    @Override
    public List<Product> getInCategory(String category) {
        Optional<Category> optionalCategory = categoryRepository.findByTitle(category);

        if(optionalCategory.isEmpty()) {
            throw new NoSuchElementException("Category "+category+" doesn't exist");
        }
        List<Product> productList = new ArrayList<>(categoryRepository.findByName(category));

        return productList;
    }
}
