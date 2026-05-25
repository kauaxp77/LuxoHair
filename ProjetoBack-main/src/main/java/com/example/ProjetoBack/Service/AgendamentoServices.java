package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoRequestDTO;
import com.example.ProjetoBack.model.Agendamento;
import com.example.ProjetoBack.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class AgendamentoServices {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    public Agendamento adicionarAgendamento(@RequestBody AgendamentoRequestDTO dto) {
        Agendamento agendamento = new Agendamento();

        agendamento.setCliente(dto.getCliente());
        agendamento.setProfissional(dto.getProfissional());
        agendamento.setProfissional(dto.getProfissional());
        agendamento.setData_hora(dto.getData_hora());
        agendamento.setStatus(dto.getStatus());

        return agendamentoRepository.save(agendamento);
    }
}



