package br.gov.pr.guaira.lambarius.domain;

public enum UnidadesMedidas {
  QUILO("Kg"), GRAMAS("g"), UNIDADE("UNID"), LITRO("L"), ML("ml");

  private String nome;

  UnidadesMedidas(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
}
