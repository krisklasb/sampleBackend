package com.example.demo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt,Collection<GrantedAuthority>>{

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
/*
        String email = jwt.getClaimAsString("unique_name");
        String oid = jwt.getClaimAsString("oid");

        //User auf Basis der email und oid
        User user = userRepository.findByEmailAndOid(email, oid);


        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities
 */
        return null;
    }
}