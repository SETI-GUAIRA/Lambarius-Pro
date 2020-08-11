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

import br.gov.pr.guaira.lambarius.domain.Pescador;
import br.gov.pr.guaira.lambarius.repository.filter.PescadorFilter;
import br.gov.pr.guaira.lambarius.repository.helper.PescadorQueries;
import br.gov.pr.guaira.lambarius.repository.paginacao.PaginacaoUtil;

@Repository
public class PescadorRepositoryImpl implements PescadorQueries {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Page<Pescador> filtrar(PescadorFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pescador> query = builder.createQuery(Pescador.class);
		Root<Pescador> root = query.from(Pescador.class);
		
		Predicate[] filtros = adicionarFiltro(filtro, root);
		query.select(root).where(filtros);
		TypedQuery<Pescador> typedQuery =  (TypedQuery<Pescador>) paginacaoUtil.prepararOrdem(query, root, pageable);
		typedQuery = (TypedQuery<Pescador>) paginacaoUtil.prepararIntervalo(typedQuery, pageable);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, total(filtro));
	}

	private Long total(PescadorFilter filtro) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<Pescador> root = query.from(Pescador.class);
		
		query.select(criteriaBuilder.count(root));
		query.where(adicionarFiltro(filtro, root));
		
		return manager.createQuery(query).getSingleResult();
	}
	
	private Predicate[] adicionarFiltro(PescadorFilter filtro, Root<Pescador> root) {
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
