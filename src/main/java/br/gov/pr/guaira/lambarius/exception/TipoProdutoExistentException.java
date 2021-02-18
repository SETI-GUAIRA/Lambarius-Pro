package br.gov.pr.guaira.lambarius.exception;

public class TipoProdutoExistentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TipoProdutoExistentException(String mensagem) {
    super(mensagem);
  }
}
