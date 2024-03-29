package com.ecomapp.productservice.repositories;

import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.repositories.projections.ProdWithIdTitlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    int countByCategory(Category category);
    Product save(Product product);

    List<Product> findAll();
    Product findByTitle(String title);

    @Query("SELECT p from Product p where p.category.title like '%grocery%'")
    List<Product> getAllProd();

    @Query(value = "select * from product p where p.category_id = :id", nativeQuery = true)
    List<Product> getAllProdNative();

    @Query("select p.Id as id, p.title as title, p.price as price from Product p where p.Id = :id")
    List<ProdWithIdTitlePrice> getProdIdTitlePrice(Long id);

}
