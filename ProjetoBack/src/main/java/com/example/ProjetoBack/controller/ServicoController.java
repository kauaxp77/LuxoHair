package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.model.Servico;
import com.example.ProjetoBack.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    @PostMapping
    public Servico adicionar(@RequestBody Servico servico) {
        return servicoRepository.save(servico);
    }
}
