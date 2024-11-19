package com.ecomapp.productservice.security;

import com.ecomapp.productservice.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers("/product").authenticated()
                                //.requestMatchers(HttpMethod.GET,"/product").permitAll()
                                .requestMatchers(HttpMethod.GET,"/product/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/product").hasAuthority("SCOPE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/product").hasAuthority("SCOPE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/product").hasAuthority("SCOPE_ADMIN")
                                .requestMatchers(HttpMethod.PATCH,"/product").hasAuthority("SCOPE_ADMIN")
                        //.anyRequest().permitAll()
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

/*    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }*/

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Handle "roles" claim
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // Fetch authorities from "roles" claim
            Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

            // Add additional authorities from "scope" claim (if it exists and is an array)
            List<String> scopes = jwt.getClaimAsStringList("scope");
            if (scopes != null) {
                authorities.addAll(scopes.stream()
                        .map(scope -> new SimpleGrantedAuthority("SCOPE_" + scope))
                        .collect(Collectors.toList()));
            }

            return authorities;
        });

        return jwtAuthenticationConverter;
    }


}
