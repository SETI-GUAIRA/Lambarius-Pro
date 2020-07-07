package com.br.gov.pr.guaira.lambarius.domain;

import javax.persistence.*;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "porto")
public class Porto {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @Column(nullable = false)
  private String nome;

}
