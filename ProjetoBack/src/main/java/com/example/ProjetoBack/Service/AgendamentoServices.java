package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.model.Agendamento;
import com.example.ProjetoBack.model.Cliente;
import com.example.ProjetoBack.repository.AgendamentoRepository;
import com.example.ProjetoBack.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoServices {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    public Agendamento adicionarAgendamento(@RequestBody Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }
}



