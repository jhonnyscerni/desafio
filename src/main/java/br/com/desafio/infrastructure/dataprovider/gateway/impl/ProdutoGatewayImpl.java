package br.com.desafio.infrastructure.dataprovider.gateway.impl;

import br.com.desafio.infrastructure.dataprovider.client.ClienteClient;
import br.com.desafio.infrastructure.dataprovider.client.dto.clientes.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClienteGatewayImpl {

    private final ClienteClient clienteClient;

    public List<ClienteDTO> getClientes() {
        return clienteClient.getClientes();
    }
}
