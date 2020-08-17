package br.gov.pr.guaira.lambarius.service;

import br.gov.pr.guaira.lambarius.repository.PescadorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Pescador;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.PescadorExistentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PescadorService {

	@Autowired
	private PescadorRepository pescadorRepository;

	public void salvar(Pescador pescador) {

		Optional<Pescador> pescadorOp = pescadorRepository.findBycpfOuCnpj(pescador.getCpfOuCnpj());

		if (pescadorOp.isPresent() && pescador.isNovo()) {
			throw new PescadorExistentException("Pescador já cadastrado verifique o CPF informado!");
		}

		if (pescador.isNovo()) {
			pescador.setHoraCadastro(LocalDate.now());
		} else {
			pescador.setHoraAtualizacao(LocalDate.now());
		}
		
		if(pescador.getAposentado() == null) {
			pescador.setAposentado(false);
		}

		pescadorRepository.save(pescador);
	}

	public List<Pescador> buscarTodos() {
		return pescadorRepository.findAll();
	}

	public void excluir(Long codigo) {
		try {
			pescadorRepository.deleteById(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelExcluirEntidadeException(
					"Não foi possível excluir o pescador, há registros associados");
		}
	}

	public Optional<Pescador> buscarUm(Long codigo) {
		return pescadorRepository.findById(codigo);
	}
}
