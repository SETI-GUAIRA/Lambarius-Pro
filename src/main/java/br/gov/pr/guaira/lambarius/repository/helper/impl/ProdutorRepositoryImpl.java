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

import br.gov.pr.guaira.lambarius.domain.Produtor;
import br.gov.pr.guaira.lambarius.repository.filter.ProdutorFilter;
import br.gov.pr.guaira.lambarius.repository.helper.ProdutorQueries;
import br.gov.pr.guaira.lambarius.repository.paginacao.PaginacaoUtil;

@Repository
public class ProdutorRepositoryImpl implements ProdutorQueries {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Page<Produtor> filtrar(ProdutorFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produtor> query = builder.createQuery(Produtor.class);
		Root<Produtor> root = query.from(Produtor.class);
		
		Predicate[] filtros = adicionarFiltro(filtro, root);
		query.select(root).where(filtros);
		TypedQuery<Produtor> typedQuery =  (TypedQuery<Produtor>) paginacaoUtil.prepararOrdem(query, root, pageable);
		typedQuery = (TypedQuery<Produtor>) paginacaoUtil.prepararIntervalo(typedQuery, pageable);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, total(filtro));
	}

	private Long total(ProdutorFilter filtro) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<Produtor> root = query.from(Produtor.class);
		
		query.select(criteriaBuilder.count(root));
		query.where(adicionarFiltro(filtro, root));
		
		return manager.createQuery(query).getSingleResult();
	}
	
	private Predicate[] adicionarFiltro(ProdutorFilter filtro, Root<Produtor> root) {
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
