package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.application.mapper.CompraModelMapper;
import br.com.desafio.application.usecase.ListarMaiorCompraPorAnoUseCase;
import br.com.desafio.domain.gateway.CompraGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListarMaiorCompraPorAnoUseCaseImpl implements ListarMaiorCompraPorAnoUseCase {

    private final CompraGateway compraGateway;

    @Override
    public CompraResponseDTO listarMaiorCompraPorAno(int ano) {
        return compraGateway.findMaiorCompraPorAno(ano).stream()
                .findFirst()
                .map(CompraModelMapper::toDomain)
                .orElse(null);
    }
}
