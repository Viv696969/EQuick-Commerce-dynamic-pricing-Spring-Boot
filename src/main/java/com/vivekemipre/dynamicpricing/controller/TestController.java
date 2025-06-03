package com.vivekemipre.dynamicpricing.controller;


import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public String testSecurity( HttpServletRequest request){
        String userAgentString = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);

        String operatingSystem = userAgent.getOperatingSystem().getName();
        String deviceType = userAgent.getOperatingSystem().getDeviceType().getName();
        String browser = userAgent.getBrowser().getName();

        Map<String, String> info = new HashMap<>();
        info.put("deviceType", deviceType); // MOBILE / COMPUTER / TABLET etc.
        info.put("operatingSystem", operatingSystem); // e.g. Android, iOS, Windows
        info.put("browser", browser);
        info.put("userAgent", userAgentString);
        System.out.println(info);
        return String.format("Welcome Back %s","Server is On");
//        return String.format("Welcome Back %s",principal.getName());
    }

//    @GetMapping("/device-info")
//    public Map<String, String> getDeviceInfo(HttpServletRequest request) {
//        String userAgentString = request.getHeader("User-Agent");
//        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
//
//        String operatingSystem = userAgent.getOperatingSystem().getName();
//        String deviceType = userAgent.getOperatingSystem().getDeviceType().getName();
//        String browser = userAgent.getBrowser().getName();
//
//        Map<String, String> info = new HashMap<>();
//        info.put("deviceType", deviceType); // MOBILE / COMPUTER / TABLET etc.
//        info.put("operatingSystem", operatingSystem); // e.g. Android, iOS, Windows
//        info.put("browser", browser);
//        info.put("userAgent", userAgentString);
//
//        return info;
//    }

}
