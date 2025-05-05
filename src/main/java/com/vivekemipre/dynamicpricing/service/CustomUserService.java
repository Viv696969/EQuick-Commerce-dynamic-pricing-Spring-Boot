package com.vivekemipre.dynamicpricing.service;

import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private CustomUserRepository customUserRepository;

    public CustomUser save(CustomUser user){
        return customUserRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        return customUserRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found with email = "+email));
    }



}
