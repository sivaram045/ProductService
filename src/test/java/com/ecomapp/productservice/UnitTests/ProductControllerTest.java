package com.ecomapp.productservice.UnitTests;

import com.ecomapp.productservice.controllers.ProductController;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.repositories.ProductRepository;
import com.ecomapp.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;


    @Test
    void testGetSingleProduct() {

        //Arrange
        Long id = 2L;
        Product mockProd = new Product();
        mockProd.setTitle("Potato");
        mockProd.setDescription("Hello Potato");
        mockProd.setId(id);

        when (
            productService.getSingleProduct(id)
        ).thenReturn (
                mockProd
        );

        //Act
        ResponseEntity<Product> response = productController.getSingleProduct(id);

        //Assert
        Product responseProduct = response.getBody();
        assertEquals(mockProd.getId(), responseProduct.getId());
        assertEquals(mockProd.getTitle(), responseProduct.getTitle());
        assertEquals(mockProd.getDescription(), responseProduct.getDescription());
    }

}
