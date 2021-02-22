package br.gov.pr.guaira.lambarius.exception;

public class ProdutoExistentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ProdutoExistentException(String mensagem) {
    super(mensagem);
  }
}
