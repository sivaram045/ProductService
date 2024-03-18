package com.ecomapp.productservice.services;

import com.ecomapp.productservice.DTOs.FakeStoreProductDTO;
import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setCategory(new Category());
        product.getCategory().setTitle(fakeStoreProductDTO.getCategory());
        product.setImageURL(fakeStoreProductDTO.getImage());

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);

        return convertToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }
}
