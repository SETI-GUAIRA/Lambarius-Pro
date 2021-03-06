package br.gov.pr.guaira.lambarius.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "pessoa")
@DynamicUpdate
public class Pessoa {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @Column(nullable = false)
  private String nome;

  @Column(name = "data_nascimento")
  private LocalDate dataNascimento;

  @Column(name = "cpf_cnpj", nullable = false)
  private String cpfOuCnpj;

  @Column(name = "rg_ie")
  private String rgOuIe;

  @Column(name = "hora_cadastro")
  private LocalDate horaCadastro;

  @Column(name = "hora_atualizacao")
  private LocalDate horaAtualizacao;

  @Column(name = "sexo", nullable = true)
  @Enumerated(EnumType.STRING)
  private Sexo sexo;

  @NotBlank(message = "Foto é obrigatória")
  @Column(name = "foto_base_64")
  private String fotoBase64;

  @Embedded
  private Contato contato;

  @Embedded
  private Endereco endereco;

  @PrePersist
  @PreUpdate
  private void toUpperCase() {
    this.nome = this.nome.toUpperCase();
  }
}
