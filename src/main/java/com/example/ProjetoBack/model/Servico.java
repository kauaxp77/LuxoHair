package com.example.ProjetoBack.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_servico;
    private String nome_servico;
    private LocalTime duracao_estimada;
    private BigDecimal preco;

    public Servico(){}

    public Long getId_servico() {
        return id_servico;
    }

    public void setId_servico(Long id_servico) {
        this.id_servico = id_servico;
    }

    public String getNome_servico() {
        return nome_servico;
    }

    public void setNome_servico(String nome_servico) {
        this.nome_servico = nome_servico;
    }

    public LocalTime getDuracao_estimada() {
        return duracao_estimada;
    }

    public void setDuracao_estimada(LocalTime duracao_estimada) {
        this.duracao_estimada = duracao_estimada;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Servico(Long id_servico, String nome_servico, LocalTime duracao_estimada, BigDecimal preco) {
        this.id_servico = id_servico;
        this.nome_servico = nome_servico;
        this.duracao_estimada = duracao_estimada;
        this.preco = preco;
    }
}
