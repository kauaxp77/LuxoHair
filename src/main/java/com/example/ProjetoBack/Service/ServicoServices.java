package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.model.Servico;
import com.example.ProjetoBack.repository.ServicoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

    public Servico adicionarServico(@RequestBody Servico servico) {
        return servicoRepository.save(servico);
    }

}
