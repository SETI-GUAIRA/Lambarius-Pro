package com.br.gov.pr.guaira.lambarius.exception;

public class PortoExistentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PortoExistentException(String mensagem) {
    super(mensagem);
  }
}
