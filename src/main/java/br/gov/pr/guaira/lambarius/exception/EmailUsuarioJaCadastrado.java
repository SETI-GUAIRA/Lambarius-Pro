package br.gov.pr.guaira.lambarius.exception;

public class EmailUsuarioJaCadastrado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailUsuarioJaCadastrado(String msg) {
		super(msg);
	}
}
