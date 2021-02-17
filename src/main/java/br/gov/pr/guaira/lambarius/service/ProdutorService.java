package br.gov.pr.guaira.lambarius.service;

import br.gov.pr.guaira.lambarius.repository.ProdutorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Produtor;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;

import br.gov.pr.guaira.lambarius.exception.ProdutorExistentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProdutorService {

	@Autowired
	private ProdutorRepository produtorRepository;

	public void salvar(Produtor produtor) {

		Optional<Produtor> produtorOp = produtorRepository.findBycpfOuCnpj(produtor.getCpfOuCnpj());

		if (produtorOp.isPresent() && produtor.isNovo()) {
			throw new ProdutorExistentException("Produtor já cadastrado verifique o CPF informado!");
		}

		if (produtor.isNovo()) {
			produtor.setHoraCadastro(LocalDate.now());
		} else {
			produtor.setHoraAtualizacao(LocalDate.now());
		}
		
		if(produtor.getFeirante() == null) {
			produtor.setFeirante(false);
		}

		produtorRepository.save(produtor);
	}

	public List<Produtor> buscarTodos() {
		return produtorRepository.findAll();
	}

	public void excluir(Long codigo) {
		try {
			produtorRepository.deleteById(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelExcluirEntidadeException(
					"Não foi possível excluir o produtor, há registros associados");
		}
	}
	public Optional<Produtor> buscarUm(Long codigo) {
		return produtorRepository.findById(codigo);
	}
}
