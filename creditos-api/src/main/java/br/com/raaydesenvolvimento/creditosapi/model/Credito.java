package br.com.raaydesenvolvimento.creditosapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCredito;

    private String numeroNfse;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataConstituicao;

    private BigDecimal valorIssqn;

    private String tipoCredito;

    private Boolean simplesNacional;

    private BigDecimal aliquota;

    private BigDecimal valorFaturado;

    private BigDecimal valorDeducao;

    private BigDecimal baseCalculo;

}
