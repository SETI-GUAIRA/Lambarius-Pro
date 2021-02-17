package br.gov.pr.guaira.lambarius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@PrimaryKeyJoinColumn(name = "codigo")
@Entity
@Table(name = "produtor")
@DynamicUpdate
public class Produtor extends Pessoa {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @Column(nullable = false)
  private Boolean feirante;  

  public boolean isNovo() {
    return this.codigo == null;
  } 
  @PrePersist
  private void toUpperCase() {
    this.getNome().toUpperCase();
    } 

}
