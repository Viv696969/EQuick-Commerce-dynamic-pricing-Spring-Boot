package com.vivekemipre.dynamicpricing.controller;


import com.vivekemipre.dynamicpricing.dto.LoginRequest;
import com.vivekemipre.dynamicpricing.entity.CustomUser;
import com.vivekemipre.dynamicpricing.service.CustomUserService;
import com.vivekemipre.dynamicpricing.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserService userService;

    @Autowired
    private JwtUtility jwtUtility;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody LoginRequest loginRequest){
        String encodedPassword=passwordEncoder.encode(loginRequest.getPassword());
        CustomUser user= new CustomUser(encodedPassword,loginRequest.getEmail());
        userService.save(user);
        String token=jwtUtility.generateToken(user.getId());
        return ResponseEntity.status(201)
                .header("Authorization", "Bearer " + token)
                .body("Successfully created user..");

    }

}
