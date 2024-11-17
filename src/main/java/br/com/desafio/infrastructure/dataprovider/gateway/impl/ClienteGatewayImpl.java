package br.com.desafio.infrastructure.dataprovider.gateway.impl;

import br.com.desafio.domain.gateway.ClienteGateway;
import br.com.desafio.domain.model.Cliente;
import br.com.desafio.infrastructure.dataprovider.mapper.ClienteMapper;
import br.com.desafio.infrastructure.dataprovider.persistence.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public List<Cliente> obterTop3ClientesFieis() {
        return clienteRepository.findTop3ClientesFieis().stream()
                .map(clienteMapper::toDomain)
                .collect(Collectors.toList());
    }
}
