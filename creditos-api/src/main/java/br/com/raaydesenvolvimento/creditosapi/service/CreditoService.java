package br.com.raaydesenvolvimento.creditosapi.service;

import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import br.com.raaydesenvolvimento.creditosapi.repository.CreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    public List<Credito> getCreditosByNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse);
    }

    public List<Credito> getCreditoByNumero(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito);
    }
}