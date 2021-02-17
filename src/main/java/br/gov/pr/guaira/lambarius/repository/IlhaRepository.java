package br.gov.pr.guaira.lambarius.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pr.guaira.lambarius.domain.Ilha;
import br.gov.pr.guaira.lambarius.repository.helper.IlhaQueries;

@Repository
public interface IlhaRepository extends JpaRepository<Ilha, Long>, IlhaQueries {

  Optional<Ilha> findByIgnoreCaseNome(String nome);
  
}
