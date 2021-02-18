package br.gov.pr.guaira.lambarius.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.domain.TipoProduto;
import br.gov.pr.guaira.lambarius.repository.filter.PortoFilter;
import br.gov.pr.guaira.lambarius.repository.filter.TipoProdutoFilter;

public interface TipoProdutoQueries {

	public Page<TipoProduto> filtrar(TipoProdutoFilter filter, Pageable pageable);
}
