package com.fullstack.irm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "solicitacao")
public class Solicitacao {

    //*** Construtor ***//
    public Solicitacao(Date data_cadastro, String medico_solicitante, String observacoes, String prestador, Date data_coleta, Date data_entrega, String forma_pagamento, String status, double total, Cliente cliente, Exame exame) {
        this.data_cadastro = data_cadastro;
        this.medico_solicitante = medico_solicitante;
        this.observacoes = observacoes;
        this.prestador = prestador;
        this.data_coleta = data_coleta;
        this.data_entrega = data_entrega;
        this.forma_pagamento = forma_pagamento;
        this.status = status;
        this.total = total;
        this.cliente = cliente;
        this.exame = exame;
    }
    //*******************//

    //*** Chave primária ***//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer solicitacao_id;
    //*********************//

    //*** Colunas do Banco de Dados ***//
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_cadastro;

    private String medico_solicitante;

    private String observacoes;

    private String prestador;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_coleta;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_entrega;

    private String forma_pagamento;

    private String status;

    private double total;
    //*********************************//

    //*** Relacionamento N:1 com a tabela Clientes ***//
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    //************************************************//

    //*** Relacionamento N:1 com a tabela Exames ***//
    @ManyToOne
    @JoinColumn(name = "exame_id")
    private Exame exame;
    //**********************************************//

}
