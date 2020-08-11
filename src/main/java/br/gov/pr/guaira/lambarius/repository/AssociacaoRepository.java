package br.gov.pr.guaira.lambarius.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pr.guaira.lambarius.domain.Associacao;
import br.gov.pr.guaira.lambarius.repository.helper.AssociacaoQueries;

@Repository
public interface AssociacaoRepository extends JpaRepository<Associacao, Long>, AssociacaoQueries {

  public Optional<Associacao> findByIgnoreCaseNome(String nome);
  public List<Associacao> findAllByOrderByNomeAsc();
}
