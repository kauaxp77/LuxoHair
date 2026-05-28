package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.dto.profissionalDTOs.ProfissionalRequestDTO;
import com.example.ProjetoBack.model.Profissional;
import com.example.ProjetoBack.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProfissionalServices {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public List<Profissional> listar() {
        return profissionalRepository.findAll();
    }

    public Profissional adicionar(@RequestBody ProfissionalRequestDTO dto) {
        Profissional profissional = new Profissional();

        profissional.setNome(dto.getNome());
        profissional.setEspecialidade(dto.getEspecialidade());


        return profissionalRepository.save(profissional);
    }

}
