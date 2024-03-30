package com.ecomapp.productservice.repositories;

import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findCategoryById(Long id);
    public Optional<Category> findByTitle(String category);
    public Category save(Category category);

}
