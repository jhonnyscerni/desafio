package br.com.desafio.infrastructure.entrypoint.api.controller.impl;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;
import br.com.desafio.application.exception.ClienteNotFoundException;
import br.com.desafio.application.usecase.ObterRecomendacaoVinhoUseCase;
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
@WebMvcTest(RecomendacaoControllerImpl.class)
class RecomendacaoControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObterRecomendacaoVinhoUseCase obterRecomendacaoVinhoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        RecomendacaoControllerImpl recomendacaoController = new RecomendacaoControllerImpl(obterRecomendacaoVinhoUseCase);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recomendacaoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void givenRecomendacoesExist_whenGetRecomendacaoVinhoIsCalled_thenShouldReturnRecomendacoes() throws Exception {
        // Given
        RecomendacaoResponseDTO recomendacao1 = new RecomendacaoResponseDTO("Tinto", "2020", 100.0, 10L);
        RecomendacaoResponseDTO recomendacao2 = new RecomendacaoResponseDTO("Branco", "2019", 80.0, 5L);
        List<RecomendacaoResponseDTO> expectedRecomendacoes = List.of(recomendacao1, recomendacao2);
        when(obterRecomendacaoVinhoUseCase.execute("12345678901")).thenReturn(recomendacao1);

        // When & Then
        mockMvc.perform(get("/recomendacao/cliente/12345678901/tipo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tipoVinho", is("Tinto")))
                .andExpect(jsonPath("$.safra", is("2020")))
                .andExpect(jsonPath("$.preco", is(100.0)))
                .andExpect(jsonPath("$.quantidadeTotal", is(10)));
    }

    @Test
    void givenNoRecomendacoesExist_whenGetRecomendacaoVinhoIsCalled_thenShouldReturnNotFound() throws Exception {
        // Given
        when(obterRecomendacaoVinhoUseCase.execute("12345678901")).thenThrow(new ClienteNotFoundException("Cliente não encontrado com o CPF: 12345678901"));

        // When & Then
        mockMvc.perform(get("/recomendacao/cliente/12345678901/tipo"))
                .andExpect(status().isNotFound());
    }
}