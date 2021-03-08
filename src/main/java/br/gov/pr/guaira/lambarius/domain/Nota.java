package br.gov.pr.guaira.lambarius.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "nota")
public class Nota {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
 
  @NotNull
  @ManyToOne()
  @JoinColumn(name = "pescador", referencedColumnName = "codigo", nullable = true)
  private Pescador pescador;
  
  @NotNull
  @ManyToOne()
  @JoinColumn(name = "produtor", referencedColumnName = "codigo", nullable = true)
  private Produtor produtor;
  
  @NotNull
  @Column(name = "tipoNota", nullable = true)
  @Enumerated(EnumType.STRING)
  private TipoNota tipoNota;
  
  @NotBlank
  @Size(max = 20)
  @Column(unique = true)
  private String naturzaoperacao;
  
  @NotBlank
  @Size(max = 255, min = 3)
  @Column(nullable = false, unique = true)
  private String nrNota;
  
  @NotNull
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
  private BigDecimal valorTotal;
  
  @NotBlank
  @Size(max = 20, min = 3)
  @Column(nullable = false, unique = true)
  private String imposto;
  
  @NotNull
  @PastOrPresent(message = "{PastOrPresent.nota.dataEmissao}")
  @DateTimeFormat(iso = ISO.DATE, pattern = "")
  @Column(name= "data_emissao", nullable = false, columnDefinition = "DATE")
  private LocalDate dataEmissao;
	
  @DateTimeFormat(iso = ISO.DATE)
  @Column(name = "data_saida", columnDefinition = "DATE")
  private LocalDate dataSaida;
  
  @DateTimeFormat(iso = ISO.TIME)
  @Column(name = "hora_saida", columnDefinition = "TIME")
  private LocalDate horaSaida;  
    
  @NotNull
  @ManyToOne()
  @JoinColumn(name = "produto", referencedColumnName = "codigo", nullable = true)
  private Produto produto;  
  
  
  @NotNull
  @Size(max = 255, min = 3)
  @Column(nullable = false, unique = true)
  private String quantidade;
  
  @NotNull
  @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
  @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
  private BigDecimal valorUnitario;
  
  

  public boolean isNovo() {
    return this.codigo == null;
  }

  
}
