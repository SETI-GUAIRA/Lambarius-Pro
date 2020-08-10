package br.gov.pr.guaira.lambarius.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Associacao;
import br.gov.pr.guaira.lambarius.repository.filter.AssociacaoFilter;

public interface AssociacaoQueries {

	public Page<Associacao> filtrar(AssociacaoFilter filter, Pageable pageable);
}
