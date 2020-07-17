package com.br.gov.pr.guaira.lambarius.repository;

import java.util.Optional;

import com.br.gov.pr.guaira.lambarius.domain.Porto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortoRepository extends JpaRepository<Porto, Long>{

	Optional<Porto> findByIgnoreCaseNome(String nome);

}
