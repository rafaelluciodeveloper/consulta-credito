package br.com.raaydesenvolvimento.creditosapi.controller;

import br.com.raaydesenvolvimento.creditosapi.dto.CreditoDTO;
import br.com.raaydesenvolvimento.creditosapi.mapper.CreditoMapper;
import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import br.com.raaydesenvolvimento.creditosapi.service.CreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    private final CreditoService creditoService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final CreditoMapper creditoMapper;

    @Value("${kafka.topic.consulta}")
    private String topic;

    public CreditoController(CreditoService creditoService, KafkaTemplate<String, String> kafkaTemplate, CreditoMapper creditoMapper) {
        this.creditoService = creditoService;
        this.kafkaTemplate = kafkaTemplate;
        this.creditoMapper = creditoMapper;
    }

    @Operation(summary = "Retorna uma lista de créditos constituídos com base no número do crédito")
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<List<CreditoDTO>> getCreditoByNumero(
            @Parameter(description = "Número identificador do crédito constituído", required = true)
            @PathVariable String numeroCredito) {
        List<Credito> creditos = creditoService.getCreditoByNumero(numeroCredito);
        kafkaTemplate.send(topic, "Consulta realizada para Crédito: " + numeroCredito);
        List<CreditoDTO> dtos = creditoMapper.toDtoList(creditos);
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Retorna uma lista de créditos constituídos com base no número da NFS-e")
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> getCreditosByNfse(
            @Parameter(description = "Número identificador da NFS-e", required = true)
            @PathVariable String numeroNfse) {
        List<Credito> creditos = creditoService.getCreditosByNfse(numeroNfse);
        kafkaTemplate.send(topic, "Consulta realizada para NFS-e: " + numeroNfse);
        List<CreditoDTO> dtos = creditoMapper.toDtoList(creditos);
        return ResponseEntity.ok(dtos);
    }

}
