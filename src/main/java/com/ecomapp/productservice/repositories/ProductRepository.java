package com.ecomapp.productservice.repositories;

import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    int countByCategory(Category category);
    Product save(Product product);

}
