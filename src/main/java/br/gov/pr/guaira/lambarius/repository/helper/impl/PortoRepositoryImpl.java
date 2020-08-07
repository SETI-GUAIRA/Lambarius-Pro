package br.gov.pr.guaira.lambarius.repository.helper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.repository.filter.PortoFilter;
import br.gov.pr.guaira.lambarius.repository.helper.PortoQueries;
import br.gov.pr.guaira.lambarius.repository.paginacao.PaginacaoUtil;

@Repository
public class PortoRepositoryImpl implements PortoQueries {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Page<Porto> filtrar(PortoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Porto> query = builder.createQuery(Porto.class);
		Root<Porto> root = query.from(Porto.class);
		
		Predicate[] filtros = adicionarFiltro(filtro, root);
		query.select(root).where(filtros);
		TypedQuery<Porto> typedQuery =  (TypedQuery<Porto>) paginacaoUtil.prepararOrdem(query, root, pageable);
		typedQuery = (TypedQuery<Porto>) paginacaoUtil.prepararIntervalo(typedQuery, pageable);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, total(filtro));
	}

	private Long total(PortoFilter filtro) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<Porto> root = query.from(Porto.class);
		
		query.select(criteriaBuilder.count(root));
		query.where(adicionarFiltro(filtro, root));
		
		return manager.createQuery(query).getSingleResult();
	}
	
	private Predicate[] adicionarFiltro(PortoFilter filtro, Root<Porto> root) {
		List<Predicate> predicateList = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())){
				predicateList.add(builder.like(builder.upper(root.get("nome")), "%"+filtro.getNome().toUpperCase()+"%"));
			}
		}
		Predicate[] predArray = new Predicate[predicateList.size()];
		return predicateList.toArray(predArray);
	}


}
