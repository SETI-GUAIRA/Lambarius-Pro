package br.gov.pr.guaira.lambarius.exception;

public class ImpossivelExcluirEntidadeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ImpossivelExcluirEntidadeException(String mensagem) {
    super(mensagem);
  }
}
