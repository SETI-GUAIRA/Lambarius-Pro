package br.gov.pr.guaira.lambarius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ilha")
public class Ilha {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @Size(max = 200, message = "O nome da ilha deve conter no máximo 200 caracteres")
  @Column(nullable = true)
  @NotBlank(message = "Informe o nome da Ilha")
  private String nome;

  public boolean isNovo() {
    return this.codigo == null;
  }

  @PrePersist
  @PreUpdate
  private void toUpperCase() {
   this.nome = this.nome.toUpperCase();
  }
}
