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
  @Column(nullable = true)
  private String observacao;

  @Column(nullable = false)
  private Boolean aposentado;

  @Column(name = "local_pesca", nullable = true)
  @Enumerated(EnumType.STRING)
  private LocalPesca localPesca;

  @ManyToOne()
  @JoinColumn(name = "associacao", referencedColumnName = "codigo", nullable = true)
  private Associacao associacao;

  @ManyToOne()
  @JoinColumn(name = "porto", referencedColumnName = "codigo", nullable = true)
  private Porto porto;

  @ManyToOne()
  @JoinColumn(name = "ilha", referencedColumnName = "codigo", nullable = true)
  private Ilha ilha;

  public boolean isNovo() {
    return this.codigo == null;
  }

  @PrePersist
  private void toUpperCase() {
    this.getNome().toUpperCase();
  }

}
