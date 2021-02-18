package br.gov.pr.guaira.lambarius.service;

import java.util.List;
import java.util.Optional;

import br.gov.pr.guaira.lambarius.domain.Porto;
import br.gov.pr.guaira.lambarius.domain.TipoProduto;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.TipoProdutoExistentException;
import br.gov.pr.guaira.lambarius.repository.TipoProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TipoProdutoService {

  @Autowired
  private TipoProdutoRepository tipoProdutoRepository;

  public void salvar(TipoProduto tipoProduto) {

    Optional<TipoProduto> tipoProdutoOp = tipoProdutoRepository.findByIgnoreCaseNome(tipoProduto.getNome());

    if (tipoProdutoOp.isPresent() && tipoProduto.isNovo()) {
      throw new TipoProdutoExistentException("Tipo Produto já cadastrado");
    }

    tipoProdutoRepository.save(tipoProduto);
  }

  public List<TipoProduto> buscarTodos() {
    return tipoProdutoRepository.findAllByOrderByNomeAsc();
  }

  public Optional<TipoProduto> buscarUm(Long codigo){
    return tipoProdutoRepository.findById(codigo);
  }

  public void excluir(Long codigo) {
    try {
    	tipoProdutoRepository.deleteById(codigo);
    } catch (DataIntegrityViolationException e){
      throw new ImpossivelExcluirEntidadeException("Não foi possível excluir o tipo de produto, está em uso!");
    }
  }

}
