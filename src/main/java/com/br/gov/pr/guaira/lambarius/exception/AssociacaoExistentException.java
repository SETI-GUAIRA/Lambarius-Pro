package com.br.gov.pr.guaira.lambarius.exception;

public class AssociacaoExistentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public AssociacaoExistentException(String mensagem) {
    super(mensagem);
  }
}
