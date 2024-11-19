package com.ecomapp.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String title;
    private Double price;
    private String description;
    private String category;

}
