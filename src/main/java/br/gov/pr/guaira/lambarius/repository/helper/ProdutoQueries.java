package br.gov.pr.guaira.lambarius.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Produto;
import br.gov.pr.guaira.lambarius.repository.filter.ProdutoFilter;

public interface ProdutoQueries {

	public Page<Produto> filtrar(ProdutoFilter filter, Pageable pageable);
}
