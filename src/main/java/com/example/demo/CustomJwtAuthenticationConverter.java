package com.example.demo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;


public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final Converter<Jwt, Collection<GrantedAuthority>> customJwtGrantedAuthoritiesConverter = new CustomJwtGrantedAuthoritiesConverter();
    private final String emailClaim = "unique_name";

    public CustomJwtAuthenticationConverter() {
    }

    public final AbstractAuthenticationToken convert(Jwt jwt) {

        String email = jwt.getClaimAsString("unique_name");
        String oid = jwt.getClaimAsString("oid");

        /*
        Abfrage ob User existiert auf Basis der email und oid
        User user = userRepository.findByEmailAndOid(email, oid);
        //wenn nicht wird er angelegt mit enabled false
        if(!user){
            user = new User();
            user.setFirstName(jwt.getClaimAsString("given_name");
            user.setLastName(jwt.getClaimAsString("family_name");
            user.setFirstName(jwt.getClaimAsString("given_name");
            user.setIsEnabled(false)
            userRepository.save(user);
        }
        //Wenn existiert aber enabled false throw Exception -> 401 Forbidden
        if((user && user.IsEnabled == false){
            throw new CustomAuthenticationException("message");
        }
        */

        Collection<GrantedAuthority> authorities = this.customJwtGrantedAuthoritiesConverter.convert(jwt);
        return new JwtAuthenticationToken(jwt, authorities, email);
    }

}
