package br.com.raaydesenvolvimento.creditosapi.mapper;

import br.com.raaydesenvolvimento.creditosapi.dto.CreditoDTO;
import br.com.raaydesenvolvimento.creditosapi.model.Credito;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  CreditoMapper {
    CreditoDTO toDto(Credito entity);
    List<CreditoDTO> toDtoList(List<Credito> entityList);
}
