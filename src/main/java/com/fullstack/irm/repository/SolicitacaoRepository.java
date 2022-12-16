package com.fullstack.irm.repository;

import com.fullstack.irm.entity.Cliente;
import com.fullstack.irm.entity.Solicitacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {
    
    List<Solicitacao> findSolicitacaoByCliente(Cliente cliente);

}
