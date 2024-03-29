package com.ecomapp.productservice.services;

import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.repositories.CategoryRepository;
import com.ecomapp.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.micrometer.common.util.StringUtils.isEmpty;

@Primary
@Service("SelfProductService")
public class SelfProductService implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        if(optionalProduct.isEmpty()) {
            throw new RuntimeException("check product id");
        }else {
            return optionalProduct.get();
        }
    }


    @Override
    public Product addNewProduct(Product product) {
        //handling duplicate titles
        if(productRepository.findByTitle(product.getTitle())==null) {
            //handling duplicate categories
            Optional<Category> optionalCategory = categoryRepository.findByTitle(product.getCategory().getTitle());
            if (optionalCategory.isEmpty()) {
                product.setCategory(categoryRepository.save(product.getCategory()));
                product.getCategory().setNoOfProducts(1);
            } else {
                product.setCategory(optionalCategory.get());
                product.getCategory().setNoOfProducts(product.getCategory().getNoOfProducts() + 1);
            }

            //product.getCategory().setNoOfProducts(productRepository.countByCategory(product.getCategory()));


            return productRepository.save(product);
        }
        else throw new RuntimeException("Title already exist");
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        Optional<Category> optionalCategory = categoryRepository.findByTitle(product.getCategory().getTitle());

        if(optionalProduct.isEmpty()) {     //if no product available with the id, throw error
            throw new RuntimeException("check product Id..!");
        }else {
            //updating data
            if(product.getPrice()!=optionalProduct.get().getPrice()) {
                optionalProduct.get().setPrice(product.getPrice());
            }
            if(product.getDescription()!=optionalProduct.get().getDescription()) {
                optionalProduct.get().setDescription(product.getDescription());
            }
            if(product.getImageURL()!=optionalProduct.get().getImageURL()) {
                optionalProduct.get().setImageURL(product.getImageURL());
            }
            //checking category already exist
            if(optionalCategory.isEmpty()) {
                product.setCategory(categoryRepository.save(product.getCategory()));
                product.getCategory().setNoOfProducts(1);
            }else {
                product.setCategory(optionalCategory.get());
                product.getCategory().setNoOfProducts(product.getCategory().getNoOfProducts()+1);
            }

            return productRepository.save(optionalProduct.get());
        }
        //return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public List<Product> searchByLimit(int a) {
        return null;
    }
}
