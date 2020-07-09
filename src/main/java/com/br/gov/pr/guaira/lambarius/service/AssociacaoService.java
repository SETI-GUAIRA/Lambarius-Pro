package com.br.gov.pr.guaira.lambarius.service;

import java.util.List;

import com.br.gov.pr.guaira.lambarius.domain.Associacao;
import com.br.gov.pr.guaira.lambarius.repository.AssociacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociacaoService {

  @Autowired
  private AssociacaoRepository associacaoRepository;

  public void salvar(Associacao associacao) {
    associacaoRepository.save(associacao);
  }

  public List<Associacao> buscarTodos() {
    return associacaoRepository.findAll();
  }

  public void excluir(Long codigo) {
    associacaoRepository.deleteById(codigo);
  }
}
