package br.gov.pr.guaira.lambarius.repository;

import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Pescador;
import br.gov.pr.guaira.lambarius.domain.TipoProduto;
import br.gov.pr.guaira.lambarius.repository.helper.PescadorQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PescadorRepository extends JpaRepository<Pescador, Long>, PescadorQueries {

  public Optional<Pescador> findBycpfOuCnpj(String cpfOuCnpj); 
  List<Pescador> findAllByOrderByNomeAsc();
}
