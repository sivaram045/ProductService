package com.ecomapp.productservice.services;

import com.ecomapp.productservice.DTOs.FakeStoreProductDTO;
import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    //private FakeStoreProductDTO fakeStoreProductDTO;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        //this.fakeStoreProductDTO = fakeStoreProductDTO;
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

    private FakeStoreProductDTO convertToFakeStoreProductDTO(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setCategory(product.getCategory().getTitle());
        fakeStoreProductDTO.setImage(product.getImageURL());

        return fakeStoreProductDTO;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDTO[].class
        );

        List<Product> ans = new ArrayList<>();

        for(FakeStoreProductDTO dto : fakeStoreProductDTO) {
            ans.add(convertToProduct(dto));
        }

        return ans;
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
        FakeStoreProductDTO fakeStoreProduct = new FakeStoreProductDTO();
        fakeStoreProduct.setTitle(product.getTitle());
        fakeStoreProduct.setPrice(product.getPrice());
        fakeStoreProduct.setDescription(product.getDescription());
        fakeStoreProduct.setCategory(product.getCategory().getTitle());
        fakeStoreProduct.setImage(product.getImageURL());
//        fakeStoreProduct = restTemplate.postForObject(
//                "https://fakestoreapi.com/products",
//                product,
//                FakeStoreProductDTO.class);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProduct, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products",
                HttpMethod.POST, requestCallback, responseExtractor);

        return convertToProduct(response);


        //return convertToProduct(fakeStoreProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setCategory(product.getCategory().getTitle());
        fakeStoreProductDTO.setImage(product.getImageURL());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);

        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());

        FakeStoreProductDTO response = restTemplate.execute(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT, requestCallback,
                 responseExtractor);

        return convertToProduct(response);
    }

    @Override
    public List<Product> searchByLimit(int a) {
        FakeStoreProductDTO[] fakeStoreProductDTO2 = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        List<Product> ans = new ArrayList<>();

        for(int i=0; i<a; i++) {
            ans.add(convertToProduct(fakeStoreProductDTO2[i]));
        }

        return  ans;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
