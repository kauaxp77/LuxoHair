package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.Service.AgendamentoServices;
import com.example.ProjetoBack.Service.ClienteServices;
import com.example.ProjetoBack.Service.ServicoServices;
import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoRequestDTO;
import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoResponseDTO;
import com.example.ProjetoBack.model.Agendamento;
import com.example.ProjetoBack.model.Cliente;
import com.example.ProjetoBack.model.Servico;
import com.example.ProjetoBack.repository.AgendamentoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {


    private final AgendamentoServices agendamentoServices;

    @PostMapping
    ResponseEntity<AgendamentoResponseDTO> adicionarAgendamento(@Valid @RequestBody AgendamentoRequestDTO dto){
        Agendamento agendamento = agendamentoServices.adicionarAgendamento(dto);

        AgendamentoResponseDTO response;

        response = new AgendamentoResponseDTO(
                agendamento.getId_agendamento(),
                agendamento.getCliente(),
                agendamento.getProfissional(),
                agendamento.getServico(),
                agendamento.getData_hora(),
                agendamento.getStatus()
        );

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    ResponseEntity<List<Agendamento>> listar(){
        return ResponseEntity.ok(agendamentoServices.listar());
    }

    //fazer cliente ver agendamento dele, nao so lista
}
