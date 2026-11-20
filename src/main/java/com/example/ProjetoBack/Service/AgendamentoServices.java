package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoRequestDTO;
import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoResponseDTO;
import com.example.ProjetoBack.model.Agendamento;
import com.example.ProjetoBack.model.Servico;
import com.example.ProjetoBack.model.user.User;
import com.example.ProjetoBack.repository.AgendamentoRepository;
import com.example.ProjetoBack.repository.ServicoRepository;
import com.example.ProjetoBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class AgendamentoServices {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public List<AgendamentoResponseDTO> listarPorCliente() {
        User cliente = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        List<Agendamento> agendamentos = agendamentoRepository.findByCliente(cliente);
        
        return agendamentos.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<AgendamentoResponseDTO> listarPorProfissional(){
        User profissional = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        List<Agendamento> agendamentos = agendamentoRepository.findByProfissional(profissional);

        return agendamentos.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<AgendamentoResponseDTO> listarTodos(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        return agendamentos.stream()
                .map(this::toDTO)
                .toList();
    }

    private AgendamentoResponseDTO toDTO(Agendamento agendamento) {
        AgendamentoResponseDTO  dto = new AgendamentoResponseDTO();

        dto.setId_agendamento(agendamento.getId_agendamento());
        dto.setCliente(agendamento.getCliente() != null ? agendamento.getCliente().getLogin() : null);
        dto.setProfissional(agendamento.getProfissional() != null ? agendamento.getProfissional().getLogin() : null);
        dto.setServico(agendamento.getServico() != null ? agendamento.getServico().getNome_servico() : null);
        dto.setData_hora(agendamento.getData_hora());
        dto.setStatus(agendamento.getStatus());

        return dto;
    }

    public AgendamentoResponseDTO adicionarAgendamento(@RequestBody AgendamentoRequestDTO dto, User cliente) {

        System.out.println("Profissional ID: " + dto.getProfissionalLogin());
        System.out.println("Servico ID: " + dto.getServicoId());

        User funcionario = (User) userRepository.findByLogin(dto.getProfissionalLogin());

        Servico servico = servicoRepository.findById(dto.getServicoId()).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Agendamento agendamento = new Agendamento();

        agendamento.setCliente(cliente);
        agendamento.setProfissional(funcionario);
        agendamento.setServico(servico);
        agendamento.setData_hora(dto.getData_hora());
        agendamento.setStatus(dto.getStatus());

        Agendamento save = agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(
                save.getId_agendamento(),
                save.getCliente().getLogin(),
                save.getProfissional().getLogin(),
                save.getServico().getNome_servico(),
                save.getData_hora(),
                save.getStatus()
        );
    }
}



