package com.dksoft.formshiftserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    public MainController() {
    }

    @GetMapping("/")
    public String hello() {
        return "Welcome to host!";
    }
}
