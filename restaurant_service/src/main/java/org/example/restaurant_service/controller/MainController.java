package org.example.restaurant_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class MainController {

    @GetMapping
    public String greeting() {
        return "restaurant service greeting";
    }

}
