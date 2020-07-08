package com.br.gov.pr.guaira.lambarius.service;

import java.util.List;

import com.br.gov.pr.guaira.lambarius.domain.Porto;
import com.br.gov.pr.guaira.lambarius.repository.PortoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortoService {

  @Autowired
  private PortoRepository portoRepository;

  public void salvar(Porto porto) {
    portoRepository.save(porto);
  }

  public List<Porto> buscarTodos() {
    return portoRepository.findAll();
  }

  public void excluir(Long codigo) {
    portoRepository.deleteById(codigo);
  }

}
