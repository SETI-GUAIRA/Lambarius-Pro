package br.gov.pr.guaira.lambarius.exception;

public class IlhaExistentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IlhaExistentException(String mensagem) {
    super(mensagem);
  }
}
