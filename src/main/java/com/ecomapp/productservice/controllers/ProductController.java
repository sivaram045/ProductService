package com.ecomapp.productservice.controllers;

import com.ecomapp.productservice.DTOs.FakeStoreProductDTO;
import com.ecomapp.productservice.DTOs.ProductDTO;
import com.ecomapp.productservice.DTOs.UserDTO;
import com.ecomapp.productservice.DTOs.RoleDTO;
import com.ecomapp.productservice.commons.AuthenticationCommons;
import com.ecomapp.productservice.exceptions.ProductNotExistException;
import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.services.CategoryService;
import com.ecomapp.productservice.services.ProductService;
import com.ecomapp.productservice.services.SelfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private RestTemplate restTemplate;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier ("SelfProductService") ProductService productService,
                             CategoryService categoryService,
                             RestTemplate restTemplate,
                              AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.restTemplate = restTemplate;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable long id) throws ProductNotExistException {
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),HttpStatus.OK);
        return response;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {

        //commented manual authentication after spring security implementation

//        UserDTO userDTO = authenticationCommons.validateToken(token);
//        if(userDTO == null) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        boolean isAdmin = true; //true for temp usage, should be false
//
//        for (RoleDTO role: userDTO.getRoles()) {
//            if (role.getTitle().equals("SCOPE_ADMIN")) {
//                isAdmin = true;
//                break;
//            }
//        }
//
//        if (!isAdmin) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List <Product> products = productService.getAllProducts();

        List<Product> finalProducts = new ArrayList<>();

        for (Product p: products) { // o  p q
            p.setTitle("Hello" + p.getTitle());
            finalProducts.add(p);
        }

        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                finalProducts, HttpStatus.OK
        );
        return response;
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody ProductDTO product) {
        Product tempProduct = new Product();
        tempProduct.setTitle(product.getTitle());
        tempProduct.setPrice(product.getPrice());
        tempProduct.setDescription(product.getDescription());
        tempProduct.setCategory(new Category());
        tempProduct.getCategory().setTitle(product.getCategory());
        return productService.addNewProduct(tempProduct);
    }

    @PutMapping("/{id}")
    public Product replaceProduct (@PathVariable long id, @RequestBody Product product) throws ProductNotExistException {
        return productService.replaceProduct(id, product);
    }

    @GetMapping("/limit/{number}")
    public List<Product> getNProducts(@PathVariable int number) {
        List<Product> products = productService.searchByLimit(number);
        return products;
    }

    @PatchMapping("/{id}")
    public Product updateProduct (@PathVariable long id, @RequestBody Product product) throws ProductNotExistException{
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable long id) throws ProductNotExistException {
        return productService.deleteProduct(id);
    }


}
