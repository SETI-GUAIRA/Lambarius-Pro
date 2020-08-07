package br.gov.pr.guaira.lambarius.domain;

public enum LocalPesca {
  LAGO("Lago"), ILHA_GRANDE("Ilha Grande");

  private String local;

  LocalPesca(String local) {
    this.local = local;
  }

  public String getLocal() {
    return local;
  }
}
