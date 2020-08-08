package br.gov.pr.guaira.lambarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pr.guaira.lambarius.domain.Grupo;


@Repository
public interface Grupos extends JpaRepository<Grupo, Long>{

}
