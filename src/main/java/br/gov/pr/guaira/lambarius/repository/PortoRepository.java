package br.gov.pr.guaira.lambarius.repository;

import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.repository.helper.PortoQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortoRepository extends JpaRepository<Porto, Long>, PortoQueries{

	Optional<Porto> findByIgnoreCaseNome(String nome);
	
	List<Porto> findAllByOrderByNomeAsc();

}
