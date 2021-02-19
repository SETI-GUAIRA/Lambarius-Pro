package br.gov.pr.guaira.lambarius.repository;

import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Produto;
import br.gov.pr.guaira.lambarius.repository.helper.ProdutoQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoQueries{

    Optional<Produto> findByIgnoreCaseNome(String nome);
	
	List<Produto> findAllByOrderByNomeAsc();

}
