package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.application.mapper.CompraModelMapper;
import br.com.desafio.domain.gateway.CompraGateway;
import br.com.desafio.domain.model.Cliente;
import br.com.desafio.domain.model.Compra;
import br.com.desafio.domain.model.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarComprasOrdenadasUseCaseImplTest {

    @Mock
    private CompraGateway compraGateway;

    @InjectMocks
    private ListarComprasOrdenadasUseCaseImpl listarComprasOrdenadasUseCase;

    @Test
    void givenComprasExist_whenListarComprasOrdenadasIsCalled_thenShouldReturnOrderedList() {
        Cliente clienteA = Cliente.builder().nome("Cliente A").cpf("12345678900").compras(null).build();

        Produto produto = new Produto(1L, "Produto A", 100.0, "2022", 2023);
        Compra compra1 = Compra.builder()
                .id(1L)
                .produto(produto)
                .cliente(clienteA)
                .quantidade(1)
                .build();

        List<Compra> compras = Arrays.asList(compra1);

        when(compraGateway.listarComprasOrdenadas()).thenReturn(compras);

        List<CompraResponseDTO> result = listarComprasOrdenadasUseCase.listarComprasOrdenadas();

        assertEquals(1, result.size());
        assertEquals(CompraModelMapper.toDomain(compra1).getProdutoCodigo(), result.get(0).getProdutoCodigo());
        assertEquals(CompraModelMapper.toDomain(compra1).getProdutoTipoVinho(), result.get(0).getProdutoTipoVinho());
        assertEquals(CompraModelMapper.toDomain(compra1).getProdutoPreco(), result.get(0).getProdutoPreco());
        assertEquals(CompraModelMapper.toDomain(compra1).getProdutoSafra(), result.get(0).getProdutoSafra());
        assertEquals(CompraModelMapper.toDomain(compra1).getProdutoAnoCompra(), result.get(0).getProdutoAnoCompra());
        assertEquals(CompraModelMapper.toDomain(compra1).getClienteNome(), result.get(0).getClienteNome());
        assertEquals(CompraModelMapper.toDomain(compra1).getClienteCpf(), result.get(0).getClienteCpf());
        assertEquals(CompraModelMapper.toDomain(compra1).getQuantidade(), result.get(0).getQuantidade());
    }

    @Test
    void givenDatabaseError_whenListarComprasOrdenadasIsCalled_thenShouldThrowException() {
        when(compraGateway.listarComprasOrdenadas()).thenThrow(new RuntimeException("Erro ao acessar o banco de dados"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            listarComprasOrdenadasUseCase.listarComprasOrdenadas();
        });

        assertEquals("Erro ao acessar o banco de dados", exception.getMessage());
    }
}