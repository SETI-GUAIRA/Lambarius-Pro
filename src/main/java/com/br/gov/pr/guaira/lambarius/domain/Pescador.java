package com.br.gov.pr.guaira.lambarius.domain;

import javax.persistence.*;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pescador")
public class Pescador extends Pessoa {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @Column(unique = true)
  private String rgp;

}
