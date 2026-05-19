package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.model.Profissional;
import com.example.ProjetoBack.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping
    public List<Profissional> listar() {
        return profissionalRepository.findAll();
    }

    @PostMapping
    public Profissional adicionar(@RequestBody Profissional profissional) {
        return profissionalRepository.save(profissional);
    }
}
