package com.br.gov.pr.guaira.lambarius.domain;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

}
