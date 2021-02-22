package br.gov.pr.guaira.lambarius.exception;

public class NotaExistentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NotaExistentException(String mensagem) {
    super(mensagem);
  }
}
