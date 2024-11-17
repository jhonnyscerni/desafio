package br.com.desafio.infrastructure.entrypoint.api.controller.impl;

import br.com.desafio.application.dto.ClienteFielResponseDTO;
import br.com.desafio.application.exception.ClienteNotFoundException;
import br.com.desafio.application.usecase.ObterClientesFieisUseCase;
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
@WebMvcTest(ClienteControllerImpl.class)
class ClienteControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObterClientesFieisUseCase obterClientesFieisUseCase;


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        ClienteControllerImpl clienteController = new ClienteControllerImpl(obterClientesFieisUseCase);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void givenClientesExist_whenGetClienteIsCalled_thenShouldReturnClientes() throws Exception {
        // Given
        ClienteFielResponseDTO cliente1 = new ClienteFielResponseDTO("Cliente One", "1234567890");
        ClienteFielResponseDTO cliente2 = new ClienteFielResponseDTO("Cliente Two", "1234567891");
        List<ClienteFielResponseDTO> expectedClientes = List.of(cliente1, cliente2);
        when(obterClientesFieisUseCase.execute()).thenReturn(expectedClientes);

        // When & Then
        mockMvc.perform(get("/clientes-fieis"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome", is("Cliente One")))
                .andExpect(jsonPath("$[0].cpf", is("1234567890")));
    }

    @Test
    void givenNoClientesExist_whenGetClienteIsCalled_thenShouldReturnNotFound() throws Exception {
        // Given
        when(obterClientesFieisUseCase.execute()).thenThrow(new ClienteNotFoundException("Cliente não encontrado com o CPF: 12345678901"));

        // When & Then
        mockMvc.perform(get("/clientes-fieis"))
                .andExpect(status().isNotFound());
    }
}