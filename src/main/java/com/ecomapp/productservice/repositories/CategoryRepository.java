package com.ecomapp.productservice.repositories;

import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select c.* from Category c", nativeQuery = true)
    List<Category> findAllCategories();
     Optional<Category> findCategoryById(Long id);
     Optional<Category> findByTitle(String category);
     Category save(Category category);

     @Query("select c.title as title from Category c ")
     List<String> findAllTitles();

     @Query("select c.products from Category c where c.title = :name")
     List<Product> findByName(@Param("name") String name);

}
