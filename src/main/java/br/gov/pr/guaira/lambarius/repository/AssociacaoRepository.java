package br.gov.pr.guaira.lambarius.repository;

import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Associacao;
import br.gov.pr.guaira.lambarius.repository.helper.AssociacaoQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociacaoRepository extends JpaRepository<Associacao, Long>, AssociacaoQueries {

  public Optional<Associacao> findByIgnoreCaseNome(String nome);
}
