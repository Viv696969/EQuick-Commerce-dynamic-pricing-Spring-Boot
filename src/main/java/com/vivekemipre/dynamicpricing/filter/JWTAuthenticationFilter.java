package com.vivekemipre.dynamicpricing.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivekemipre.dynamicpricing.dto.LoginRequest;
import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.util.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtility jwtUtility;
    private AuthenticationManager authenticationManager;

    @Autowired
    public JWTAuthenticationFilter(JwtUtility jwtUtility, AuthenticationManager authenticationManager) {

        this.authenticationManager=authenticationManager;
        this.jwtUtility=jwtUtility;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (!request.getServletPath().equals("/auth/login")){
            filterChain.doFilter(request,response);
            return;
        }

        ObjectMapper mapper=new ObjectMapper();
        LoginRequest loginRequest=mapper.readValue(request.getInputStream(), LoginRequest.class);

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());

        Authentication authenticationResult=authenticationManager.authenticate(authenticationToken);

        if (authenticationResult.isAuthenticated()){
            CustomUser userDetails = (CustomUser) authenticationResult.getPrincipal();
            String userId = userDetails.getId();
            String token= jwtUtility.generateToken(userId);
            response.setHeader("Authorization","Bearer "+token);
        }


    }
}
