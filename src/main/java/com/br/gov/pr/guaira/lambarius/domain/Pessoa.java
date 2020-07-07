package com.br.gov.pr.guaira.lambarius.domain;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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

  @DateTimeFormat(iso = ISO.DATE)
	@Column(columnDefinition = "DATE", nullable = false)
	private LocalDate dataNascimento;

  @Column(nullable = false)
  private String cpfCnpj;

  @Column(nullable = false)
  private String rgOuIe;

  @Column(nullable = false)
  private String foto;

  @Embedded
  private Contato contato;

  @Embedded
  private Endereco endereco;
}
