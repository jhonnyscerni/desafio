package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;
import br.com.desafio.application.exception.ClienteNotFoundException;
import br.com.desafio.application.mapper.RecomendacaoModelMapper;
import br.com.desafio.domain.gateway.CompraGateway;
import br.com.desafio.domain.model.Recomendacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterRecomendacaoVinhoUseCaseImplTest {

    @Mock
    private CompraGateway compraGateway;

    @InjectMocks
    private ObterRecomendacaoVinhoUseCaseImpl obterRecomendacaoVinhoUseCase;

    @Test
    void givenRecomendacaoExists_whenExecuteIsCalled_thenShouldReturnRecomendacao() {
        Recomendacao recomendacao = Recomendacao.builder()
                .tipoVinho("Tinto")
                .safra("2020")
                .preco(100.0)
                .quantidadeTotal(10L)
                .build();
        RecomendacaoResponseDTO recomendacaoResponseDTO = RecomendacaoModelMapper.toDomain(recomendacao);
        when(compraGateway.obterRecomendacaoVinho("12345678901")).thenReturn(List.of(recomendacao));

        RecomendacaoResponseDTO result = obterRecomendacaoVinhoUseCase.execute("12345678901");

        assertNotNull(result);
        assertEquals(recomendacaoResponseDTO.getTipoVinho(), result.getTipoVinho());
        assertEquals(recomendacaoResponseDTO.getSafra(), result.getSafra());
        assertEquals(recomendacaoResponseDTO.getPreco(), result.getPreco());
        assertEquals(recomendacaoResponseDTO.getQuantidadeTotal(), result.getQuantidadeTotal());
    }

    @Test
    void givenNoRecomendacaoExists_whenExecuteIsCalled_thenShouldThrowClienteNotFoundException() {
        when(compraGateway.obterRecomendacaoVinho("12345678901")).thenReturn(Collections.emptyList());

        assertThrows(ClienteNotFoundException.class, () -> {
            obterRecomendacaoVinhoUseCase.execute("12345678901");
        });
    }
}