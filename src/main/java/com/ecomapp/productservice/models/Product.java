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
    @ManyToOne(cascade = {CascadeType.PERSIST}) //since a category must be created for a product,
                                          // not using cascade will give an error..!
                                          // while inserting a product into the database.
                                          //***look into cascadeTypes ALL is not preferred***
    private Category category;
    private String imageURL;
    //private int noOfProducts;
}
