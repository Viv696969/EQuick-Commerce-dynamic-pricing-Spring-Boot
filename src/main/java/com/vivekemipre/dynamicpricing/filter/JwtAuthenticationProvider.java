package com.vivekemipre.dynamicpricing.filter;

import com.vivekemipre.dynamicpricing.service.CustomUserService;
import com.vivekemipre.dynamicpricing.util.JwtUtility;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtAuthenticationProvider implements AuthenticationProvider {


    private JwtUtility jwtUtility;

    @Autowired
    public JwtAuthenticationProvider(JwtUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = ((JwtAuthenticationToken) authentication).getToken();
        String userId=jwtUtility.validateToken(token);

        if(userId==null){
            throw new BadCredentialsException("Invalid Jwt token");
        }
        return new JwtAuthenticationToken(token, userId); // No DB call

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
