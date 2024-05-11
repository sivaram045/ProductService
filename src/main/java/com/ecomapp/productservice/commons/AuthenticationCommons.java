package com.ecomapp.productservice.commons;

import com.ecomapp.productservice.DTOs.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDTO validateToken(String token) {
        ResponseEntity<UserDTO> userDTOResponse = restTemplate.postForEntity(
                "http://localhost:8181/user/validate/"+ token,
                null,
                UserDTO.class
        );
        if(userDTOResponse.getBody() == null) {
            //throw token invalid exception
            return null;
        }
        return userDTOResponse.getBody();
    }
}
