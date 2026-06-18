package com.example.ProjetoBack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/cliente")
    public String cliente(){
        return "cliente";
    }

    @GetMapping("/funcionario")
    public String funcionario(){
        return "dashboard-funcionaria";
    }

    @GetMapping("/gerente")
    public String Gerente(){
        return "dashboard-gerente";
    }


}
