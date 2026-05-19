package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.model.Agendamento;
import com.example.ProjetoBack.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    @PostMapping
    public Agendamento adicionar(@RequestBody Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }
}
