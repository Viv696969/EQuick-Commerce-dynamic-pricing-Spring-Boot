package com.vivekemipre.dynamicpricing.config;


import com.vivekemipre.dynamicpricing.filter.JWTAuthenticationFilter;
import com.vivekemipre.dynamicpricing.filter.JWTValidationFilter;
import com.vivekemipre.dynamicpricing.filter.JwtAuthenticationProvider;
import com.vivekemipre.dynamicpricing.filter.JwtAuthenticationToken;
import com.vivekemipre.dynamicpricing.service.CustomUserService;
import com.vivekemipre.dynamicpricing.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserService userService;

    @Autowired
    private JwtUtility jwtUtility;


    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
//        authenticationProvider.setUserDetailsService(userService);
//        return authenticationProvider;
//    }
//
//    @Bean
//    public JwtAuthenticationProvider jwtAuthenticationProvider(){
//        return new JwtAuthenticationProvider(jwtUtility);
//    }
//
//
//
//    @Bean
//    public AuthenticationManager getAuthenticationManager(){
//        return new ProviderManager(Arrays.asList(
//                daoAuthenticationProvider(),
//                jwtAuthenticationProvider()
//        ));
//    }
//
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
//                                                   AuthenticationManager authenticationManager,
//                                                   JwtUtility jwtUtility
    ) throws Exception {


//        JWTAuthenticationFilter jwtAuthenticationFilter=new JWTAuthenticationFilter(jwtUtility,authenticationManager);
//        JWTValidationFilter jwtValidationFilter=new JWTValidationFilter(authenticationManager);

        http
//                .authorizeHttpRequests(
//                        auth->auth.requestMatchers("/auth/register","/test/test").permitAll().anyRequest().authenticated()
//                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(csrf->csrf.disable());
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(jwtValidationFilter,JWTAuthenticationFilter.class);

        return http.build();

    }


}
