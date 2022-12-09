package com.fullstack.irm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "exame")
public class Exame {

    //*** Construtor ***//
    public Exame(Date data_cadastro, String nome, double valor, String informacoes, List<Solicitacao> exames, TipoExame tipoExame) {
        this.data_cadastro = data_cadastro;
        this.nome = nome;
        this.valor = valor;
        this.informacoes = informacoes;
        this.exames = exames;
        this.tipoExame = tipoExame;
    }
    //*****************//

    //*** Chave primária ***//
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer exame_id;
    //*********************//

    //*** Colunas do Banco de Dados ***//
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_cadastro;

    private String nome;

    private double valor;

    private String informacoes;
    //*********************************//

    //*** Relacionamento 1:N com a tabela Solicitaçõs de Exame ***//
    @OneToMany(mappedBy = "exame")
    private List<Solicitacao> exames = new ArrayList<>();
    //************************************************************//

    //*** Relacionamento N:1 com a tabela Tipo de Exame ***//
    @ManyToOne
    @JoinColumn(name = "tipo_exame_id")
    private TipoExame tipoExame;
    //*****************************************************//

}