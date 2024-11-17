package br.com.desafio.infrastructure.entrypoint.api.controller.impl;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.application.usecase.ListarComprasOrdenadasUseCase;
import br.com.desafio.application.usecase.ObterMaiorCompraPorAnoUseCase;
import br.com.desafio.infrastructure.entrypoint.api.exception.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComprasControllerImpl.class)
class ComprasControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListarComprasOrdenadasUseCase listarComprasOrdenadasUseCase;
    @MockBean
    private ObterMaiorCompraPorAnoUseCase obterMaiorCompraPorAnoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        ComprasControllerImpl comprasController = new ComprasControllerImpl(listarComprasOrdenadasUseCase, obterMaiorCompraPorAnoUseCase);
        this.mockMvc = MockMvcBuilders.standaloneSetup(comprasController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void givenComprasExist_whenGetComprasIsCalled_thenShouldReturnCompras() throws Exception {
        // Given
        CompraResponseDTO compra1 = CompraResponseDTO.builder()
                .clienteNome("Cliente One")
                .clienteCpf("12345678901")
                .produtoCodigo(1L)
                .produtoTipoVinho("Tinto")
                .produtoPreco(100.0)
                .produtoSafra("2020")
                .produtoAnoCompra(2023)
                .quantidade(1)
                .valorTotal(100.0)
                .build();
        CompraResponseDTO compra2 = CompraResponseDTO.builder()
                .clienteNome("Cliente Two")
                .clienteCpf("12345678902")
                .produtoCodigo(2L)
                .produtoTipoVinho("Branco")
                .produtoPreco(200.0)
                .produtoSafra("2021")
                .produtoAnoCompra(2023)
                .quantidade(2)
                .valorTotal(200.0)
                .build();
        List<CompraResponseDTO> expectedCompras = List.of(compra1, compra2);
        when(listarComprasOrdenadasUseCase.listarComprasOrdenadas()).thenReturn(expectedCompras);

        // When & Then
        mockMvc.perform(get("/compras"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].clienteNome", is("Cliente One")))
                .andExpect(jsonPath("$[0].clienteCpf", is("12345678901")))
                .andExpect(jsonPath("$[0].produtoCodigo", is(1)))
                .andExpect(jsonPath("$[0].produtoTipoVinho", is("Tinto")))
                .andExpect(jsonPath("$[0].produtoPreco", is(100.0)))
                .andExpect(jsonPath("$[0].produtoSafra", is("2020")))
                .andExpect(jsonPath("$[0].produtoAnoCompra", is(2023)))
                .andExpect(jsonPath("$[0].quantidade", is(1)))
                .andExpect(jsonPath("$[0].valorTotal", is(100.0)));
    }

    @Test
    void givenMaiorCompraExists_whenGetMaiorCompraPorAnoIsCalled_thenShouldReturnMaiorCompra() throws Exception {
        // Given
        CompraResponseDTO maiorCompra = CompraResponseDTO.builder()
                .clienteNome("Cliente Maior")
                .clienteCpf("12345678903")
                .produtoCodigo(1L)
                .produtoTipoVinho("Reserva")
                .produtoPreco(500.0)
                .produtoSafra("2019")
                .produtoAnoCompra(2023)
                .quantidade(5)
                .valorTotal(500.0)
                .build();
        when(obterMaiorCompraPorAnoUseCase.obterMaiorCompraPorAno(2023)).thenReturn(maiorCompra);

        // When & Then
        mockMvc.perform(get("/maior-compra/2023"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.clienteNome", is("Cliente Maior")))
                .andExpect(jsonPath("$.clienteCpf", is("12345678903")))
                .andExpect(jsonPath("$.produtoCodigo", is(1)))
                .andExpect(jsonPath("$.produtoTipoVinho", is("Reserva")))
                .andExpect(jsonPath("$.produtoPreco", is(500.0)))
                .andExpect(jsonPath("$.produtoSafra", is("2019")))
                .andExpect(jsonPath("$.produtoAnoCompra", is(2023)))
                .andExpect(jsonPath("$.quantidade", is(5)))
                .andExpect(jsonPath("$.valorTotal", is(500.0)));
    }

}