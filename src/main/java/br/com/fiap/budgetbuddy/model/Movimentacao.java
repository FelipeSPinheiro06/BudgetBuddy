package br.com.fiap.budgetbuddy.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.budgetbuddy.validation.TipoMovimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Movimentacao {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{movimentacao.descricao.notblank}") // code smell i18n -> internacionalization 
    @Size(min = 3, max = 255, message = "{movimentacao.descricao.size}")
    private String descricao;

    @Positive(message = "{movimentacao.valor.positive}")
    private BigDecimal valor;

    @Past
    private LocalDate data;

    @TipoMovimentacao(message = "{movimentacao.tipo}")
    private String tipo;

    

}
