package br.gov.pr.guaira.lambarius.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.repository.filter.PortoFilter;

public interface PortoQueries {

	public Page<Porto> filtrar(PortoFilter filter, Pageable pageable);
}
