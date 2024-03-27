package com.ecomapp.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    //private long id;
    private String title;
    private Double price;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL) //since a category must be created before a product,
                                          // not using cascade will give an error..!
                                          // while inserting a product into the database.
    private Category category;
    private String imageURL;
    //private int noOfProducts;
}
