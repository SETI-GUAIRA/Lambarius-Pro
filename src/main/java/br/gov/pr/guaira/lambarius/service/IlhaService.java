package br.gov.pr.guaira.lambarius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.gov.pr.guaira.lambarius.domain.Ilha;
import br.gov.pr.guaira.lambarius.exception.IlhaExistentException;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidade;
import br.gov.pr.guaira.lambarius.repository.IlhaRepository;

@Service
public class IlhaService {

  @Autowired
  private IlhaRepository ilhaRepository;

  public void salvar(Ilha ilha) {

    Optional<Ilha> ilhaOp = ilhaRepository.findByIgnoreCaseNome(ilha.getNome());

    if (ilhaOp.isPresent() && ilha.isNovo()) {
      throw new IlhaExistentException("Ilha já cadastrada");
    }

    ilhaRepository.save(ilha);
  }

  public List<Ilha> buscarTodos() {
    return ilhaRepository.findAll();
  }

  public Optional<Ilha> buscarUm(Long codigo) {
    return ilhaRepository.findById(codigo);
  }

  public void excluir(Long codigo) {
    try {
      ilhaRepository.deleteById(codigo);
    } catch (DataIntegrityViolationException e) {
      throw new ImpossivelExcluirEntidade("Não foi possível excluir o porto, há pescadores associados");
    }
  }
}
