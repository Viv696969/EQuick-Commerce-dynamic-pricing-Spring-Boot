package com.vivekemipre.dynamicpricing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/view")
    public ResponseEntity<?> viewCart(Principal principal){

    }



}
