package br.com.raaydesenvolvimento.creditosapi.controller;

import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import br.com.raaydesenvolvimento.creditosapi.service.CreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.consulta}")
    private String topic;

    @Operation(summary = "Retorna uma lista de créditos constituídos com base no número da NFS-e")
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<Credito>> getCreditosByNfse(
            @Parameter(description = "Número identificador da NFS-e", required = true)
            @PathVariable String numeroNfse) {
        List<Credito> creditos = creditoService.getCreditosByNfse(numeroNfse);
        kafkaTemplate.send(topic, "Consulta realizada para NFS-e: " + numeroNfse);
        return ResponseEntity.ok(creditos);
    }

    @Operation(summary = "Retorna os detalhes de um crédito constituído específico com base no número do crédito constituído")
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<Credito> getCreditoByNumero(
            @Parameter(description = "Número identificador do crédito constituído", required = true)
            @PathVariable String numeroCredito) {
        Optional<Credito> credito = creditoService.getCreditoByNumero(numeroCredito);
        kafkaTemplate.send(topic, "Consulta realizada para Crédito: " + numeroCredito);
        return credito.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
