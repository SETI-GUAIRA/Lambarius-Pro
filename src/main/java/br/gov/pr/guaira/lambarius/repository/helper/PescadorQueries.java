package br.gov.pr.guaira.lambarius.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Pescador;
import br.gov.pr.guaira.lambarius.repository.filter.PescadorFilter;

public interface PescadorQueries {

	public Page<Pescador> filtrar(PescadorFilter filter, Pageable pageable);
}
