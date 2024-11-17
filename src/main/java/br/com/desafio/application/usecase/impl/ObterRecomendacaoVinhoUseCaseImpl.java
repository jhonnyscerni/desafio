package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;
import br.com.desafio.application.exception.ClienteNotFoundException;
import br.com.desafio.application.mapper.RecomendacaoModelMapper;
import br.com.desafio.application.usecase.ObterRecomendacaoVinhoUseCase;
import br.com.desafio.domain.gateway.CompraGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObterRecomendacaoVinhoUseCaseImpl implements ObterRecomendacaoVinhoUseCase {

    private final CompraGateway compraGateway;

    @Override
    public RecomendacaoResponseDTO execute(String cpf) {
        return compraGateway.obterRecomendacaoVinho(cpf).stream().findFirst()
                .map(RecomendacaoModelMapper::toDomain)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com o CPF: " + cpf));
    }
}
