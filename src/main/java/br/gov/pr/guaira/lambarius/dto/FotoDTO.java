package br.gov.pr.guaira.lambarius.dto;

public class FotoDTO {
  private String nome;
  private boolean fotoNova;

  public FotoDTO(String nome, String contentType, boolean fotoNova) {
    this.nome = nome;
    this.fotoNova = fotoNova;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }


  public boolean isFotoNova() {
    return fotoNova;
  }

  public void setFotoNova(boolean fotoNova) {
    this.fotoNova = fotoNova;
  }
}
