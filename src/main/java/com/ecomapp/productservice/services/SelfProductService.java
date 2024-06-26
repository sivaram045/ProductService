package com.ecomapp.productservice.services;

import com.ecomapp.productservice.exceptions.ProductNotExistException;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.repositories.CategoryRepository;
import com.ecomapp.productservice.repositories.ProductRepository;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotExistException(id+"");
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
    public Product updateProduct(Long id, Product product) throws ProductNotExistException{
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        Optional<Category> optionalCategory = categoryRepository.findByTitle(product.getCategory().getTitle());

        if(optionalProduct.isEmpty()) {     //if no product available with the id, throw error
            throw new ProductNotExistException(id+" ");
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
                //reducing count of old category
                Product oldProduct = productRepository.findByTitle(product.getTitle());
                oldProduct.getCategory().setNoOfProducts(oldProduct.getCategory().getNoOfProducts()-1);

                //changing category and count as well
                optionalProduct.get().setCategory(categoryRepository.save(product.getCategory()));
                optionalProduct.get().getCategory().setNoOfProducts(1);

            }else {
                //reducing count of old category
                Product oldProduct = productRepository.findByTitle(product.getTitle());
                oldProduct.getCategory().setNoOfProducts(oldProduct.getCategory().getNoOfProducts()-1);

                //changing category and count as well
                optionalProduct.get().setCategory(optionalCategory.get());
                optionalCategory.get().setNoOfProducts(optionalCategory.get().getNoOfProducts()+1);

            }

            return productRepository.save(optionalProduct.get());
        }
        //return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        Optional<Category> optionalCategory = categoryRepository.findByTitle(product.getCategory().getTitle());

        if(optionalProduct.isEmpty()) {
            throw new ProductNotExistException("poduct with id "+id+" not found");
        }
        optionalProduct.get().setTitle(product.getTitle());
        optionalProduct.get().setPrice(product.getPrice());
        optionalProduct.get().setDescription(product.getDescription());

        if(optionalCategory.isEmpty()) {
            optionalProduct.get().setCategory(categoryRepository.save(product.getCategory()));
            optionalProduct.get().getCategory().setNoOfProducts(1);
        }else {
            Product oldProduct = productRepository.findByTitle(product.getTitle());
            oldProduct.getCategory().setNoOfProducts(oldProduct.getCategory().getNoOfProducts()-1);
            optionalProduct.get().setCategory(optionalCategory.get());
            optionalCategory.get().setNoOfProducts(optionalCategory.get().getNoOfProducts()+1);
        }


        return productRepository.save(optionalProduct.get());
    }

    @Override
    public List<Product> searchByLimit(int a) {
        return null;
    }

    @Override
    @Transactional
    public Product deleteProduct(Long id) throws ProductNotExistException{

        Optional<Product> optionalProduct = productRepository.findProductById(id);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotExistException("product with id "+id+" not found" );
        }
//        Category currProdCategory = optionalProduct.get().getCategory();
//        currProdCategory.setNoOfProducts(currProdCategory.getNoOfProducts()-1);
         optionalProduct.get().getCategory().setNoOfProducts(
                 optionalProduct.get().getCategory().getNoOfProducts()-1);


        productRepository.deleteById(id);
        return optionalProduct.get();

    }
}
