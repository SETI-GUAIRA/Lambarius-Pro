package com.br.gov.pr.guaira.lambarius.exception;

public class PescadorExistentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PescadorExistentException(String mensagem) {
    super(mensagem);
  }
}
