package br.gov.pr.guaira.lambarius.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Nota;
import br.gov.pr.guaira.lambarius.repository.filter.NotaFilter;

public interface NotaQueries {

	public Page<Nota> filtrar(NotaFilter filter, Pageable pageable);
}
