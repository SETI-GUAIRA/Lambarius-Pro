package br.gov.pr.guaira.lambarius.repository;

import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Nota;
import br.gov.pr.guaira.lambarius.repository.helper.NotaQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>, NotaQueries{

	Optional<Nota> findByIgnoreCaseNrNota(String nrNota);	

}
