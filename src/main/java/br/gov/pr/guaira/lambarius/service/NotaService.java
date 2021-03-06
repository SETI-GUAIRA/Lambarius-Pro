package br.gov.pr.guaira.lambarius.service;

import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Nota;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.NotaExistentException;
import br.gov.pr.guaira.lambarius.repository.NotaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class NotaService {

  @Autowired
  private NotaRepository notaRepository;

  public void salvar(Nota nota) {

    Optional<Nota> portoOp = notaRepository.findByIgnoreCaseNome(nota.getNome());

    if (portoOp.isPresent() && nota.isNovo()) {
      throw new NotaExistentException("Nota já cadastrado");
    }

    notaRepository.save(nota);
  }

  public List<Nota> buscarTodos() {
    return notaRepository.findAllByOrderByNomeAsc();
  }

  public Optional<Nota> buscarUm(Long codigo){
    return notaRepository.findById(codigo);
  }

  public void excluir(Long codigo) {
    try {
    	notaRepository.deleteById(codigo);
    } catch (DataIntegrityViolationException e){
      throw new ImpossivelExcluirEntidadeException("Não foi possível excluir o Nota, esta em uso");
    }
  }

}
