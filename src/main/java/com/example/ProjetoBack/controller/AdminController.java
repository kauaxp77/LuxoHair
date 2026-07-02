package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.Service.ClienteServices;
import com.example.ProjetoBack.Service.ServicoServices;
import com.example.ProjetoBack.model.Cliente;
import com.example.ProjetoBack.model.Servico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ServicoServices servicoServices;

    private final ClienteServices clienteServices;
    @PostMapping("/servicos")
    ResponseEntity<Servico> adicionarServico(@RequestBody Servico servico){
        Servico servico1 = servicoServices.adicionarServico(servico);

        Servico response;

        response = new Servico(
                servico1.getId_servico(),
                servico1.getNome_servico(),
                servico1.getDuracao_estimada(),
                servico1.getPreco()
        );

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/servicos")
    ResponseEntity<List<Servico>> listarServico(){
        return ResponseEntity.ok(servicoServices.listar());
    }


    @PostMapping("/clientes")
    ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente){
        Cliente cliente1 = clienteServices.adicionarCliente(cliente);

        Cliente response;

        response = new Cliente(
                cliente1.getId_cliente(),
                cliente1.getNome(),
                cliente1.getTelefone(),
                cliente1.getEmail()
        );

        return ResponseEntity.status(201).body(response);

    }

    @GetMapping("/clientes")
    ResponseEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(clienteServices.listar());
    }

}
