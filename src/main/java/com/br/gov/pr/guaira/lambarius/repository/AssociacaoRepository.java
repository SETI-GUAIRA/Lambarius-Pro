package com.br.gov.pr.guaira.lambarius.repository;

import com.br.gov.pr.guaira.lambarius.domain.Associacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociacaoRepository extends JpaRepository<Associacao, Long> {

}