package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.Service.ServicoServices;
import com.example.ProjetoBack.dto.servicoDTOs.ServicoRequestDTO;
import com.example.ProjetoBack.dto.servicoDTOs.ServicoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoServices servicoServices;

    @PostMapping("/novo-servico")
    public ResponseEntity<ServicoResponseDTO> adicionarServico(@Valid @RequestBody ServicoRequestDTO dto){
        return ResponseEntity.ok(servicoServices.adicionarServico(dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ServicoResponseDTO>> listarServicos(){
        return ResponseEntity.ok(servicoServices.listar());
    }
}
