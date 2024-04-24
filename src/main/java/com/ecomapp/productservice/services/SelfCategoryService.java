package com.ecomapp.productservice.services;

import com.ecomapp.productservice.exceptions.CategoryNotExistException;
import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.repositories.CategoryRepository;
import com.ecomapp.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService {
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    public SelfCategoryService(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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
    public List<Product> getInCategory(String category) throws CategoryNotExistException {
        Optional<Category> optionalCategory = categoryRepository.findByTitle(category);

        if(optionalCategory.isEmpty()) {
            throw new CategoryNotExistException("Category '"+category+"' doesn't exist");
        }

        return categoryRepository.findByName(category);
        //return productRepository.findByCategory(optionalCategory.get()); //this also works
    }
}
