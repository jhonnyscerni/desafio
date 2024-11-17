package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.application.mapper.CompraModelMapper;
import br.com.desafio.domain.gateway.CompraGateway;
import br.com.desafio.domain.model.Cliente;
import br.com.desafio.domain.model.Compra;
import br.com.desafio.domain.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterMaiorCompraPorAnoUseCaseImplTest {

    @Mock
    private CompraGateway compraGateway;

    @InjectMocks
    private ObterMaiorCompraPorAnoUseCaseImpl obterMaiorCompraPorAnoUseCase;

    private Compra compra;
    private CompraResponseDTO compraResponseDTO;

    @BeforeEach
    void setUp() {

        compra = Compra.builder()
                .id(1L)
                .produto(Produto.builder()
                        .codigo(1L)
                        .tipoVinho("Tinto")
                        .preco(50.0)
                        .safra("2020")
                        .anoCompra(2023)
                        .build())
                .cliente(Cliente.builder()
                        .nome("Cliente 1")
                        .cpf("12345678901")
                        .build())
                .quantidade(100)
                .build();
        compraResponseDTO = CompraModelMapper.toDomain(compra);
    }

    @Test
    void givenComprasExist_whenObterMaiorCompraPorAnoIsCalled_thenShouldReturnCompra() {
        when(compraGateway.obterMaiorCompraPorAno(2023)).thenReturn(List.of(compra));

        CompraResponseDTO result = obterMaiorCompraPorAnoUseCase.obterMaiorCompraPorAno(2023);

        assertEquals(compraResponseDTO.getProdutoCodigo(), result.getProdutoCodigo());
        assertEquals(compraResponseDTO.getProdutoTipoVinho(), result.getProdutoTipoVinho());
        assertEquals(compraResponseDTO.getProdutoPreco(), result.getProdutoPreco());
        assertEquals(compraResponseDTO.getProdutoSafra(), result.getProdutoSafra());
        assertEquals(compraResponseDTO.getProdutoAnoCompra(), result.getProdutoAnoCompra());
        assertEquals(compraResponseDTO.getClienteNome(), result.getClienteNome());
        assertEquals(compraResponseDTO.getClienteCpf(), result.getClienteCpf());
        assertEquals(compraResponseDTO.getQuantidade(), result.getQuantidade());

    }

    @Test
    void givenNoComprasExist_whenObterMaiorCompraPorAnoIsCalled_thenShouldReturnNull() {
        when(compraGateway.obterMaiorCompraPorAno(2023)).thenReturn(Collections.emptyList());

        CompraResponseDTO result = obterMaiorCompraPorAnoUseCase.obterMaiorCompraPorAno(2023);

        assertNull(result);
    }
}