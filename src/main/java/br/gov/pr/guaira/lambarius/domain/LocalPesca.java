package br.gov.pr.guaira.lambarius.domain;

public enum LocalPesca {
  LAGO("Lago"), ILHA_GRANDE("Ilha Grande");

  private String nome;

  LocalPesca(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
}
