package com.br.gov.pr.guaira.lambarius.service;

import com.br.gov.pr.guaira.lambarius.repository.PescadorRepository;

import java.util.List;
import java.util.Optional;

import com.br.gov.pr.guaira.lambarius.domain.Pescador;
import com.br.gov.pr.guaira.lambarius.exception.PescadorExistentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PescadorService {

  @Autowired
  private PescadorRepository pescadorRepository;

  public void salvar(Pescador pescador) {

    Optional<Pescador> pescadorOp = pescadorRepository.findBycpfOuCnpj(pescador.getCpfOuCnpj());

    if (pescadorOp.isPresent()) {
      throw new PescadorExistentException("Pescador já cadastrado");
    }

    pescadorRepository.save(pescador);
  }

  public List<Pescador> buscarTodos() {
    return pescadorRepository.findAll();
  }

  public void excluir(Long codigo) {
    pescadorRepository.deleteById(codigo);
  }

  public Optional<Pescador> buscarUm(Long codigo) {
    return pescadorRepository.findById(codigo);
  }
}
