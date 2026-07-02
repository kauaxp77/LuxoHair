package com.example.ProjetoBack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // Retorna o nome do arquivo HTML (login.html) da pasta templates
        return "login";
    }
}
