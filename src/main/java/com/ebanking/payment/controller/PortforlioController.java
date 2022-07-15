package com.ebanking.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortforlioController {

    @GetMapping("/")
    public String portfolio() {
        return "Welcome to Cryto Portfolio!";
    }
}
