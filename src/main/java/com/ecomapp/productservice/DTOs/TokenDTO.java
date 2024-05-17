package com.ecomapp.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TokenDTO {
    private String value;
    private Date expiryDate;
    private UserDTO user;
}
