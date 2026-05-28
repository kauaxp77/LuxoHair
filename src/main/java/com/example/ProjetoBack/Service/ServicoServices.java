package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.dto.servicoDTOs.ServicoRequestDTO;
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

    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    public Servico adicionarServico(@RequestBody ServicoRequestDTO dto) {
        Servico servico = new Servico();

        servico.setNome_servico(dto.getNome_servico());
        servico.setDuracao_estimada(dto.getDuracao_estimada());
        servico.setPreco(dto.getPreco());

        return servicoRepository.save(servico);
    }

}
