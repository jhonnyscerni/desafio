package br.com.desafio.application.mapper;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class RecomendacaoMapper {

    public RecomendacaoResponseDTO toDomain(ProdutoEntity entity) {
        return RecomendacaoResponseDTO.builder()
                .tipoVinho(entity.getTipoVinho())
                .build();
    }
}
