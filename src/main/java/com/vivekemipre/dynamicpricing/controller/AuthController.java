package com.vivekemipre.dynamicpricing.controller;


import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam("email") String email,
                                          @RequestParam("password") String password){
        String encodedPassword=passwordEncoder.encode(password);
        CustomUser user= new CustomUser(encodedPassword,email);
        userService.save(user);
        return ResponseEntity.ok("Successfully saved user..");

    }

}
