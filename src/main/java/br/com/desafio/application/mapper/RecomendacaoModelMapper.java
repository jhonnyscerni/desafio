package br.com.desafio.application.mapper;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;
import br.com.desafio.domain.model.Recomendacao;

public class RecomendacaoModelMapper {

    public static RecomendacaoResponseDTO toDomain(Recomendacao recomendacao) {
        return RecomendacaoResponseDTO.builder()
                .preco(recomendacao.getPreco())
                .quantidadeTotal(recomendacao.getQuantidadeTotal())
                .safra(recomendacao.getSafra())
                .tipoVinho(recomendacao.getTipoVinho())
                .build();
    }
}
