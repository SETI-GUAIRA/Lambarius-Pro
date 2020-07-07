package com.br.gov.pr.guaira.lambarius.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Contato {
	
	private String email;
	private String telefone;
	private String celular;
}
