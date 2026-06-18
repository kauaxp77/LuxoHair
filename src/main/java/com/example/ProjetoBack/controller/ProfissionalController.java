package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.Service.AgendamentoServices;
import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {
    @Autowired
    AgendamentoServices agendamentoServices;

    @GetMapping("/meus-agendamentos")
    public ResponseEntity<List<AgendamentoResponseDTO>> listar(){
        return ResponseEntity.ok(agendamentoServices.listarPorProfissional());
    }
}
