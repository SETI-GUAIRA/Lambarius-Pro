package br.gov.pr.guaira.lambarius.domain;

public enum TipoNota {
  TIPO01("Tipo 01"), TIPO02("Tipo 02");

  private String nome;

  TipoNota(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
}
