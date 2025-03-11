package br.com.raaydesenvolvimento.creditosapi.controller;

import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import br.com.raaydesenvolvimento.creditosapi.service.CreditoService;
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

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<Credito>> getCreditosByNfse(@PathVariable String numeroNfse) {
        List<Credito> creditos = creditoService.getCreditosByNfse(numeroNfse);
        kafkaTemplate.send(topic, "Consulta realizada para NFS-e: " + numeroNfse);
        return ResponseEntity.ok(creditos);
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<Credito> getCreditoByNumero(@PathVariable String numeroCredito) {
        Optional<Credito> credito = creditoService.getCreditoByNumero(numeroCredito);
        kafkaTemplate.send(topic, "Consulta realizada para Crédito: " + numeroCredito);
        return credito.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
