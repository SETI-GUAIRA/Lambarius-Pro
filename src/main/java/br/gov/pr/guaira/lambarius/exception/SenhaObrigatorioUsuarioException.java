package br.gov.pr.guaira.lambarius.exception;

public class SenhaObrigatorioUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaObrigatorioUsuarioException(String msg) {
		super(msg);
	}
}
