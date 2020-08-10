package br.gov.pr.guaira.lambarius.domain;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

  @Size(max = 255, message = "O campo Observação deve conter no máximo 255 caracteres.")
  private String observacao;

  @ManyToOne()
  @JoinColumn(name = "associacao", referencedColumnName = "codigo")
  private Associacao associacao;

  @ManyToOne()
  @JoinColumn(name = "porto", referencedColumnName = "codigo")
  private Porto porto;

  @ManyToOne()
  @JoinColumn(name = "ilha", referencedColumnName = "codigo")
  private Ilha ilha;

  public boolean isNovo() {
    return this.codigo == null;
  }

  @PrePersist
  private void toUpperCase() {
    this.getNome().toUpperCase();
  }

}
