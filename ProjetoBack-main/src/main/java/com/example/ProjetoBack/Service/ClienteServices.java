package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.dto.clientesDTOs.ClienteRequestDTO;
import com.example.ProjetoBack.model.Cliente;
import com.example.ProjetoBack.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class ClienteServices {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente adicionarCliente(@RequestBody ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
}
