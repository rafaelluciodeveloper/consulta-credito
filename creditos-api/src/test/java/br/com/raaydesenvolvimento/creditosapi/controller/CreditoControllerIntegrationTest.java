package br.com.raaydesenvolvimento.creditosapi.controller;

import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import br.com.raaydesenvolvimento.creditosapi.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CreditoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreditoRepository creditoRepository;

    @MockitoBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    public void setUp() {
        creditoRepository.deleteAll();

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
    }

    @Test
    public void testGetCreditosByNfseIntegration() throws Exception {
        mockMvc.perform(get("/api/creditos/7891011")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCredito", is("123456")))
                .andExpect(jsonPath("$[0].numeroNfse", is("7891011")));
    }

    @Test
    public void testGetCreditoByNumeroIntegration() throws Exception {
        mockMvc.perform(get("/api/creditos/credito/123456")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito", is("123456")))
                .andExpect(jsonPath("$.numeroNfse", is("7891011")));
    }
}
