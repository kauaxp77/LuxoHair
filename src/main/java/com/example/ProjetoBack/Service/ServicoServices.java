package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.dto.servicoDTOs.ServicoRequestDTO;
import com.example.ProjetoBack.dto.servicoDTOs.ServicoResponseDTO;
import com.example.ProjetoBack.model.Servico;
import com.example.ProjetoBack.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service

public class ServicoServices {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<ServicoResponseDTO> listar() {
        List<Servico> servicos = servicoRepository.findAll();

        return servicos.stream()
                .map(this::toDTO)
                .toList();
    }

    public ServicoResponseDTO toDTO(Servico servico){
        ServicoResponseDTO dto = new ServicoResponseDTO();

        dto.setId_servico(servico.getId_servico());
        dto.setNome_servico(servico.getNome_servico());
        dto.setDuracao_estimada(servico.getDuracao_estimada());
        dto.setPreco(servico.getPreco());

        return dto;
    }

    public ServicoResponseDTO adicionarServico(ServicoRequestDTO dto) {

        Servico servico = new Servico();

        servico.setNome_servico(dto.getNome_servico());
        servico.setDuracao_estimada(dto.getDuracao_estimada());
        servico.setPreco(dto.getPreco());

        servicoRepository.save(servico);

        return new ServicoResponseDTO(
                servico.getId_servico(),
                servico.getNome_servico(),
                servico.getDuracao_estimada(),
                servico.getPreco()
        );

    }

}
