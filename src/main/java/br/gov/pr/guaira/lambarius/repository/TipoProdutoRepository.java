package br.gov.pr.guaira.lambarius.repository;

import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.TipoProduto;
import br.gov.pr.guaira.lambarius.repository.helper.TipoProdutoQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long>, TipoProdutoQueries{

    Optional<TipoProduto> findByIgnoreCaseNome(String nome);
	
	List<TipoProduto> findAllByOrderByNomeAsc();

}
