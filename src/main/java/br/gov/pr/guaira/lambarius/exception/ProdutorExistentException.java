package br.gov.pr.guaira.lambarius.exception;

public class ProdutorExistentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ProdutorExistentException(String mensagem) {
    super(mensagem);
  }
}
