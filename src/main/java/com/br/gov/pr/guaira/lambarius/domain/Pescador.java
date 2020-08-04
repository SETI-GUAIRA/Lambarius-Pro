package com.br.gov.pr.guaira.lambarius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@PrimaryKeyJoinColumn(name = "codigo")
@Entity
@Table(name = "pescador")
@DynamicUpdate
public class Pescador extends Pessoa {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @NotBlank(message = "O campo RGP é obrigatório.")
  @Size(max = 20, message = "O campo RGP deve conter no máximo 20 caracteres.")
  @Column(unique = true)
  private String rgp;

  @NotNull(message = "Selecione uma associação para o pescador")
  @ManyToOne()
  @JoinColumn(name = "associacao", referencedColumnName = "codigo")
  private Associacao associacao;

  @NotNull(message = "Selecione um porto para o pescador")
  @ManyToOne()
  @JoinColumn(name = "porto", referencedColumnName = "codigo")
  private Porto porto;
  
  @PrePersist
  private void toUpperCase() {
	  this.getNome().toUpperCase();
  }

}
