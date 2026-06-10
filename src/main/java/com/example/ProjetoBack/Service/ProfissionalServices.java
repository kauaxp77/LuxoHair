package com.example.ProjetoBack.Service;

import com.example.ProjetoBack.model.Profissional;
import com.example.ProjetoBack.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ProfissionalServices {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public List<Profissional> listar() {
        return profissionalRepository.findAll();
    }

    public Profissional adicionar(@RequestBody Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

}
