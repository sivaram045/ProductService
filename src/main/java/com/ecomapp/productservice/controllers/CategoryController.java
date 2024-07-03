package com.ecomapp.productservice.controllers;

import com.ecomapp.productservice.exceptions.CategoryNotExistException;
import com.ecomapp.productservice.models.Category;
import com.ecomapp.productservice.models.Product;
import com.ecomapp.productservice.services.CategoryService;
import com.ecomapp.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpResponse;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories () {

        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/title/all")
    public ResponseEntity<List<String>> getAllCategoryTitles () {
        return new ResponseEntity<>(categoryService.getAllCategoryTitles(),HttpStatus.OK);
    }
    @GetMapping("/{title}")
    public ResponseEntity<List<Product>> getInCategory(@PathVariable String title) throws CategoryNotExistException {
        return new ResponseEntity<>(categoryService.getInCategory(title),HttpStatus.FOUND);
    }
}
