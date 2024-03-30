package com.ecomapp.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;     //gives "Failed to initialize JPA EntityManagerFactory " error and
                                        //returning bulk repeated date in find all jpa query
}
