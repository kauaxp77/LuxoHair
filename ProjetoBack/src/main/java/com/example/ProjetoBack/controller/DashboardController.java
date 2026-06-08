package com.example.ProjetoBack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String getDashboard() {
        // Esta string "dashboard" diz ao Spring para renderizar o arquivo "dashboard.html"
        return "dashboard";
    }
}
