package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.ClienteFielResponseDTO;
import br.com.desafio.application.mapper.ClienteModelMapper;
import br.com.desafio.application.usecase.ObterClientesFieisUseCase;
import br.com.desafio.domain.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ObterClientesFieisUseCaseImpl implements ObterClientesFieisUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public List<ClienteFielResponseDTO> execute() {
        return clienteGateway.obterTop3ClientesFieis().stream()
                .limit(3)
                .map(ClienteModelMapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }
}
