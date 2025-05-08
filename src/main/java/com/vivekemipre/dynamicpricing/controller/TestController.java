package com.vivekemipre.dynamicpricing.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public String testSecurity(Principal principal){

        return String.format("Welcome Back %s",principal.getName());
    }

}
