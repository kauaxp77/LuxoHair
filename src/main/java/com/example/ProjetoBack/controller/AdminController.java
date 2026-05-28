package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.Service.ClienteServices;
import com.example.ProjetoBack.Service.ProfissionalServices;
import com.example.ProjetoBack.Service.ServicoServices;
import com.example.ProjetoBack.dto.clientesDTOs.ClienteRequestDTO;
import com.example.ProjetoBack.dto.clientesDTOs.ClienteResponseDTO;
import com.example.ProjetoBack.dto.profissionalDTOs.ProfissionalRequestDTO;
import com.example.ProjetoBack.dto.profissionalDTOs.ProfissionalResponseDTO;
import com.example.ProjetoBack.dto.servicoDTOs.ServicoResponseDTO;
import com.example.ProjetoBack.dto.servicoDTOs.ServicoRequestDTO;
import com.example.ProjetoBack.model.Cliente;
import com.example.ProjetoBack.model.Profissional;
import com.example.ProjetoBack.model.Servico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    //MUDAR RETORNO DE LISTAS PARA *RESPONSEDTO DEPOIS

    private final ServicoServices servicoServices;

    private final ClienteServices clienteServices;

    private final ProfissionalServices profissionalServices;

    @PostMapping("/servicos")
    public ResponseEntity<ServicoResponseDTO> adicionarServico(@Valid @RequestBody ServicoRequestDTO dto){
        Servico servico = servicoServices.adicionarServico(dto);

        ServicoResponseDTO response;

        response = new ServicoResponseDTO(
                servico.getId_servico(),
                servico.getNome_servico(),
                servico.getDuracao_estimada(),
                servico.getPreco()
        );

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/servicos")
    public ResponseEntity<List<Servico>> listarServico(){
        return ResponseEntity.ok(servicoServices.listar());
    }


    @PostMapping("/clientes")
    public ResponseEntity<ClienteResponseDTO> adicionarCliente(@Valid @RequestBody ClienteRequestDTO dto){
        Cliente cliente = clienteServices.adicionarCliente(dto);

        ClienteResponseDTO response;

        response = new ClienteResponseDTO(
                cliente.getId_cliente(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail()
        );

        return ResponseEntity.status(201).body(response);

    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.ok(clienteServices.listar());
    }

    @PostMapping("/profissional")
    public ResponseEntity<ProfissionalResponseDTO>  adicionarProficional(@Valid @RequestBody ProfissionalRequestDTO dto) {
        Profissional profissional = profissionalServices.adicionar(dto);

        ProfissionalResponseDTO response;

        response = new ProfissionalResponseDTO(
                profissional.getId_profissional(),
                profissional.getNome(),
                profissional.getEspecialidade()
        );
        return ResponseEntity.status(201).body(response)    ;
    }

    @GetMapping("/profissional")
    public ResponseEntity<List<Profissional>> listarProficionail() {
        return ResponseEntity.ok(profissionalServices.listar());
    }
}
