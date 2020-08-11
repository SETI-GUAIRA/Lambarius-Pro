package br.gov.pr.guaira.lambarius.domain;

public enum LocalPesca {
  LAGO("Lago de Itaipu"), ILHA_GRANDE("Parque Nacional de Ilha Grande");

  private String nome;

  LocalPesca(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
}
