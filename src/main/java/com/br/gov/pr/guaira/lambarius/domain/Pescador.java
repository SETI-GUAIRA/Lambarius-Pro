package com.br.gov.pr.guaira.lambarius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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

  @Column(unique = true)
  private String rgp;

  @ManyToOne()
  @JoinColumn(name = "associacao", referencedColumnName = "codigo")
  private Associacao associacao;

  @ManyToOne()
  @JoinColumn(name = "porto", referencedColumnName = "codigo")
  private Porto porto;

}
