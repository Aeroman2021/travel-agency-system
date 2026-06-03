package com.myproject.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @GetMapping("/test")
    public String test(Authentication authentication){
        return "Hello from payment-service : " +
                 authentication.getDeclaringClass().getName();
    }
}
