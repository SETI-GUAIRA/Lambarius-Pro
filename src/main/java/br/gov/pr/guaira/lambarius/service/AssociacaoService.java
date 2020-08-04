package br.gov.pr.guaira.lambarius.service;

import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Associacao;
import br.gov.pr.guaira.lambarius.exception.AssociacaoExistentException;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidade;
import br.gov.pr.guaira.lambarius.repository.AssociacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AssociacaoService {

  @Autowired
  private AssociacaoRepository associacaoRepository;

  public void salvar(Associacao associacao) {

    Optional<Associacao> associacaoOp = associacaoRepository.findByIgnoreCaseNome(associacao.getNome());

    if (associacaoOp.isPresent() && associacao.isNovo()) {
      throw new AssociacaoExistentException("Associação já cadastrada");
    }

    associacaoRepository.save(associacao);
  }

  public List<Associacao> buscarTodos() {
    return associacaoRepository.findAll();
  }

  public void excluir(Long codigo) {
    try {
      associacaoRepository.deleteById(codigo);
    } catch (DataIntegrityViolationException e) {
      throw new ImpossivelExcluirEntidade("Não foi possível excluir a associação, há pescadores associados");
    }
  }

  public Optional<Associacao> buscarUm(Long codigo) {
    return associacaoRepository.findById(codigo);
  }
}
