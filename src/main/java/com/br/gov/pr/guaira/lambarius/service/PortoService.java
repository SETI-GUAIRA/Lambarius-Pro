package com.br.gov.pr.guaira.lambarius.service;

import java.util.List;
import java.util.Optional;

import com.br.gov.pr.guaira.lambarius.domain.Porto;
import com.br.gov.pr.guaira.lambarius.exception.PortoExistentException;
import com.br.gov.pr.guaira.lambarius.repository.PortoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortoService {

  @Autowired
  private PortoRepository portoRepository;

  public void salvar(Porto porto) {

    Optional<Porto> portoOp = portoRepository.findByIgnoreCaseNome(porto.getNome());

    if (portoOp.isPresent()) {
      throw new PortoExistentException("Porto já cadastrado");
    }

    portoRepository.save(porto);
  }

  public List<Porto> buscarTodos() {
    return portoRepository.findAll();
  }

  public Optional<Porto> buscarUm(Long codigo){
    return portoRepository.findById(codigo);
  }

  public void excluir(Long codigo) {
    portoRepository.deleteById(codigo);
  }

}
