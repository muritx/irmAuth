package com.fullstack.irm.repository;

import com.fullstack.irm.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {
}
