package com.ecomapp.productservice.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String Name;
    private String Email;
    private String Password;
    private List<RoleDTO> Roles;
    private List<TokenDTO> token;
}

