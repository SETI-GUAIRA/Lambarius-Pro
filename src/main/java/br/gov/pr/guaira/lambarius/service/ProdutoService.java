package br.gov.pr.guaira.lambarius.service;

import java.util.List;
import java.util.Optional;


import br.gov.pr.guaira.lambarius.domain.Produto;
import br.gov.pr.guaira.lambarius.exception.ImpossivelExcluirEntidadeException;
import br.gov.pr.guaira.lambarius.exception.ProdutoExistentException;
import br.gov.pr.guaira.lambarius.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  public void salvar(Produto produto) {

    Optional<Produto> produtoOp = produtoRepository.findByIgnoreCaseNome(produto.getNome());

    if (produtoOp.isPresent() && produto.isNovo()) {
      throw new ProdutoExistentException("Produto já cadastrado");
    }

    produtoRepository.save(produto);
  }

  public List<Produto> buscarTodos() {
    return produtoRepository.findAllByOrderByNomeAsc();
  }

  public Optional<Produto> buscarUm(Long codigo){
    return produtoRepository.findById(codigo);
  }

  public void excluir(Long codigo) {
    try {
    	produtoRepository.deleteById(codigo);
    } catch (DataIntegrityViolationException e){
      throw new ImpossivelExcluirEntidadeException("Não foi possível excluir o produto, está em uso!");
    }
  }

}
