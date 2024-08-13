package org.example.payment_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class MainController {

    @GetMapping
    public String greeting() {
        return "payment service greeting";
    }

}
