package com.ecomapp.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    //private long id;
    private String title;
    private int noOfProducts;
    //private List<Product> products;  gives "Failed to initialize JPA EntityManagerFactory " error
}
