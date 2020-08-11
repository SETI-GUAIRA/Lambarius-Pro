package br.gov.pr.guaira.lambarius.service;

import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.PortoExistentException;
import br.gov.pr.guaira.lambarius.repository.PortoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PortoService {

  @Autowired
  private PortoRepository portoRepository;

  public void salvar(Porto porto) {

    Optional<Porto> portoOp = portoRepository.findByIgnoreCaseNome(porto.getNome());

    if (portoOp.isPresent() && porto.isNovo()) {
      throw new PortoExistentException("Porto já cadastrado");
    }

    portoRepository.save(porto);
  }

  public List<Porto> buscarTodos() {
    return portoRepository.findAllByOrderByNomeAsc();
  }

  public Optional<Porto> buscarUm(Long codigo){
    return portoRepository.findById(codigo);
  }

  public void excluir(Long codigo) {
    try {
      portoRepository.deleteById(codigo);
    } catch (DataIntegrityViolationException e){
      throw new ImpossivelExcluirEntidadeException("Não foi possível excluir o porto, há pescadores associados");
    }
  }

}
