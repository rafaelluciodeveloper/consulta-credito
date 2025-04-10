package br.com.raaydesenvolvimento.creditosapi.service;

import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import br.com.raaydesenvolvimento.creditosapi.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    @InjectMocks
    private CreditoService creditoService;

    private Credito credito;

    @BeforeEach
    public void setUp() {
        credito = new Credito();
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");
        credito.setDataConstituicao(LocalDate.of(2024, 2, 25));
        credito.setValorIssqn(new BigDecimal("1500.75"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.0"));
        credito.setValorFaturado(new BigDecimal("30000.00"));
        credito.setValorDeducao(new BigDecimal("5000.00"));
        credito.setBaseCalculo(new BigDecimal("25000.00"));
    }

    @Test
    public void testGetCreditosByNfse() {
        when(creditoRepository.findByNumeroNfse("7891011"))
                .thenReturn(Collections.singletonList(credito));

        List<Credito> resultado = creditoService.getCreditosByNfse("7891011");
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("7891011", resultado.get(0).getNumeroNfse());
    }

    @Test
    public void testGetCreditoByNumero() {
        when(creditoRepository.findByNumeroCredito("123456"))
                .thenReturn(Collections.singletonList(credito));

        List<Credito> resultado = creditoService.getCreditoByNumero("123456");
        assertFalse(resultado.isEmpty());
        assertEquals("123456", resultado.get(0).getNumeroCredito());
    }
}
