package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.application.mapper.CompraModelMapper;
import br.com.desafio.application.usecase.ObterMaiorCompraPorAnoUseCase;
import br.com.desafio.domain.gateway.CompraGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObterMaiorCompraPorAnoUseCaseImpl implements ObterMaiorCompraPorAnoUseCase {

    private final CompraGateway compraGateway;

    @Override
    public CompraResponseDTO obterMaiorCompraPorAno(int ano) {
        return compraGateway.obterMaiorCompraPorAno(ano).stream()
                .findFirst()
                .map(CompraModelMapper::toDomain)
                .orElse(null);
    }
}
