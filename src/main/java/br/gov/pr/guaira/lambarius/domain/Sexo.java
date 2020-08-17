package br.gov.pr.guaira.lambarius.domain;

public enum Sexo {
  FEMININO("Feminino"), MASCULINO("Masculino");

  private String nome;

  Sexo(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
}
