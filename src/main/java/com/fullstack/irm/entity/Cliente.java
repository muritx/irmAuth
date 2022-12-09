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
@Table(name = "cliente")
public class Cliente {

    //*** Construtor ***//
    public Cliente(String cpf, String nome, String rg, String uf_rg, String orgao_emissor, Date data_nascimento, String genero, String email, String telefone, String telefone_secundario, String cep, String logradouro, String numero, String complemento, String bairro, String municipio, String uf, Date ultimaalteracao, List<Solicitacao> solicitacoes) {
        this.cpf = cpf;
        this.nome = nome;
        this.rg = rg;
        this.uf_rg = uf_rg;
        this.orgao_emissor = orgao_emissor;
        this.data_nascimento = data_nascimento;
        this.genero = genero;
        this.email = email;
        this.telefone = telefone;
        this.telefone_secundario = telefone_secundario;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.uf = uf;
        this.ultimaalteracao = ultimaalteracao;
        this.solicitacoes = solicitacoes;
    }
    //*******************//

    //*** Chave primária ***//
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    //*********************//

    //*** Colunas do Banco de Dados ***//
    private String cpf;

    private String nome;

    private String rg;

    private String uf_rg;

    private String orgao_emissor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_nascimento;

    private String genero;

    private String email;

    private String telefone;

    private String telefone_secundario;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String municipio;

    private String uf;

    private Date ultimaalteracao;
    //*********************************//

    //*** Relacionamento 1:N com a tabela Solicitaçõs de Exame ***//
    @OneToMany(mappedBy = "cliente")
    private List<Solicitacao> solicitacoes = new ArrayList<>();
    //************************************************************//

}