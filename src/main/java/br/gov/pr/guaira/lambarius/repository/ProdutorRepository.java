package br.gov.pr.guaira.lambarius.repository;

import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Produtor;
import br.gov.pr.guaira.lambarius.repository.helper.ProdutorQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long>, ProdutorQueries {

  public Optional<Produtor> findBycpfOuCnpj(String cpfOuCnpj);
}
