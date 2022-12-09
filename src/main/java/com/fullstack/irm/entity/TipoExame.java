package com.fullstack.irm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tipo_exame")
public class TipoExame {

    //*** Construtor ***//
    public TipoExame(String nome, List<Exame> exames) {
        this.nome = nome;
        this.exames = exames;
    }
    //*****************//

    //*** Chave primária ***//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer tipo_id;
    //*********************//

    //*** Colunas do Banco de Dados ***//
    private String nome;
    //*********************************//

    //*** Relacionamento 1:N com a tabela Exames ***//
    @OneToMany(mappedBy = "tipoExame")
    private List<Exame> exames = new ArrayList<>();
    //**********************************************//

}
