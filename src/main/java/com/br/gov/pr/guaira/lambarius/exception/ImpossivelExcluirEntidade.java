package com.br.gov.pr.guaira.lambarius.exception;

public class ImpossivelExcluirEntidade extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ImpossivelExcluirEntidade(String mensagem) {
    super(mensagem);
  }
}
