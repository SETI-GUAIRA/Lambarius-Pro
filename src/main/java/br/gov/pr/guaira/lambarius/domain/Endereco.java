package br.gov.pr.guaira.lambarius.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

  private String logradouro;
  private Integer numero;
  private String bairro;
  private String complemento;
}
