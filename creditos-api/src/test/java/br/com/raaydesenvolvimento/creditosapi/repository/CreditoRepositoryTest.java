package br.com.raaydesenvolvimento.creditosapi.repository;

import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CreditoRepositoryTest {

    @Autowired
    private CreditoRepository creditoRepository;

    @Test
    public void testFindByNumeroNfse() {
        Credito credito = new Credito();
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
        creditoRepository.save(credito);

        List<Credito> lista = creditoRepository.findByNumeroNfse("7891011");
        assertFalse(lista.isEmpty());
        assertEquals("123456", lista.get(0).getNumeroCredito());
    }

    @Test
    public void testFindByNumeroCredito() {
        Credito credito = new Credito();
        credito.setNumeroCredito("654321");
        credito.setNumeroNfse("1122334");
        credito.setDataConstituicao(LocalDate.of(2024, 1, 15));
        credito.setValorIssqn(new BigDecimal("800.50"));
        credito.setTipoCredito("Outros");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("3.5"));
        credito.setValorFaturado(new BigDecimal("20000.00"));
        credito.setValorDeducao(new BigDecimal("3000.00"));
        credito.setBaseCalculo(new BigDecimal("17000.00"));
        creditoRepository.save(credito);

        Optional<Credito> resultado = creditoRepository.findByNumeroCredito("654321");
        assertTrue(resultado.isPresent());
        assertEquals("1122334", resultado.get().getNumeroNfse());
    }
}