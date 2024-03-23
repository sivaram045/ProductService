package com.ecomapp.productservice.models;

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
    @ManyToOne
    private Category category;
    private String imageURL;
}
