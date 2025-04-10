package br.com.raaydesenvolvimento.creditosapi.repository;

import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {
    List<Credito> findByNumeroNfse(String numeroNfse);

    List<Credito> findByNumeroCredito(String numeroCredito);
}
