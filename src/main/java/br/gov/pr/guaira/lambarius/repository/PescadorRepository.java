package br.gov.pr.guaira.lambarius.repository;

import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Pescador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PescadorRepository extends JpaRepository<Pescador, Long> {

  public Optional<Pescador> findBycpfOuCnpj(String cpfOuCnpj);
}
