package com.ecomapp.productservice.controllerAdvices;

import com.ecomapp.productservice.DTOs.ExceptionDTO;
import com.ecomapp.productservice.exceptions.CategoryNotExistException;
import com.ecomapp.productservice.exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotExistException(
            ProductNotExistException exception
    ) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(exception.getMessage());
        dto.setDetail("Check the product id. Probably Not Exist.");

        return new ResponseEntity<>(dto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CategoryNotExistException.class)
    public ResponseEntity<ExceptionDTO> handleCategoryNotExistException(
            CategoryNotExistException exception
    ) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(exception.getMessage());
        dto.setDetail("Check the Category name. Probably Not Exist.");

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
