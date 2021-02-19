package br.gov.pr.guaira.lambarius.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "produto")
public class Produto {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @NotBlank(message = "O nome do Produto é obrigatório")
  @Size(max = 200, message = "O nome do Produto deve conter no máximo 200 caracteres")
  @Column(nullable = false)
  private String nome;
  
  @ManyToOne()
  @JoinColumn(name = "tipoproduto", referencedColumnName = "codigo", nullable = true)
  private TipoProduto tipoproduto;
  
  @Column(name = "unidademedida", nullable = true)
  @Enumerated(EnumType.STRING)
  private UnidadesMedidas unidadesMedidas;

  public boolean isNovo() {
    return this.codigo == null;
  }
  

  @PrePersist
  @PreUpdate
  private void toUpperCase() {
   this.nome = this.nome.toUpperCase();
  }
}
