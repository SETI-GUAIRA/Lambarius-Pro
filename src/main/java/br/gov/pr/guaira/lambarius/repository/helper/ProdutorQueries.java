package br.gov.pr.guaira.lambarius.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Produtor;
import br.gov.pr.guaira.lambarius.repository.filter.ProdutorFilter;

public interface ProdutorQueries {

	public Page<Produtor> filtrar(ProdutorFilter filter, Pageable pageable);
}
