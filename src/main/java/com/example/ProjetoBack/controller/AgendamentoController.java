package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.Service.AgendamentoServices;
import com.example.ProjetoBack.model.Agendamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {


    private final AgendamentoServices agendamentoServices;

    public AgendamentoController(AgendamentoServices agendamentoServices) {
        this.agendamentoServices = agendamentoServices;
    }

    @PostMapping
    ResponseEntity<Agendamento> adicionarAgendamento(@RequestBody Agendamento agendamento){ //botar dto dps
        Agendamento agendamento1 = agendamentoServices.adicionarAgendamento(agendamento); //dto

        Agendamento response; // dto response

        response = new Agendamento( //
                agendamento1.getId_agendamento(),
                agendamento1.getCliente(),
                agendamento1.getProfissional(),
                agendamento1.getServico(),
                agendamento1.getData_hora(),
                agendamento1.getStatus()
        );

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    ResponseEntity<List<Agendamento>> listar(){
        return ResponseEntity.ok(agendamentoServices.listar());
    }

    //fazer cliente ver agendamento dele, nao so lista
}
