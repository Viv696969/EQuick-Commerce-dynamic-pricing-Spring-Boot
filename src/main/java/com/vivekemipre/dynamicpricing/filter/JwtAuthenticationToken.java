package com.vivekemipre.dynamicpricing.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    private String userId;

    public JwtAuthenticationToken(String token){
        super(null);
        this.token=token;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(String token, String userId) {
        super(null);
        this.token = token;
        this.userId = userId;
        setAuthenticated(true);
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return token;
    }


}
