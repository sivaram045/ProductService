package com.ecomapp.productservice;

import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.repositories.CategoryRepository;
import com.ecomapp.productservice.repositories.ProductRepository;
import com.ecomapp.productservice.repositories.projections.ProdWithIdTitlePrice;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
//    @Autowired
//    public ProductServiceApplicationTests(ProductRepository productRepository, CategoryRepository categoryRepository) {
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//    }

    @Test
    void contextLoads() {
    }
    @Test
    @Transactional
    void testQueries() {
        List<ProdWithIdTitlePrice> productList = new ArrayList<>(productRepository.getProdIdTitlePrice(2L));
        for(int i=0; i<productList.size(); i++) {
            System.out.println(productList.get(i).getId());
            System.out.println(productList.get(i).getDescription());
            System.out.println(productList.get(i).getPrice());
        }
    }

}
