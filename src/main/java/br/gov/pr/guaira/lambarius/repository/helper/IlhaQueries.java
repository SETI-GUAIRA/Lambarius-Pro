package br.gov.pr.guaira.lambarius.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.pr.guaira.lambarius.domain.Ilha;
import br.gov.pr.guaira.lambarius.repository.filter.IlhaFilter;

public interface IlhaQueries {

	public Page<Ilha> filtrar(IlhaFilter filter, Pageable pageable);
}
