package com.ecomapp.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RoleDTO {
    private String Title;
    private List<UserDTO> users;
}
