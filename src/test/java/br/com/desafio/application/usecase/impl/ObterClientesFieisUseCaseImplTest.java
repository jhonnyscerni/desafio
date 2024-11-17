package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.ClienteFielResponseDTO;
import br.com.desafio.application.mapper.ClienteModelMapper;
import br.com.desafio.domain.gateway.ClienteGateway;
import br.com.desafio.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterClientesFieisUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ObterClientesFieisUseCaseImpl obterClientesFieisUseCase;

    private List<Cliente> clienteList;
    private List<ClienteFielResponseDTO> clienteFielResponseDTOList;

    @BeforeEach
    void setUp() {
        clienteList = Arrays.asList(
                new Cliente("Cliente 1", "12345678901", null),
                new Cliente("Cliente 2", "12345678902", null),
                new Cliente("Cliente 3", "12345678903", null)
        );

        clienteFielResponseDTOList = Arrays.asList(
                new ClienteFielResponseDTO("Cliente 1", "12345678901"),
                new ClienteFielResponseDTO("Cliente 2", "12345678902"),
                new ClienteFielResponseDTO("Cliente 3", "12345678903")
        );
    }

    @Test
    void givenClientesExist_whenExecuteIsCalled_thenShouldReturnTop3ClientesFieis() {
        when(clienteGateway.obterTop3ClientesFieis()).thenReturn(clienteList);

        List<ClienteFielResponseDTO> result = obterClientesFieisUseCase.execute();

        assertEquals(3, result.size());
        assertEquals("Cliente 1", result.get(0).getNome());
        assertEquals("Cliente 2", result.get(1).getNome());
        assertEquals("Cliente 3", result.get(2).getNome());
    }
}