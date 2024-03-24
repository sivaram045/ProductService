package com.ecomapp.productservice.repositories;

import com.ecomapp.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findCategoryById(Long id);
}
